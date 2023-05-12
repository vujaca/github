package com.ftninformatika.jwd.modul3.test.service;


import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Restaurant;

public interface RestaurantService {

	Restaurant findOne(Long id);
	
	Page<Restaurant> findAll(Integer pageNo);

	Restaurant save(Restaurant restaurant);

	Restaurant update(Restaurant restaurant);

	Restaurant delete(Long id);

    Page<Restaurant> find(String name, Integer minFoundingYear, Integer maxFoundingYear, String location, Integer pageNo);

}
