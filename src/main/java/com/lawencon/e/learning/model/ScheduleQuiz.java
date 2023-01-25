package com.lawencon.e.learning.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "tb_schedule_quiz")
public class ScheduleQuiz extends BaseModel {
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@Column(name = "start_at")
	private LocalDateTime startAt;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@Column(name = "finish_on")
	private LocalDateTime finishOn;
	
	@ManyToOne 
	@JoinColumn(name = "tb_quiz_id", nullable = false)
	private Quiz quizId;

	
	public LocalDateTime getStartAt() {
		return startAt;
	}
	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}
	public LocalDateTime getFinishOn() {
		return finishOn;
	}
	public void setFinishOn(LocalDateTime finishOn) {
		this.finishOn = finishOn;
	}
	public Quiz getQuizId() {
		return quizId;
	}
	public void setQuizId(Quiz quizId) {
		this.quizId = quizId;
	}

}
