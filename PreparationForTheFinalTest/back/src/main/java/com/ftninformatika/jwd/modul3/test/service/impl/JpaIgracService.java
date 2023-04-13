package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;
import com.ftninformatika.jwd.modul3.test.repository.IgracRepository;
import com.ftninformatika.jwd.modul3.test.repository.ReprezentacijaRepository;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.service.ReprezentacijaService;

@Service
public class JpaIgracService implements IgracService{

	@Autowired
	private IgracRepository igracRepo;
	
	@Autowired
	private ReprezentacijaService reprezentacijaService;

	@Override
	public List<Igrac> findAll() {
		return igracRepo.findAll();
	}

	@Override
	public Igrac findOne(Long id) {
		return igracRepo.findOneById(id);
	}

	@Override
	public List<Igrac> findByReprezentacija(Long reprezentacijaId) {
		List<Igrac>igraci = new ArrayList<Igrac>();
		Reprezentacija reprezentacija = reprezentacijaService.findOne(reprezentacijaId);
		System.out.println(reprezentacija);
		for(Igrac igrac: reprezentacija.getIgraci()) {
			igraci.add(igrac);
		}
		System.out.println(igraci);
			return igraci;
		
	}

	@Override
	public Igrac save(Igrac igrac) {
		
		return igracRepo.save(igrac);
	}

	@Override
	public Igrac update(Igrac igrac) {
		// TODO Auto-generated method stub
		return igracRepo.save(igrac);
	}
	
	
	
	
}
