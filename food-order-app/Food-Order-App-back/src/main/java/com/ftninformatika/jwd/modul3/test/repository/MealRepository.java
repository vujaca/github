package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{
	
	Meal findOneById(Long id);
	
	List<Meal>findByIdIn(List<Long>ids);
	
	@Query("SELECT k FROM Meal k WHERE " +
	"(:name IS NULL OR k.name like %:name%) AND "+
	"(:description IS NULL OR k.description like %:description%) AND "+
	"(:minPrice IS NULL OR k.price >= :minPrice) AND "+
	"(:maxPrice IS NULL OR k.price <= :maxPrice) AND "+
	"(:restaurantId IS NULL OR k.restaurant.id =:restaurantId)")
	Page<Meal>search(@Param("name")String name, @Param("description")String description, @Param("minPrice")Double minPrice, @Param("maxPrice")Double maxPrice, @Param("restaurantId")Long restaurantId, Pageable pageable);
			
			
	
}
