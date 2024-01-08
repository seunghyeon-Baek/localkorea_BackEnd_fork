package com.study.springboot.api.request;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateAndEditBoardRequest {
	
	private String title;
    private String content;
//    private String viewCnt;
    private ZonedDateTime regDate; 
	private ZonedDateTime updateDate; 
//	private String id;
	private Member id;
	private Member userName;
	private Long boardCno; // 카테고리 번호
	private Long locationCno; // 지역 카테고리 번호
	private String location; // 지역 이름
//	private String filePath; // 파일업로드
//	private String uuid; // 파일 uuid

//	List<fileUpload> postFile
// 	
	private List<MultipartFile> files; // 첨부파일 
	
}
