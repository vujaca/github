package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Restaurant findOneById(Long id);
	
	Page<Restaurant> findByNameIgnoreCaseContainsAndFoundingYearBetweenAndLocationIgnoreCaseContains(String name, Integer minFoundingYear, Integer maxFoundingYear, String location, Pageable pageable);
//	@Query("SELECT k from Restaurant k WHERE "+
//			"(:name IS NULL OR k.name like %:name$) AND "+
//			"(:minFoundingYear IS NULL OR k.foundingYear >= :minFoundingYear) AND "+
//			"(:adre)"
//			
//			)
	
}
