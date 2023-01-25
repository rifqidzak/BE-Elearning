package com.lawencon.e.learning.dto.collectionexam;

import javax.validation.constraints.NotNull;

public class CollectionExamUpdateReqDataDto {
	private String note;
	
	@NotNull(message = "Score Required")
	private Float score;
	
	@NotNull(message = "Collection Exam Not Found")
	private Long id;
	
	@NotNull(message = "Version Not Found")
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
