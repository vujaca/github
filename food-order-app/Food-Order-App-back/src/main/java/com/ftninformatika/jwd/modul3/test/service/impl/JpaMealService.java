package com.ftninformatika.jwd.modul3.test.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Meal;
import com.ftninformatika.jwd.modul3.test.repository.MealRepository;
import com.ftninformatika.jwd.modul3.test.service.MealService;

@Service
public class JpaMealService implements MealService{

	@Autowired
	private MealRepository mealRepository;
	
	@Override
	public Meal findOne(Long id) {
		return mealRepository.findOneById(id);
	}

	@Override
	public Page<Meal> findAll(Integer pageNo) {
		return mealRepository.findAll(PageRequest.of(pageNo, 10));
	}

	@Override
	public Meal save(Meal meal) {
		return mealRepository.save(meal);
	}

	@Override
	public Meal update(Meal meal) {
		return mealRepository.save(meal);
	}

	@Override
	public Meal delete(Long id) {
		Meal meal = mealRepository.findOneById(id);
		if(meal != null ) {
			meal.getRestaurant().getMeals().remove(meal);
			meal.setRestaurant(null);
			mealRepository.save(meal);
			mealRepository.deleteById(id);
			return meal;
		}
		return null;
	}

	@Override
	public Page<Meal> find(String name, String description, Double minPrice, Double maxPrice, Long restaurantId,
			Integer pageNo) {
		return mealRepository.search(name, description, minPrice, maxPrice, restaurantId, PageRequest.of(pageNo, 10));
	}

	@Override
	public List<Meal> find(List<Long> ids) {
		return mealRepository.findByIdIn(ids);
	}

}
