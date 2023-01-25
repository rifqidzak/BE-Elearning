package com.lawencon.e.learning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attachment_exam")
public class AttachmentExam extends BaseModel {
	@ManyToOne 
	@JoinColumn(name = "file_id" ,nullable = false)
	private File fileId;
	
	@ManyToOne 
	@JoinColumn(name = "exam_id" ,nullable = false)
	private Exam examId;

	public File getFileId() {
		return fileId;
	}

	public void setFileId(File fileId) {
		this.fileId = fileId;
	}

	public Exam getExamId() {
		return examId;
	}

	public void setExamId(Exam examId) {
		this.examId = examId;
	}

}
