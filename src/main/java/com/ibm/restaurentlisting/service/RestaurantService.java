package com.ibm.restaurentlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.restaurentlisting.dto.RestaurantDto;
import com.ibm.restaurentlisting.entity.Restaurant;
import com.ibm.restaurentlisting.mapper.RestaurantMapper;
import com.ibm.restaurentlisting.repo.RestaurantRepository;

@Service
public class RestaurantService {

	RestaurantRepository restaurantRepository;

	public RestaurantService(RestaurantRepository restaurantRepository) {
		super();
		this.restaurantRepository = restaurantRepository;
	}

//    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
//        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
//        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
//    }

	public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
		Restaurant restaurant = restaurantRepository
				.save(RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto));
				System.out.println(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant));
		return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant);
	}

	public List<RestaurantDto> getAllRestarantList() {
		List<RestaurantDto> map = this.restaurantRepository.findAll().stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant))
				.collect(Collectors.toList());
		return map;
	}

	public ResponseEntity<RestaurantDto> getAllRestarantById(int id) {
		Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
		if (restaurant.isPresent()) {
			return new ResponseEntity<RestaurantDto>(
					RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

}
