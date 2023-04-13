package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;

@Component
public class IgracToIgracDTO implements Converter<Igrac, IgracDTO>{

	@Override
	public IgracDTO convert(Igrac igrac) {
		IgracDTO dto = new IgracDTO();
		dto.setId(igrac.getId());
		dto.setIme(igrac.getIme());
		dto.setPostignutiGolovi(igrac.getPostignutiGolovi());
		dto.setPrezime(igrac.getPrezime());
		return dto;
	}
	
	public List<IgracDTO> convert(List<Igrac> Igraci) {
		List<IgracDTO> IgraciDTO = new ArrayList<IgracDTO>();
		
		for(Igrac Igrac : Igraci) {
			IgraciDTO.add(convert(Igrac));
		}
		
		return IgraciDTO;
	}

}
