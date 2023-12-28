package com.study.springboot.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditLocalFestivalRequest;
import com.study.springboot.api.request.CreateAndEditLocalFoodsRequest;
import com.study.springboot.api.request.CreateAndEditMemberRequest;
import com.study.springboot.api.response.LocalFestivalsDetail;
import com.study.springboot.api.response.LocalFestivalsList;
import com.study.springboot.api.response.LocalFoodsDetail;
import com.study.springboot.api.response.LocalFoodsList;
import com.study.springboot.api.response.MemberDetail;
import com.study.springboot.api.response.MemberList;
import com.study.springboot.entity.LocalFestivals;
import com.study.springboot.entity.LocalFoods;
import com.study.springboot.entity.Location;
import com.study.springboot.entity.Member;
import com.study.springboot.repository.LocalFestivalsRepository;
import com.study.springboot.repository.LocalFoodsRepository;
import com.study.springboot.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalFestivalsService {
	
	@Autowired
	private LocalFestivalsRepository festivalsRepository;
	
	@Transactional
	public LocalFestivals insertLocalFestivals(CreateAndEditLocalFestivalRequest request) {
		LocalFestivals festivals = LocalFestivals.builder()
							.name(request.getName())
							.location(request.getLocation())
							.content(request.getContent())
							.schedule(request.getSchedule())
							.viewCnt(request.getViewCnt())
							.localNo(request.getLocalNo())
							.build();
		festivalsRepository.save(festivals);
		
		return festivals;
	}
	
	@Transactional
	public List<LocalFestivalsList> findAllFestivals(){
		List<LocalFestivals> festivals = festivalsRepository.findAll();
		return festivals.stream().map((festival) -> LocalFestivalsList.builder()
							.festivalNo(festival.getFestivalNo())
							.name(festival.getName())
							.location(festival.getLocation())
							.content(festival.getContent())
							.schedule(festival.getSchedule())
							.viewCnt(festival.getViewCnt())
							.localNo(festival.getLocalNo())
							.build()
							).toList();
	}
	
	@Transactional
	public LocalFestivalsDetail findById(Long festivalNo) {
		LocalFestivals festivals = festivalsRepository.findById(festivalNo).orElseThrow();
		
		return LocalFestivalsDetail.builder()
									.festivalNo(festivals.getFestivalNo())
									.name(festivals.getName())
									.location(festivals.getLocation())
									.content(festivals.getContent())
									.schedule(festivals.getSchedule())
									.viewCnt(festivals.getViewCnt())
									.localNo(festivals.getLocalNo())
									.build();
	}

	@Transactional
	public List<LocalFestivalsList> findByLocalNo(Location localNo) {
		List<LocalFestivals> festivals = festivalsRepository.findAllByLocalNo(localNo);
		
		return festivals.stream().map((festival) -> LocalFestivalsList.builder()
							.festivalNo(festival.getFestivalNo())
							.name(festival.getName())
							.location(festival.getLocation())
							.content(festival.getContent())
							.schedule(festival.getSchedule())
							.viewCnt(festival.getViewCnt())
							.localNo(festival.getLocalNo())
							.build()
							).toList();
	}
	
	@Transactional
	public void editLocalFestivals(Long festivalNo, CreateAndEditLocalFestivalRequest request) {
		LocalFestivals festivals = festivalsRepository.findById(festivalNo)
				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
		festivals.changeFestivalDetail(request.getName(), request.getLocation(), request.getContent(), request.getSchedule(), request.getViewCnt());
		festivalsRepository.save(festivals);
		
	}
	
	@Transactional
	public void deleteLocalFestivals(Long festivalNo) {
		LocalFestivals festivals = festivalsRepository.findById(festivalNo).orElseThrow();
		festivalsRepository.delete(festivals);
	}

}