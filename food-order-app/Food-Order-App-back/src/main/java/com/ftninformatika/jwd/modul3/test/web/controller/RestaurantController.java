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

import com.ftninformatika.jwd.modul3.test.model.Restaurant;
import com.ftninformatika.jwd.modul3.test.service.RestaurantService;
import com.ftninformatika.jwd.modul3.test.support.RestaurantDtoToRestaurant;
import com.ftninformatika.jwd.modul3.test.support.RestaurantToRestaurantDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.RestaurantDTO;

@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	RestaurantToRestaurantDTO toRestaurantDTO;
	
	@Autowired
	RestaurantDtoToRestaurant toRestaurant;
	
	@PreAuthorize("permitAll()")
	 @GetMapping
	    public ResponseEntity<List<RestaurantDTO>> getAll(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) Integer minFoundingYear,
	            @RequestParam(required = false) Integer maxFoundingYear,
	            @RequestParam(required = false) String location,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Restaurant> page;
	        	
	        		page = restaurantService.find(name, minFoundingYear, maxFoundingYear, location, pageNo);
	        	
	        			HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toRestaurantDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }
	 
	@PreAuthorize("permitAll()")
	 @GetMapping("/{id}")
	    public ResponseEntity<RestaurantDTO> getOne(@PathVariable Long id){
		Restaurant Restaurant = restaurantService.findOne(id);

	        if(Restaurant != null) {
	            return new ResponseEntity<>(toRestaurantDTO.convert(Restaurant), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK','ADMIN')")
	 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RestaurantDTO> create(@Valid @RequestBody RestaurantDTO RestaurantDTO){
		Restaurant Restaurant = toRestaurant.convert(RestaurantDTO);
		 
		if(Restaurant.getMeals() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

		Restaurant savedRestaurant = restaurantService.save(Restaurant);

	        return new ResponseEntity<>(toRestaurantDTO.convert(savedRestaurant), HttpStatus.CREATED);
	    }
	
	@PreAuthorize("hasAnyRole('KORISNIK','ADMIN')")
	 @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<RestaurantDTO> update(@PathVariable Long id, @Valid @RequestBody RestaurantDTO RestaurantDTO){

	        if(!id.equals(RestaurantDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Restaurant Restaurant = toRestaurant.convert(RestaurantDTO);
	        if(Restaurant.getMeals() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Restaurant savedRestaurant = restaurantService.update(Restaurant);

	        return new ResponseEntity<>(toRestaurantDTO.convert(savedRestaurant),HttpStatus.OK);
	    }
	 
	@PreAuthorize("hasRole('ADMIN')")
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){
		Restaurant deletedRestaurant = restaurantService.delete(id);

	        if(deletedRestaurant != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
}
