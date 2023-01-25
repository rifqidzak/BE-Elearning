package com.lawencon.e.learning.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_attendance_theory", uniqueConstraints = @UniqueConstraint(name = "tb_attendance_theory_ck", columnNames = {
		"student_id", "schedule_id" }))
public class AttendanceTheory extends BaseModel {
	@Column(name = "approve", nullable = false)
	private Boolean approve;
	
	@ManyToOne 
	@JoinColumn(name = "student_id" ,nullable = false)
	private User studentId;
	
	@Column(name = "attend_time")
	private LocalDate attendTime;
	
	@ManyToOne 
	@JoinColumn(name = "study_class_detail_id" ,nullable = false)
	private StudyClassDetail studyClassDetailId;
	
	@ManyToOne 
	@JoinColumn(name = "schedule_id" ,nullable = false)
	private ScheduleTheory scheduleId;
	
	
	public Boolean getApprove() {
		return approve;
	}
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}
	public User getStudentId() {
		return studentId;
	}
	public void setStudentId(User studentId) {
		this.studentId = studentId;
	}
	public LocalDate getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(LocalDate attendTime) {
		this.attendTime = attendTime;
	}
	public StudyClassDetail getStudyClassDetailId() {
		return studyClassDetailId;
	}
	public void setStudyClassDetailId(StudyClassDetail studyClassDetailId) {
		this.studyClassDetailId = studyClassDetailId;
	}
	public ScheduleTheory getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(ScheduleTheory scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	

	
	
}
