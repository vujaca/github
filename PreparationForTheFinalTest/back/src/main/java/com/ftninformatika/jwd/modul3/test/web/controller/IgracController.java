package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.UtakmicaDTO;

@RestController
@RequestMapping(value = "/api/igraci", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class IgracController {

	@Autowired
	private IgracService igracService;
	
	@Autowired
	private IgracToIgracDTO toIgracDto;
	
	@Autowired
	private ReprezentacijaService reprezentacijaService;
	
	@GetMapping
	public ResponseEntity<List<IgracDTO>> getAll() {
		
		List<Igrac> Igraci = igracService.findAll();
		
		return new ResponseEntity<>(toIgracDto.convert(Igraci), HttpStatus.OK);
	}
	
	@GetMapping(value = "/reprezentacije/{reprezentacijaId}")
	public ResponseEntity<List<IgracDTO>> getByReprezentacija(@PathVariable Long reprezentacijaId) {
		List<Igrac> Igraci = igracService.findByReprezentacija(reprezentacijaId);
		return new ResponseEntity<>(toIgracDto.convert(Igraci), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @PutMapping(value= "strelac/{id}")
	    public ResponseEntity<IgracDTO> update(@PathVariable Long id){
			
			Igrac igrac = igracService.findOne(id);

	        if(igrac.getReprezentacija() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        int postignutiGolovi = igrac.getPostignutiGolovi()+1;
	       igrac.setPostignutiGolovi(postignutiGolovi);

	        Igrac sacuvanIgrac = igracService.update(igrac);

	        return new ResponseEntity<>(toIgracDto.convert(sacuvanIgrac),HttpStatus.OK);
	    }
	
}
