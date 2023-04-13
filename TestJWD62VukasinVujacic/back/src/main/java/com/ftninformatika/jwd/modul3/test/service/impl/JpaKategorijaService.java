package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Kategorija;
import com.ftninformatika.jwd.modul3.test.repository.KategorijaRepository;
import com.ftninformatika.jwd.modul3.test.service.KategorijaService;

@Service
public class JpaKategorijaService implements KategorijaService{

	@Autowired
	KategorijaRepository repository;
	
	@Override
	public List<Kategorija> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Kategorija findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

}
