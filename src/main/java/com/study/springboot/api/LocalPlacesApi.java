package com.study.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditLocalFestivalRequest;
import com.study.springboot.api.request.CreateAndEditLocalFoodsRequest;
import com.study.springboot.api.request.CreateAndEditLocalPlacesRequest;
import com.study.springboot.api.request.CreateAndEditMemberRequest;
import com.study.springboot.api.response.LocalFestivalsDetail;
import com.study.springboot.api.response.LocalFestivalsList;
import com.study.springboot.api.response.LocalFoodsDetail;
import com.study.springboot.api.response.LocalFoodsList;
import com.study.springboot.api.response.LocalPlacesDetail;
import com.study.springboot.api.response.LocalPlacesList;
import com.study.springboot.api.response.MemberDetail;
import com.study.springboot.api.response.MemberList;
import com.study.springboot.entity.Location;
import com.study.springboot.service.LocalFestivalsService;
import com.study.springboot.service.LocalFoodsService;
import com.study.springboot.service.LocalPlaceService;
import com.study.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocalPlacesApi {
	
	@Autowired
	private LocalPlaceService localPlaceService;
	
	@GetMapping("/localPlaces")
	public List<LocalPlacesList> getLocalPalcesList(){
		return localPlaceService.findAllPlaces();
	}
	
	@GetMapping("/localPlace/{placeNo}")
	public LocalPlacesDetail getPlace(
			@PathVariable(name="placeNo") Long placeNo
			) {
		return localPlaceService.findById(placeNo);
	}

	@GetMapping("/localPlaces/{localNo}")
	public List<LocalPlacesList> getPlaceByLocalNo(
			@PathVariable(name="localNo") Location localNo
			) {
		return localPlaceService.findByLocalNo(localNo);
	}
	
	@PostMapping("/localPlace")
	public ResponseEntity<String> insertLocalPlace(
			@RequestBody CreateAndEditLocalPlacesRequest request
			){
		try {
			localPlaceService.insertLocalPlaces(request);
			return ResponseEntity.ok("Data Input Completed");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/localPlace/{placeNo}")
	public void editLocalPlaces(
			@PathVariable(name="placeNo") Long placeNo,
			@RequestBody CreateAndEditLocalPlacesRequest request
			) {
		localPlaceService.editLocalPlaces(placeNo, request);
	}
	
	@DeleteMapping("/localPlace/{placeNo}")
	public void deletePlace(
			@PathVariable(name="placeNo") Long placeNo
			) {
		localPlaceService.deleteLocalPlaces(placeNo);
	}

}