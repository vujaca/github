package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.service.ReprezentacijaService;
import com.ftninformatika.jwd.modul3.test.service.UtakmicaService;
import com.ftninformatika.jwd.modul3.test.support.UtakmicaDTOToUtakmica;
import com.ftninformatika.jwd.modul3.test.support.UtakmicaToUtakmicaDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.UtakmicaDTO;

@RestController
@RequestMapping(value = "/api/utakmice", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UtakmicaController {

	@Autowired
	private UtakmicaService utakmicaService;
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private ReprezentacijaService reprezentacijaService;
	
	@Autowired
	private UtakmicaToUtakmicaDTO toUtakmicaDTO;
	
	@Autowired
	private UtakmicaDTOToUtakmica toUtakmica;
	
	@PreAuthorize("permitAll()")
	 @GetMapping
	    public ResponseEntity<List<UtakmicaDTO>> getAll(
	            @RequestParam(required = false) Long reprezentacijaAId,
	            @RequestParam(required = false) Long reprezentacijaBId,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Utakmica> page;
	        	//if(destinacija != null || prevoznikId != null || cenaKarteDo != null) {
	        		page = utakmicaService.find(reprezentacijaAId, reprezentacijaBId, pageNo);
	        	//}
	        	//page = linijaService.findAll(pageNo);
	        			HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toUtakmicaDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }
	 
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping("/{id}")
	    public ResponseEntity<UtakmicaDTO> getOne(@PathVariable Long id){
		Utakmica Utakmica = utakmicaService.findOne(id);

	        if(Utakmica != null) {
	            return new ResponseEntity<>(toUtakmicaDTO.convert(Utakmica), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<UtakmicaDTO> create(@Valid @RequestBody UtakmicaDTO UtakmicaDTO){
		Utakmica Utakmica = toUtakmica.convert(UtakmicaDTO);

	        if(Utakmica.getReprezentacijaA() == null || Utakmica.getReprezentacijaB()==null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        
	        if(Utakmica.getReprezentacijaA() == Utakmica.getReprezentacijaB()) {
	        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Utakmica sacuvanaUtakmica = utakmicaService.save(Utakmica);

	        return new ResponseEntity<>(toUtakmicaDTO.convert(sacuvanaUtakmica), HttpStatus.CREATED);
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<UtakmicaDTO> update(@PathVariable Long id, @Valid @RequestBody UtakmicaDTO UtakmicaDTO){

	        if(!id.equals(UtakmicaDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Utakmica Utakmica = toUtakmica.convert(UtakmicaDTO);

	        if(Utakmica.getReprezentacijaA() == null || Utakmica.getReprezentacijaB()==null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        if(Utakmica.getReprezentacijaA() == Utakmica.getReprezentacijaB()) {
	        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Utakmica sacuvanaUtakmica = utakmicaService.update(Utakmica);

	        return new ResponseEntity<>(toUtakmicaDTO.convert(sacuvanaUtakmica),HttpStatus.OK);
	    }
	 
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
		Utakmica obrisanaUtakmica = utakmicaService.delete(id);

	        if(obrisanaUtakmica != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	
	
}
