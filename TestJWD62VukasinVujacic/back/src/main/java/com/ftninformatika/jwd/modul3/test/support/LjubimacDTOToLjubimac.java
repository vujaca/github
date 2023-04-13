package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;
import com.ftninformatika.jwd.modul3.test.service.KategorijaService;
import com.ftninformatika.jwd.modul3.test.service.LjubimacService;
import com.ftninformatika.jwd.modul3.test.web.dto.LjubimacDTO;

@Component
public class LjubimacDTOToLjubimac implements Converter<LjubimacDTO, Ljubimac>{

	@Autowired
	LjubimacService ljubimacService;
	
	@Autowired
	KategorijaService kategorijaService;
	
	@Override
	public Ljubimac convert(LjubimacDTO dto) {
		Ljubimac ljubimac;
		
		if(dto.getId() == null) {
			ljubimac = new Ljubimac();
		}else {
			ljubimac = ljubimacService.findOne(dto.getId());
		}
		
		if(ljubimac!= null) {
			ljubimac.setIme(dto.getIme());
			ljubimac.setKategorija(kategorijaService.findOne(dto.getKategorijaId()));
			ljubimac.setOpis(dto.getOpis());
			ljubimac.setPol(dto.getPol());
			ljubimac.setStarost(dto.getStarost());
			ljubimac.setTezina(dto.getTezina());
			ljubimac.setVakcinisan(dto.isVakcinisan());
		}
		
		return ljubimac;
	}

	
}
