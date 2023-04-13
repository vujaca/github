package com.ftninformatika.jwd.modul3.test.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;
import com.ftninformatika.jwd.modul3.test.repository.UdomljavanjeRepository;
import com.ftninformatika.jwd.modul3.test.service.UdomljavanjeService;

@Service
public class JpaUdomljavanjeService implements UdomljavanjeService{

	@Autowired
	UdomljavanjeRepository repository;
	
	@Override
	public Udomljavanje findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

	@Override
	public Udomljavanje save(Udomljavanje udomljavanje) {
		List<Udomljavanje>udomljavanja = repository.findAll();
		for(Udomljavanje itUdomljavanje: udomljavanja) {
			if(itUdomljavanje.getLjubimac()==udomljavanje.getLjubimac())
				return null;
		}
		udomljavanje.setDatumIVreme(LocalDateTime.now());
		return repository.save(udomljavanje);}

}
