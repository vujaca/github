package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;
import com.ftninformatika.jwd.modul3.test.repository.ReprezentacijaRepository;
import com.ftninformatika.jwd.modul3.test.service.ReprezentacijaService;

@Service
public class JpaReprezentacijaService implements ReprezentacijaService{

	@Autowired
	private ReprezentacijaRepository repo;

	@Override
	public List<Reprezentacija> findAll() {
		return repo.findAll();
	}

	@Override
	public Reprezentacija findOne(Long id) {
		return repo.findOneById(id);
	}
	
	
}
