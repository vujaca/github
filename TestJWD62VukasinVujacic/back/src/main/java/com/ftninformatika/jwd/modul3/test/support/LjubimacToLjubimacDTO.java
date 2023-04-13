package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;
import com.ftninformatika.jwd.modul3.test.web.dto.LjubimacDTO;

@Component
public class LjubimacToLjubimacDTO implements Converter<Ljubimac, LjubimacDTO>{

	@Override
	public LjubimacDTO convert(Ljubimac ljubimac) {
		LjubimacDTO dto = new LjubimacDTO();
		
		dto.setId(ljubimac.getId());
		dto.setIme(ljubimac.getIme());
		dto.setKategorijaId(ljubimac.getKategorija().getId());
		dto.setKategorijaNaziv(ljubimac.getKategorija().getNaziv());
		dto.setOpis(ljubimac.getOpis());
		dto.setPol(ljubimac.getPol());
		dto.setStarost(ljubimac.getStarost());
		dto.setTezina(ljubimac.getTezina());
		dto.setVakcinisan(ljubimac.isVakcinisan());
		return dto;
	}

	public List<LjubimacDTO> convert(List<Ljubimac>ljubimci) {
		List<LjubimacDTO> ljubimciDTO = new ArrayList<LjubimacDTO>();
		
		for(Ljubimac Ljubimac: ljubimci) {
			ljubimciDTO.add(convert(Ljubimac));
		}
		
		return ljubimciDTO;
	}
}
