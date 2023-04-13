package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Ljubimac;

@Repository
public interface LjubimacRepository extends JpaRepository<Ljubimac, Long>{

	Ljubimac findOneById(Long id);
	
	@Query("SELECT k FROM Ljubimac k WHERE "+
			"(:pol IS NULL OR k.pol like %:pol%) AND "+
			"(:kategorijaId IS NULL OR k.kategorija.id = :kategorijaId) AND "+
			"(:opis IS NULL OR k.opis like %:opis%)")
	Page<Ljubimac>search(@Param("pol")String pol, @Param("kategorijaId")Long kategorijaId,@Param("opis")String opis, Pageable pageable);
}
