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

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;
import com.ftninformatika.jwd.modul3.test.service.LjubimacService;
import com.ftninformatika.jwd.modul3.test.support.LjubimacDTOToLjubimac;
import com.ftninformatika.jwd.modul3.test.support.LjubimacToLjubimacDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.LjubimacDTO;

@RestController
@RequestMapping(value = "/api/ljubimci", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LjubimacController {

	@Autowired
	LjubimacService ljubimacService;
	
	@Autowired
	LjubimacToLjubimacDTO toLjubimacDTO;
	
	@Autowired
	LjubimacDTOToLjubimac toLjubimac;
	
	@PreAuthorize("permitAll()")
	 @GetMapping
	    public ResponseEntity<List<LjubimacDTO>> getAll(
	            @RequestParam(required = false) String pol,
	            @RequestParam(required = false) Long kategorijaId,
	            @RequestParam(required = false) String opis,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Ljubimac> page;
	        	
	        		page = ljubimacService.find(pol, kategorijaId, opis, pageNo);
	        			HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toLjubimacDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }
	 
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping("/{id}")
	    public ResponseEntity<LjubimacDTO> getOne(@PathVariable Long id){
		Ljubimac ljubimac = ljubimacService.findOne(id);

	        if(ljubimac != null) {
	            return new ResponseEntity<>(toLjubimacDTO.convert(ljubimac), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LjubimacDTO> create(@Valid @RequestBody LjubimacDTO LjubimacDTO){
		Ljubimac ljubimac = toLjubimac.convert(LjubimacDTO);

	        if(ljubimac.getKategorija() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Ljubimac sacuvanLjubimac = ljubimacService.save(ljubimac);

	        return new ResponseEntity<>(toLjubimacDTO.convert(sacuvanLjubimac), HttpStatus.CREATED);
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<LjubimacDTO> update(@PathVariable Long id, @Valid @RequestBody LjubimacDTO LjubimacDTO){

	        if(!id.equals(LjubimacDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Ljubimac ljubimac = toLjubimac.convert(LjubimacDTO);
	        if(ljubimac.getKategorija()== null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Ljubimac sacuvanLjubimac = ljubimacService.update(ljubimac);

	        return new ResponseEntity<>(toLjubimacDTO.convert(sacuvanLjubimac),HttpStatus.OK);
	    }
	
	@PreAuthorize("hasRole( 'ADMIN')")
	 @PutMapping(value= "promeni/{id}")
	    public ResponseEntity<LjubimacDTO> update(@PathVariable Long id){

	        Ljubimac ljubimac = ljubimacService.findOne(id);
	        if(ljubimac.getKategorija()== null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        
	        ljubimac = ljubimacService.promeniVakcinisanost(id);

	        Ljubimac sacuvanLjubimac = ljubimacService.update(ljubimac);

	        return new ResponseEntity<>(toLjubimacDTO.convert(sacuvanLjubimac),HttpStatus.OK);
	    }
	
		@PreAuthorize("hasRole('ADMIN')")
		 @DeleteMapping("/{id}")
		    public ResponseEntity<Void> delete(@PathVariable Long id){
			Ljubimac obrisanLjubimac = ljubimacService.delete(id);

		        if(obrisanLjubimac != null) {
		            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }
	
	
}
