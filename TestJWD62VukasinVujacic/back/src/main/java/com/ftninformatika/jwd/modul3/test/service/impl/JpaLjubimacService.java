package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;
import com.ftninformatika.jwd.modul3.test.model.Udomljavanje;
import com.ftninformatika.jwd.modul3.test.repository.LjubimacRepository;
import com.ftninformatika.jwd.modul3.test.repository.UdomljavanjeRepository;
import com.ftninformatika.jwd.modul3.test.service.LjubimacService;

@Service
public class JpaLjubimacService implements LjubimacService{

	@Autowired
	LjubimacRepository repository;
	
	@Autowired
	UdomljavanjeRepository udomljavanjeRepository;
	
	@Override
	public Page<Ljubimac> findAll(int pageNo) {
		// TODO Auto-generated method stub
		return repository.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Ljubimac findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOneById(id);
	}

	@Override
	public Ljubimac save(Ljubimac Ljubimac) {
		if(Ljubimac.getPol().equals("muski")|| Ljubimac.getPol().equals("zenski")) {
		Ljubimac.setVakcinisan(false);
		return repository.save(Ljubimac);}
		else {
			return null;
		}
	}

	@Override
	public Ljubimac update(Ljubimac Ljubimac) {
		if(Ljubimac.getPol().equals("muski")|| Ljubimac.getPol().equals("zenski")) {
			return repository.save(Ljubimac);}
			else {
				return null;
			}
	}

	@Override
	public Ljubimac delete(Long id) {
		Ljubimac Ljubimac = findOne(id);
		List<Udomljavanje>udomljavanja = udomljavanjeRepository.findAll();
		if(Ljubimac != null) {
			Ljubimac.getKategorija().getLjubimci().remove(Ljubimac);
			for(Udomljavanje udomljavanje: udomljavanja) {
				if(udomljavanje.getLjubimac()==Ljubimac) {
					udomljavanjeRepository.delete(udomljavanje);
				}
			}
			repository.delete(Ljubimac);
			return Ljubimac;
		}
		return null;
	}

	@Override
	public Page<Ljubimac> find(String pol, Long kategorijaId, String opis, int pageNo) {
		// TODO Auto-generated method stub
		return repository.search(pol, kategorijaId, opis, PageRequest.of(pageNo, 2)) ;
	}
	@Override
	public Ljubimac promeniVakcinisanost(Long id) {
		Ljubimac ljubimac = repository.findOneById(id);
		if(ljubimac == null ) {
			return null;
		}
		else {
			boolean vakcinisan = ljubimac.isVakcinisan();
			if(vakcinisan == true) {
				ljubimac.setVakcinisan(false);
			}else {
				ljubimac.setVakcinisan(true);
			}
			return ljubimac;
		}
	}

	
}
