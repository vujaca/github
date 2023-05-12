package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Meal;
import com.ftninformatika.jwd.modul3.test.web.dto.MealDTO;

@Component
public class MealToMealDTO implements Converter<Meal, MealDTO> {

	@Autowired
	RestaurantToRestaurantDTO toRestaurantDTO;
	
	@Override
	public MealDTO convert(Meal meal) {
		MealDTO dto = new MealDTO();
		
		dto.setAmount(meal.getAmount());
		dto.setDescription(meal.getDescription());
		dto.setId(meal.getId());
		dto.setName(meal.getName());
		dto.setPrice(meal.getPrice());
		dto.setRestaurant(toRestaurantDTO.convert(meal.getRestaurant()));
		return dto;
	}
	
	public List<MealDTO> convert(List<Meal>meals) {
		List<MealDTO> mealsDTO = new ArrayList<MealDTO>();
		
		for(Meal Meal: meals) {
			mealsDTO.add(convert(Meal));
		}
		
		return mealsDTO;
	}
	

}
