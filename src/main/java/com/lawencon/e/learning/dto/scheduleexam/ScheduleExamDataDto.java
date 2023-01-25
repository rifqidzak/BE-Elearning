package com.lawencon.e.learning.dto.scheduleexam;

import java.time.LocalDateTime;

public class ScheduleExamDataDto {
	private Long id;
	private Integer ver;
	private String examName;
	private LocalDateTime startExam;
	private LocalDateTime finishExam;
	
	
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
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
	public LocalDateTime getStartExam() {
		return startExam;
	}
	public void setStartExam(LocalDateTime startExam) {
		this.startExam = startExam;
	}
	public LocalDateTime getFinishExam() {
		return finishExam;
	}
	public void setFinishExam(LocalDateTime finishExam) {
		this.finishExam = finishExam;
	}
}
