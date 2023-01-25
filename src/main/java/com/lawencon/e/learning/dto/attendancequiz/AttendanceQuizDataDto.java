package com.lawencon.e.learning.dto.attendancequiz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceQuizDataDto {
	private Long id;
	private Integer ver;
	private String studentName;
	private LocalDate attendTime;
	private String quizName;
	private LocalDateTime startQuiz;
	private LocalDateTime finishQuiz;
	private Boolean approve;
	private Long scheduleId;
	private List<Long> fileId;
	
	public List<Long> getFileId() {
		return fileId;
	}
	public void setFileId(List<Long> fileId) {
		this.fileId = fileId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public LocalDate getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(LocalDate attendTime) {
		this.attendTime = attendTime;
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
	public Boolean getApprove() {
		return approve;
	}
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

}
