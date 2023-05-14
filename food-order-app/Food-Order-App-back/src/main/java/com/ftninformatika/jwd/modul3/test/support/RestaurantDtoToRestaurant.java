package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Restaurant;
import com.ftninformatika.jwd.modul3.test.service.RestaurantService;
import com.ftninformatika.jwd.modul3.test.web.dto.RestaurantDTO;

@Component
public class RestaurantDtoToRestaurant implements Converter<RestaurantDTO, Restaurant> {

	@Autowired
	RestaurantService restaurantService;
	
	@Override
	public Restaurant convert(RestaurantDTO dto) {
		Restaurant restaurant;
		
		if(dto.getId() == null) {
			restaurant = new Restaurant();
		}else {
			restaurant = restaurantService.findOne(dto.getId());
		}
		if(restaurant != null) {
			restaurant.setAboutUs(dto.getAboutUs());
			restaurant.setContact(dto.getContact());
			restaurant.setFoundingYear(dto.getFoundingYear());
			restaurant.setLocation(dto.getLocation());
			restaurant.setName(dto.getName());
			restaurant.setImageUrl(dto.getImageUrl());
		}
		return restaurant;
	}

	
}
