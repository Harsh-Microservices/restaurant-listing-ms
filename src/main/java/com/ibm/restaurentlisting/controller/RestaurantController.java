package com.ibm.restaurentlisting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.restaurentlisting.dto.RestaurantDto;
import com.ibm.restaurentlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@PostMapping("/add")
	public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
		RestaurantDto createRestaurant = this.restaurantService.createRestaurant(restaurantDto);
		return new ResponseEntity<RestaurantDto>(createRestaurant, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<RestaurantDto>> fetchAllDetails() {
		List<RestaurantDto> allRestarantList = this.restaurantService.getAllRestarantList();
		return new ResponseEntity<List<RestaurantDto>>(allRestarantList, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<RestaurantDto> fetchAllDetails(@PathVariable int id) {
		ResponseEntity<RestaurantDto> allRestarantById = this.restaurantService.getAllRestarantById(id);
		return allRestarantById;
	}
}
