package com.ftninformatika.jwd.modul3.test.web.controller;

import com.ftninformatika.jwd.modul3.test.model.User;
import com.ftninformatika.jwd.modul3.test.security.TokenUtils;
import com.ftninformatika.jwd.modul3.test.service.UserService;
import com.ftninformatika.jwd.modul3.test.support.UserDtoToUser;
import com.ftninformatika.jwd.modul3.test.support.UserToUserDto;
import com.ftninformatika.jwd.modul3.test.web.dto.AuthUserDto;
import com.ftninformatika.jwd.modul3.test.web.dto.UserDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.KorisnikPromenaLozinkeDto;
import com.ftninformatika.jwd.modul3.test.web.dto.KorisnikRegistracijaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService korisnikService;

    @Autowired
    private UserDtoToUser toKorisnik;

    @Autowired
    private UserToUserDto toKorisnikDto;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity create(@RequestBody @Validated KorisnikRegistracijaDTO dto){

        if(dto.getId() != null || !dto.getPassword().equals(dto.getRepeatedPassword())) {
        	 Map<String, String> errors = new HashMap<>();
             errors.put("message", "Password and repeated password must be same!");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter za njega
        // ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
        User korisnik = toKorisnik.convert(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        korisnik.setPassword(encodedPassword);



        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO korisnikDTO){

        if(!id.equals(korisnikDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User korisnik = toKorisnik.convert(korisnikDTO);

        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id){
        Optional<User> korisnik = korisnikService.findOne(id);

        if(korisnik.isPresent()) {
            return new ResponseEntity<>(toKorisnikDto.convert(korisnik.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> get(@RequestParam(defaultValue="0") int page){
        Page<User> korisnici = korisnikService.findAll(page);
        return new ResponseEntity<>(toKorisnikDto.convert(korisnici.getContent()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_KORISNIK')")
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "promenaLozinke")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody KorisnikPromenaLozinkeDto dto){
        // ova metoda se "okida" kada se primi PUT /korisnici?promenaLozinke
        // pogrešno bi bilo mapirati na npr. PUT /korisnici/lozinke, pošto "lozinka" nije punopravan REST resurs!

        if(!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean rezultat;
        try {
            rezultat = korisnikService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(rezultat) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthUserDto dto) {
        try {
            // Perform the authentication
            UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Reload user details so we can generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadCredentialsException e) {
            //(422 or 401 status)
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
        } catch (org.springframework.security.access.AccessDeniedException e) {
            //(403 status)
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Wrong username or password");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
        }	catch (InternalServerError e) {
        	 Map<String, String> errors = new HashMap<>();
             errors.put("message", "Internal server error");
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
        }
        catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
        }
    }
}
