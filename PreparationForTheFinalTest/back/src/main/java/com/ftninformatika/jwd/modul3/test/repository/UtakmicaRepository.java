package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftninformatika.jwd.modul3.test.model.Utakmica;

public interface UtakmicaRepository extends JpaRepository<Utakmica, Long>{

	Utakmica findOneById(Long id);
	
	@Query("SELECT k from Utakmica k WHERE "+
			"(:reprezentacijaAId IS NULL OR k.reprezentacijaA.id = :reprezentacijaAId) AND "+
			"(:reprezentacijaBId IS NULL OR k.reprezentacijaB.id = :reprezentacijaBId)")
	Page<Utakmica>search(@Param("reprezentacijaAId")Long reprezentacijaAId, @Param("reprezentacijaBId") Long reprezentacijaBId, Pageable pageable);
}
