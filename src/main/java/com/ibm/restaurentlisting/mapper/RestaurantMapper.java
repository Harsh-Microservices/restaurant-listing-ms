package com.ibm.restaurentlisting.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ibm.restaurentlisting.dto.RestaurantDto;
import com.ibm.restaurentlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapper {
	RestaurantMapper INSTANCE=Mappers.getMapper(RestaurantMapper.class);
	
	Restaurant mapRestaurantDtoToRestaurant(RestaurantDto restaurantDto);
	
	RestaurantDto mapRestaurantToRestaurantDto(Restaurant restaurant);
}
