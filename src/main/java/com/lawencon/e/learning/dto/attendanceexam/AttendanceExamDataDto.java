package com.lawencon.e.learning.dto.attendanceexam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceExamDataDto {
	private Long id;
	private Integer ver;
	private String studentName;
	private LocalDate attendTime;
	private String examName;
	private LocalDateTime startExam;
	private LocalDateTime finishExam;
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
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
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
	public Boolean getApprove() {
		return approve;
	}
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}
	
	
}
