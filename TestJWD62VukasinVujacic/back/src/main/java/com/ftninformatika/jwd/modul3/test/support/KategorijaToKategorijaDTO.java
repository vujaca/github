package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Kategorija;
import com.ftninformatika.jwd.modul3.test.web.dto.KategorijaDTO;

@Component
public class KategorijaToKategorijaDTO implements Converter<Kategorija, KategorijaDTO>{

	@Override
	public KategorijaDTO convert(Kategorija kategorija) {
		KategorijaDTO dto = new KategorijaDTO();
		dto.setId(kategorija.getId());
		dto.setNaziv(kategorija.getNaziv());
		return dto;
	}
	
	public List<KategorijaDTO> convert(List<Kategorija> kategorije) {
		List<KategorijaDTO> kategorijeDTO = new ArrayList<KategorijaDTO>();
		
		for(Kategorija Kategorija : kategorije) {
			kategorijeDTO.add(convert(Kategorija));
		}
		
		return kategorijeDTO;
	}

	
}
