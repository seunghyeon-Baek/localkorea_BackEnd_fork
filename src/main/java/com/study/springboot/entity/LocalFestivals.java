package com.study.springboot.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Local_Festivals")
@Entity
public class LocalFestivals {
	
	@Id
    @SequenceGenerator (
            name = "festivalSequence",
            sequenceName = "Festival_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "festivalSequence")
    private Long festivalNo;

    private String name;
    private String location;
    private String content;
    private String schedule;
    private String viewCnt;
    @ManyToOne
	@JoinColumn(name="local_no", referencedColumnName = "localNo")
    private Location localNo;
	
	public void changeFestivalDetail(String name, String location, String content, String schedule, String viewCnt) {
		this.name = name;
		this.location = location;
		this.content = content;
		this.schedule = schedule;
		this.viewCnt = viewCnt;
	}

}