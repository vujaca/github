package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;
import com.ftninformatika.jwd.modul3.test.web.dto.UdomljavanjeDTO;

@Component
public class UdomljavanjeToUdomljavanjeDTO implements Converter<Udomljavanje, UdomljavanjeDTO> {

	@Override
	public UdomljavanjeDTO convert(Udomljavanje udomljavanje) {
		UdomljavanjeDTO dto = new UdomljavanjeDTO();
		dto.setId(udomljavanje.getId());
		dto.setLjubimacId(udomljavanje.getLjubimac().getId());
		dto.setLjubimacNaziv(udomljavanje.getLjubimac().getIme());
		return dto;
	}
	
	public List<UdomljavanjeDTO> convert(List<Udomljavanje>udomljavanja) {
		List<UdomljavanjeDTO>udomljavanjaDTO = new ArrayList<UdomljavanjeDTO>();
		
		for(Udomljavanje Udomljavanje: udomljavanja) {
			udomljavanjaDTO.add(convert(Udomljavanje));
		}
		return udomljavanjaDTO;
	}

	
}
