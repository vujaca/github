package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;

public interface IgracService {
	
	List<Igrac>findAll();
	
	Igrac findOne(Long id);
	
	List<Igrac>findByReprezentacija(Long reprezentacijaId);
	
	Igrac save(Igrac igrac);
	
	Igrac update(Igrac igrac);
}
