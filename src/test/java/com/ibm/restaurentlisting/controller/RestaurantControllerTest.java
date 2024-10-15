package com.ibm.restaurentlisting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.restaurentlisting.dto.RestaurantDto;
import com.ibm.restaurentlisting.entity.Restaurant;
import com.ibm.restaurentlisting.mapper.RestaurantMapper;
import com.ibm.restaurentlisting.service.RestaurantService;

public class RestaurantControllerTest {
	
	@InjectMocks
	RestaurantController restaurantController;
	
	@Mock
	RestaurantService restaurantService;
	
	@BeforeEach
	private void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void addRestaurantTest() {
		//Action
		RestaurantDto restaurant=new RestaurantDto(1, "UFC", "California", "UA", "This is famous for western food");
		
		//Act
		when(restaurantService.createRestaurant(restaurant)).thenReturn(restaurant);
		
		ResponseEntity<RestaurantDto> addRestaurant = restaurantController.addRestaurant(restaurant);
		
		//assert
		assertEquals(HttpStatus.CREATED, addRestaurant.getStatusCode());
		assertEquals(restaurant, addRestaurant.getBody());
		
		//verify
		verify(restaurantService,times(1)).createRestaurant(restaurant);
	}
	
	@Test
	public void fetchAllDetails() {
		//Action
		List<RestaurantDto> asList = Arrays.asList(new RestaurantDto(1, "a", "b", "c", "d"),
				new RestaurantDto(2, "e", "f", "g", "h")
				);
		//act
		when(restaurantService.getAllRestarantList()).thenReturn(asList);
		ResponseEntity<List<RestaurantDto>> fetchAllDetails = restaurantController.fetchAllDetails();
		
		//assert
		assertEquals(HttpStatus.OK, fetchAllDetails.getStatusCode());
		
		assertEquals(fetchAllDetails.getBody().size(), asList.size());
		
		for (int i = 0; i < asList.size(); i++) {
			assertEquals(fetchAllDetails.getBody().get(i), asList.get(i));
		}
		
		//verify
		verify(restaurantService,times(1)).getAllRestarantList();
	}
	
	@Test
	public void fetchAllDetailsByIdTest() {
		//Action
		int mockId=1;
		RestaurantDto restaurantDto=new RestaurantDto(1, "a", "b", "c", "d");
	
		//Act
		when(restaurantService.getAllRestarantById(mockId)).thenReturn(new ResponseEntity<RestaurantDto>(restaurantDto, HttpStatus.OK));
		
		ResponseEntity<RestaurantDto> fetchAllDetails = restaurantController.fetchAllDetails(mockId);
		
		//assert
		assertEquals(HttpStatus.OK, fetchAllDetails.getStatusCode());
		assertEquals(restaurantDto, fetchAllDetails.getBody());
		
		//verify
		verify(restaurantService,times(1)).getAllRestarantById(mockId);
	}
}
