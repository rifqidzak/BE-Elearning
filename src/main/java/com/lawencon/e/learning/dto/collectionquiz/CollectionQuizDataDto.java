package com.lawencon.e.learning.dto.collectionquiz;

import java.time.LocalDateTime;

public class CollectionQuizDataDto {
	private String note;
	private Float score;
	private String quizName;
	private LocalDateTime quizStart;
	private LocalDateTime quizFinish;
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

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public LocalDateTime getQuizStart() {
		return quizStart;
	}

	public void setQuizStart(LocalDateTime quizStart) {
		this.quizStart = quizStart;
	}

	public LocalDateTime getQuizFinish() {
		return quizFinish;
	}

	public void setQuizFinish(LocalDateTime quizFinish) {
		this.quizFinish = quizFinish;
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