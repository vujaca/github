package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftninformatika.jwd.modul3.test.model.Igrac;

public interface IgracRepository extends JpaRepository<Igrac, Long>{

	Igrac findOneById(Long id);
	
	List<Igrac>findByReprezentacijaId(Long reprezentacijaId);
}
