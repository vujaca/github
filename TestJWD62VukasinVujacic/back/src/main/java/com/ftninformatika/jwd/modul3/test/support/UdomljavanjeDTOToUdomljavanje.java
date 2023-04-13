package com.ftninformatika.jwd.modul3.test.support;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;
import com.ftninformatika.jwd.modul3.test.service.LjubimacService;
import com.ftninformatika.jwd.modul3.test.service.UdomljavanjeService;
import com.ftninformatika.jwd.modul3.test.web.dto.UdomljavanjeDTO;

@Component
public class UdomljavanjeDTOToUdomljavanje implements Converter<UdomljavanjeDTO, Udomljavanje>{

	@Autowired
	UdomljavanjeService udomljavanjeService;
	
	@Autowired
	LjubimacService ljubimacService;
	
	@Override
	public Udomljavanje convert(UdomljavanjeDTO dto) {
		Udomljavanje udomljavanje;
		
		if(dto.getId() == null) {
			udomljavanje = new Udomljavanje();
		}else {
			udomljavanje = udomljavanjeService.findOne(dto.getId());
		}
		
		if(udomljavanje != null) {
			udomljavanje.setDatumIVreme(LocalDateTime.now());
			udomljavanje.setLjubimac(ljubimacService.findOne(dto.getLjubimacId()));
		}
		return udomljavanje;
	}

	
}
