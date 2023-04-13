package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;

public interface ReprezentacijaService {

List<Reprezentacija>findAll();
	
Reprezentacija findOne(Long id);
}
