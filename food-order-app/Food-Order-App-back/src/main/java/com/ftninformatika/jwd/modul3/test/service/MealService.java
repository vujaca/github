package com.ftninformatika.jwd.modul3.test.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Meal;

public interface MealService {

	Meal findOne(Long id);

    Page<Meal> findAll(Integer pageNo);
    
    List<Meal> find(List<Long> ids);

    Meal save(Meal meal);

    Meal update(Meal meal);

    Meal delete(Long id);

    Page<Meal> find(String name, String description, Double minPrice, Double maxPrice, Long restaurantId, Integer pageNo);

}
