package com.lawencon.e.learning.dto.schedulequiz;

import java.time.LocalDateTime;

public class ScheduleQuizDataDto {
	private Long id;
	private Integer ver;
	private String quizName;
	private LocalDateTime startQuiz;
	private LocalDateTime finishQuiz;
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
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public LocalDateTime getStartQuiz() {
		return startQuiz;
	}
	public void setStartQuiz(LocalDateTime startQuiz) {
		this.startQuiz = startQuiz;
	}
	public LocalDateTime getFinishQuiz() {
		return finishQuiz;
	}
	public void setFinishQuiz(LocalDateTime finishQuiz) {
		this.finishQuiz = finishQuiz;
	}
	
	
	
}
