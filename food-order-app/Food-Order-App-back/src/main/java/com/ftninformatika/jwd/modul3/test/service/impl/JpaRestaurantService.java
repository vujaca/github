package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Restaurant;
import com.ftninformatika.jwd.modul3.test.repository.RestaurantRepository;
import com.ftninformatika.jwd.modul3.test.service.RestaurantService;

@Service
public class JpaRestaurantService implements RestaurantService{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurant findOne(Long id) {
		return restaurantRepository.findOneById(id);
	}

	@Override
	public Page<Restaurant> findAll(Integer pageNo) {
		return restaurantRepository.findAll(PageRequest.of(pageNo, 10));
	}

	@Override
	public Restaurant save(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant update(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant delete(Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if(restaurant.isPresent()) {
			restaurantRepository.deleteById(id);
			return restaurant.get();
		}
		return null;
	}

	@Override
	public Page<Restaurant> find(String name, Integer minFoundingYear, Integer maxFoundingYear, String location,
			Integer pageNo) {
		if(name == null ) {
			name = "";
		}
		if(minFoundingYear == null) {
			minFoundingYear = 0;
		}
		if(maxFoundingYear == null ) {
			maxFoundingYear = Integer.MAX_VALUE;
		}
		if(location == null) {
			location = "";
		}
		return restaurantRepository.findByNameIgnoreCaseContainsAndFoundingYearBetweenAndLocationIgnoreCaseContains(name, minFoundingYear, maxFoundingYear, location, PageRequest.of(pageNo, 10));
	}

}
