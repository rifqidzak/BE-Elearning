package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_collection_quiz", uniqueConstraints = @UniqueConstraint(name = "tb_collection_quiz_ck", columnNames = {
		"student_id", "schedule_id" }))
public class CollectionQuiz extends BaseModel {
	@Column(name = "note")
	private String note;
	
	@Column(name = "score", length = 8)
	private Float score;
	
	@ManyToOne 
	@JoinColumn(name = "schedule_id" ,nullable = false)
	private ScheduleQuiz scheduleId;
	
	@ManyToOne 
	@JoinColumn(name = "student_id")
	private User userId;
	
	@ManyToOne 
	@JoinColumn(name = "file_id" ,nullable = false)
	private File fileId;
	
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
	public ScheduleQuiz getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(ScheduleQuiz scheduleId) {
		this.scheduleId = scheduleId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public File getFileId() {
		return fileId;
	}
	public void setFileId(File fileId) {
		this.fileId = fileId;
	}
}
