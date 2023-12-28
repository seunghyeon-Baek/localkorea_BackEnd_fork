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

import com.study.springboot.api.request.CreateAndEditMemberRequest;
import com.study.springboot.api.request.EditMemberInfo;
import com.study.springboot.api.response.MemberDetail;
import com.study.springboot.api.response.MemberList;
import com.study.springboot.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberApi {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public List<MemberList> getMemberList(){
		return memberService.findAllMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberDetail getMember(
			@PathVariable(name="id") String id
			) {
		return memberService.findById(id);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> insertMember(
			@RequestBody CreateAndEditMemberRequest request
			){
		try {
			memberService.insertMember(request);
			return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error" + e.getMessage());
		}
	}
	
	@PutMapping("/mypage/{id}")
	public void editInfo(
			@PathVariable(name="id") String id,
			@RequestBody EditMemberInfo request
			) {
		memberService.editMemberInfo(id, request);
	}
	
	
	@PutMapping("/member/{id}")
	public void editMember(
			@PathVariable(name="id") String id,
			@RequestBody CreateAndEditMemberRequest request
			) {
		memberService.editMember(id, request);
	}
	
	@DeleteMapping("/member/{id}")
	public void deleteMember(
			@PathVariable(name="id") String id
			) {
		memberService.deleteMember(id);
	}

}
