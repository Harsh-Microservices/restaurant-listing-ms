package com.ibm.restaurentlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ibm.restaurentlisting.dto.RestaurantDto;
import com.ibm.restaurentlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapperTest {
RestaurantMapperTest INSTANCE=Mappers.getMapper(RestaurantMapperTest.class);
	
	Restaurant mapRestaurantDtoToRestaurant(RestaurantDto restaurantDto);
	
	RestaurantDto mapRestaurantToRestaurantDto(Restaurant restaurant);
}
