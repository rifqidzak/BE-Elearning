package com.lawencon.e.learning.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "tb_schedule_theory")
public class ScheduleTheory extends BaseModel {
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@Column(name = "start_at")
	private LocalDateTime startAt;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@Column(name = "finish_on")
	private LocalDateTime finishOn;
	
	@ManyToOne 
	@JoinColumn(name = "tb_theory_id", nullable = false)
	private Theory theoryId;
	
	public Theory getTheoryId() {
		return theoryId;
	}
	public void setTheoryId(Theory theoryId) {
		this.theoryId = theoryId;
	}
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


}
