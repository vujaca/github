package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftninformatika.jwd.modul3.test.model.Reprezentacija;

public interface ReprezentacijaRepository extends JpaRepository<Reprezentacija, Long>{

	Reprezentacija findOneById(Long id);
}
