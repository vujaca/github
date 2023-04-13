package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;
import com.ftninformatika.jwd.modul3.test.service.ReprezentacijaService;
import com.ftninformatika.jwd.modul3.test.service.UtakmicaService;
import com.ftninformatika.jwd.modul3.test.web.dto.UtakmicaDTO;

@Component
public class UtakmicaDTOToUtakmica implements Converter<UtakmicaDTO, Utakmica>{

	@Autowired
	private UtakmicaService utakmicaService;
	
	@Autowired
	private ReprezentacijaService reprezentacijaService;

	@Override
	public Utakmica convert(UtakmicaDTO dto) {
		Utakmica utakmica = new Utakmica();
		
		if(dto.getId() == null) {
			utakmica = new Utakmica();
		}else {
			utakmica = utakmicaService.findOne(dto.getId());
		}
		
		if(utakmica!= null) {
			utakmica.setBrojGolovaA(dto.getBrojGolovaA());
			utakmica.setBrojGolovaB(dto.getBrojGolovaB());
			utakmica.setReprezentacijaA(reprezentacijaService.findOne(dto.getReprezentacijaA().getId()));
			utakmica.setReprezentacijaB(reprezentacijaService.findOne(dto.getReprezentacijaB().getId()));
		}
		return utakmica;
	}
	
	
}
