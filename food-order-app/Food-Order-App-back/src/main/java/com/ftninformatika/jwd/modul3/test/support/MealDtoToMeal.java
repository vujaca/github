package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Meal;
import com.ftninformatika.jwd.modul3.test.service.MealService;
import com.ftninformatika.jwd.modul3.test.service.RestaurantService;
import com.ftninformatika.jwd.modul3.test.web.dto.MealDTO;

@Component
public class MealDtoToMeal implements Converter<MealDTO, Meal>{

	@Autowired
	MealService mealService;
	
	@Autowired
	RestaurantService restaurantService;
	
	
	@Override
	public Meal convert(MealDTO dto) {
		Meal meal;
		
		if(dto.getId() == null) {
			meal = new Meal();
		}else {
			meal = mealService.findOne(dto.getId());
		}
		if(meal != null) {
			meal.setAmount(dto.getAmount());
			meal.setDescription(dto.getDescription());
			meal.setName(dto.getName());
			meal.setPrice(dto.getPrice());
			meal.setRestaurant(restaurantService.findOne(dto.getRestaurant().getId()));
			meal.setImageUrl(dto.getImageUrl());
		}
		return meal;
	}

}
