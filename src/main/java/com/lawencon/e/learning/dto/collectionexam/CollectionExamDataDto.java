package com.lawencon.e.learning.dto.collectionexam;

import java.time.LocalDateTime;

public class CollectionExamDataDto {
	private String note;
	private Float score;
	private String examName;
	private LocalDateTime examStart;
	private LocalDateTime examFinish;
	private String studentName;
	private Long fileId;
	private Long id;
	private Integer ver;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public LocalDateTime getExamStart() {
		return examStart;
	}
	public void setExamStart(LocalDateTime examStart) {
		this.examStart = examStart;
	}
	public LocalDateTime getExamFinish() {
		return examFinish;
	}
	public void setExamFinish(LocalDateTime examFinish) {
		this.examFinish = examFinish;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
	
	
}