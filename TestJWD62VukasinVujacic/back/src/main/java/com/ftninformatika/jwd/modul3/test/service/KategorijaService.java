package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Kategorija;


public interface KategorijaService {

List<Kategorija>findAll();
	
Kategorija findOne(Long id);
}
