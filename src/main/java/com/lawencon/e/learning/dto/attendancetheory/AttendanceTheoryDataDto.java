package com.lawencon.e.learning.dto.attendancetheory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceTheoryDataDto {
	private Long id;
	private Integer ver;
	private String studentName;
	private LocalDate attendTime;
	private String theoryName;
	private LocalDateTime startTheory;
	private LocalDateTime finishTheory;
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

	public String getTheoryName() {
		return theoryName;
	}

	public void setTheoryName(String theoryName) {
		this.theoryName = theoryName;
	}

	public LocalDateTime getStartTheory() {
		return startTheory;
	}

	public void setStartTheory(LocalDateTime startTheory) {
		this.startTheory = startTheory;
	}

	public LocalDateTime getFinishTheory() {
		return finishTheory;
	}

	public void setFinishTheory(LocalDateTime finishTheory) {
		this.finishTheory = finishTheory;
	}

	public Boolean getApprove() {
		return approve;
	}

	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

}
