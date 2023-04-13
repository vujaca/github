package com.ftninformatika.jwd.modul3.test.web.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;
import com.ftninformatika.jwd.modul3.test.service.LjubimacService;
import com.ftninformatika.jwd.modul3.test.service.UdomljavanjeService;
import com.ftninformatika.jwd.modul3.test.support.UdomljavanjeDTOToUdomljavanje;
import com.ftninformatika.jwd.modul3.test.support.UdomljavanjeToUdomljavanjeDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.UdomljavanjeDTO;

@RestController
@RequestMapping(value = "/api/udomljavanja", produces =  MediaType.APPLICATION_JSON_VALUE)
@Validated
public class UdomljavanjeController {

	@Autowired
	UdomljavanjeService udomljavanjeService;
	
	@Autowired
	LjubimacService ljubimacService;
	
	@Autowired
	UdomljavanjeToUdomljavanjeDTO toUdomljavanjeDTO;
	
	@Autowired
	UdomljavanjeDTOToUdomljavanje toUdomljavanje;
	
	@PreAuthorize("hasRole('KORISNIK')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UdomljavanjeDTO> create(@Valid @RequestBody UdomljavanjeDTO UdomljavanjeDTO){
		Udomljavanje Udomljavanje = toUdomljavanje.convert(UdomljavanjeDTO);
       
		if(Udomljavanje.getLjubimac() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Udomljavanje sacuvanoUdomljavanje = udomljavanjeService.save(Udomljavanje);

        return new ResponseEntity<>(toUdomljavanjeDTO.convert(sacuvanoUdomljavanje), HttpStatus.CREATED);
    }
	
}
