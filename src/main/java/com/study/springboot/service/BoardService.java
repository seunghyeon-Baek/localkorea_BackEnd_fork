package com.study.springboot.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.api.request.CreateAndEditBoardRequest;
import com.study.springboot.api.request.CreateAndEditLocalFestivalRequest;
import com.study.springboot.api.request.CreateAndEditLocalFoodsRequest;
import com.study.springboot.api.request.CreateAndEditMemberRequest;
import com.study.springboot.api.response.BoardDetail;
import com.study.springboot.api.response.BoardList;
import com.study.springboot.api.response.LocalFestivalsDetail;
import com.study.springboot.api.response.LocalFestivalsList;
import com.study.springboot.api.response.LocalFoodsDetail;
import com.study.springboot.api.response.LocalFoodsList;
import com.study.springboot.api.response.MemberDetail;
import com.study.springboot.api.response.MemberList;
import com.study.springboot.entity.Board;
import com.study.springboot.entity.LocalFestivals;
import com.study.springboot.entity.LocalFoods;
import com.study.springboot.entity.Member;
import com.study.springboot.entity.category.BoardCategory;
import com.study.springboot.repository.BoardRepository;
import com.study.springboot.repository.LocalFestivalsRepository;
import com.study.springboot.repository.LocalFoodsRepository;
import com.study.springboot.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	// 게시글 전체 조회
//	public List<BoardList> findAllBoard(){
//		List<Board> boards = boardRepository.findAll();
//		return boards.stream().map((board) -> BoardList.builder()
//							.title(board.getTitle())
//							.content(board.getContent())
//							.viewCnt(board.getViewCnt())
//							.regDate(ZonedDateTime.now())
//							.updateDate(ZonedDateTime.now())
////							.boardCno(board.getCno())
////							.locationCno(board.getLocno())
//							.build()
//							).toList();
//	}
	
	// 관광지 추천 게시글 조회
	@Transactional
	public List<BoardList> findByTourisSpot() {
	    // BoardCategory 객체 생성 및 초기화
	    BoardCategory category = BoardCategory.builder().cno(1L).build();
	    
	    // 해당 카테고리로 게시글 조회
	    List<Board> boards = boardRepository.findByCno(category);
	    
	    // 조회된 게시글을 BoardList로 변환하여 반환
	    return boards.stream()
	            .map(board -> BoardList.builder()
	            		.bno(board.getBno())
	                    .title(board.getTitle())
	                    .content(board.getContent())
	                    .viewCnt(board.getViewCnt())
	                    .regDate(ZonedDateTime.now())
	                    .updateDate(ZonedDateTime.now())	            
	                    .boardCno(board.getCno().getCno())
	                    .locationCno(board.getLocno().getLocno())
	                    .build())
	            .collect(Collectors.toList());
	}
	
	
//	@Transactional
//	public Board insertBoard(CreateAndEditBoardRequest request) {
//		Board board = Board.builder()
//							.title(request.getTitle())
//							.content(request.getContent())
//							.viewCnt(request.getViewCnt())
//							.regDate(ZonedDateTime.now())
//							.updateDate(ZonedDateTime.now())
//							.build();
//		boardRepository.save(board);
//		
//		return board;
//	}
//	
//	
//	
//	@Transactional
//	public BoardDetail findById(Long bno) {
//		Board board = boardRepository.findById(bno).orElseThrow();
//		
//		return BoardDetail.builder()
//									.title(board.getTitle())
//									.content(board.getContent())
//									.viewCnt(board.getViewCnt())
//									.regDate(ZonedDateTime.now())
//									.updateDate(ZonedDateTime.now())
//									.build();
//	}
//	
//	@Transactional
//	public void editBoard(Long bno, CreateAndEditBoardRequest request) {
//		Board board = boardRepository.findById(bno)
//				.orElseThrow(() -> new RuntimeException("Known LocalFood"));
//		board.changeBoard(request.getTitle(), request.getContent());
//		boardRepository.save(board);
//		
//	}
//	
//	@Transactional
//	public void deleteBoard(Long bno) {
//		Board board = boardRepository.findById(bno).orElseThrow();
//		boardRepository.delete(board);
//	}

}