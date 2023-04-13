package com.ftninformatika.jwd.modul3.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;
import com.ftninformatika.jwd.modul3.test.repository.UtakmicaRepository;
import com.ftninformatika.jwd.modul3.test.service.UtakmicaService;

@Service
public class JpaUtakmicaService implements UtakmicaService{

	@Autowired
	private UtakmicaRepository repo;

	@Override
	public Page<Utakmica> findAll(int pageNo) {
		return repo.findAll(PageRequest.of(pageNo, 2));
	}

	@Override
	public Utakmica findOne(Long id) {
		return repo.findOneById(id);
	}

	@Override
	public Utakmica save(Utakmica Utakmica) {
		if(Utakmica.getReprezentacijaA()==Utakmica.getReprezentacijaB()) {
			return null;
		}
		Utakmica.setBrojGolovaA(0);
		Utakmica.setBrojGolovaB(0);
		return repo.save(Utakmica);
	}

	@Override
	public Utakmica update(Utakmica Utakmica) {
		return repo.save(Utakmica);
	}

	@Override
	public Utakmica delete(Long id) {
		Utakmica Utakmica = findOne(id);
		if(Utakmica != null) {
			repo.delete(Utakmica);
			return Utakmica;
		}
		return null;
	}

	@Override
	public Page<Utakmica> find(Long reprezentacijaAId, Long reprezentacijaBId, int pageNo) {
		return repo.search(reprezentacijaAId, reprezentacijaBId, PageRequest.of(pageNo, 3));
	}
	
	
}
