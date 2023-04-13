package com.ftninformatika.jwd.modul3.test.service;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;

public interface UtakmicaService {

Page<Utakmica> findAll(int pageNo);
	
Utakmica findOne(Long id);
	
Utakmica save(Utakmica Utakmica);
	
Utakmica update(Utakmica Utakmica);
	
Utakmica delete(Long id);
	
	Page<Utakmica> find(Long reprezentacijaAId, Long reprezentacijaBId, int pageNo);
}
