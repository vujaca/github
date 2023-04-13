package com.ftninformatika.jwd.modul3.test.service;


import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;

public interface LjubimacService {


	Page<Ljubimac> findAll(int pageNo);
	
	Ljubimac findOne(Long id);
		
	Ljubimac save(Ljubimac Ljubimac);
		
	Ljubimac update(Ljubimac Ljubimac);
		
	Ljubimac delete(Long id);
	
	Ljubimac promeniVakcinisanost(Long id);
	
		
		Page<Ljubimac> find(String pol, Long kategorijaId, String opis,int pageNo);
}
