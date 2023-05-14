package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Restaurant;
import com.ftninformatika.jwd.modul3.test.web.dto.RestaurantDTO;

@Component
public class RestaurantToRestaurantDTO implements Converter<Restaurant, RestaurantDTO>{

	@Override
	public RestaurantDTO convert(Restaurant restaurant) {
		RestaurantDTO dto = new RestaurantDTO();
		dto.setAboutUs(restaurant.getAboutUs());
		dto.setContact(restaurant.getContact());
		dto.setFoundingYear(restaurant.getFoundingYear());
		dto.setId(restaurant.getId());
		dto.setLocation(restaurant.getLocation());
		dto.setName(restaurant.getName());
		dto.setImageUrl(restaurant.getImageUrl());
		return dto;
	}
	
	public List<RestaurantDTO> convert(List<Restaurant>restaurants) {
		List<RestaurantDTO> restaurantsDTO = new ArrayList<RestaurantDTO>();
		
		for(Restaurant Restaurant: restaurants) {
			restaurantsDTO.add(convert(Restaurant));
		}
		
		return restaurantsDTO;
	}

	
}
