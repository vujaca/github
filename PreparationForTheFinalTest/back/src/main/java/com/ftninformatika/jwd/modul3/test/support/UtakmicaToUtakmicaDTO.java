package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;
import com.ftninformatika.jwd.modul3.test.web.dto.UtakmicaDTO;

@Component
public class UtakmicaToUtakmicaDTO implements Converter<Utakmica, UtakmicaDTO>{

	@Autowired
	private ReprezentacijaToReprezentacijaDTO toDto;
	
	@Override
	public UtakmicaDTO convert(Utakmica utakmica) {
		UtakmicaDTO dto = new UtakmicaDTO();
		dto.setId(utakmica.getId());;
		dto.setBrojGolovaA(utakmica.getBrojGolovaA());
		dto.setBrojGolovaB(utakmica.getBrojGolovaB());
		dto.setReprezentacijaA(toDto.convert(utakmica.getReprezentacijaA()));
		dto.setReprezentacijaB(toDto.convert(utakmica.getReprezentacijaB()));
		return dto;	
	}

	public List<UtakmicaDTO> convert(List<Utakmica>Utakmice) {
		List<UtakmicaDTO> UtakmiceDTO = new ArrayList<UtakmicaDTO>();
		
		for(Utakmica Utakmica: Utakmice) {
			UtakmiceDTO.add(convert(Utakmica));
		}
		
		return UtakmiceDTO;
	}
	
}
