package com.ftninformatika.jwd.modul3.test.web.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;
import com.ftninformatika.jwd.modul3.test.model.Utakmica;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.service.ReprezentacijaService;
import com.ftninformatika.jwd.modul3.test.support.IgracToIgracDTO;
import com.ftninformatika.jwd.modul3.test.support.ReprezentacijaToReprezentacijaDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.ReprezentacijaDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.UtakmicaDTO;

@RestController
@RequestMapping(value = "/api/reprezentacije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ReprezentacijaController {

	@Autowired
	private ReprezentacijaService reprezentacijaService;
	
	@Autowired
	private IgracService igracService;
	
	@Autowired
	private ReprezentacijaToReprezentacijaDTO toReprezentacijaDTO;
	
	@Autowired
	private IgracToIgracDTO toIgracDTO;
	
	@GetMapping
	public ResponseEntity<List<ReprezentacijaDTO>> getAll() {
		
		List<Reprezentacija> Reprezentacije = reprezentacijaService.findAll();
		
		return new ResponseEntity<>(toReprezentacijaDTO.convert(Reprezentacije), HttpStatus.OK);
	}
	
	
	
	
}
