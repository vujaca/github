package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Meal;
import com.ftninformatika.jwd.modul3.test.service.MealService;
import com.ftninformatika.jwd.modul3.test.support.MealDtoToMeal;
import com.ftninformatika.jwd.modul3.test.support.MealToMealDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.MealDTO;

@RestController
@RequestMapping(value = "/api/meals", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MealController {
	
	@Autowired
	private MealService mealService;
	
	@Autowired
	private MealToMealDTO toMealDTO;
	
	@Autowired
	private MealDtoToMeal toMeal;
	
	@PreAuthorize("permitAll()")
	 @GetMapping
	    public ResponseEntity<List<MealDTO>> getAll(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) String description,
	            @RequestParam(required = false) Double minPrice,
	            @RequestParam(required = false) Double maxPrice,
	            @RequestParam(required = false) Long restaurantId,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Meal> page;
	        	
	        		page = mealService.find(name, description, minPrice, maxPrice, restaurantId, pageNo);
	        	
	        			HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toMealDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }
	 
	@PreAuthorize("permitAll()")
	 @GetMapping("/{id}")
	    public ResponseEntity<MealDTO> getOne(@PathVariable Long id){
		Meal Meal = mealService.findOne(id);

	        if(Meal != null) {
	            return new ResponseEntity<>(toMealDTO.convert(Meal), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK','ADMIN')")
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<MealDTO> create(@Valid @RequestBody MealDTO MealDTO){
		Meal Meal = toMeal.convert(MealDTO);

	        if(Meal.getRestaurant() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Meal savedMeal = mealService.save(Meal);

	        return new ResponseEntity<>(toMealDTO.convert(savedMeal), HttpStatus.CREATED);
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK','ADMIN')")
	 @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<MealDTO> update(@PathVariable Long id, @Valid @RequestBody MealDTO MealDTO){

	        if(!id.equals(MealDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Meal Meal = toMeal.convert(MealDTO);
	        if(Meal.getRestaurant() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Meal savedMeal = mealService.update(Meal);

	        return new ResponseEntity<>(toMealDTO.convert(savedMeal),HttpStatus.OK);
	    }
	 
	@PreAuthorize("hasRole('ADMIN')")
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
		Meal deletedMeal = mealService.delete(id);

	        if(deletedMeal != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
