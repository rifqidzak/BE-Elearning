package com.lawencon.e.learning.dto.scheduletheory;

import java.time.LocalDateTime;

public class ScheduleTheoryDataDto {
	private Long id;
	private Integer ver;
	private String theoryName;
	private LocalDateTime startTheory;
	private LocalDateTime finishTheory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public String getTheoryName() {
		return theoryName;
	}
	public void setTheoryName(String theoryName) {
		this.theoryName = theoryName;
	}
	public LocalDateTime getStartTheory() {
		return startTheory;
	}
	public void setStartTheory(LocalDateTime startTheory) {
		this.startTheory = startTheory;
	}
	public LocalDateTime getFinishTheory() {
		return finishTheory;
	}
	public void setFinishTheory(LocalDateTime finishTheory) {
		this.finishTheory = finishTheory;
	}
}
