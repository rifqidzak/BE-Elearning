package com.lawencon.e.learning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attachment_quiz")
public class AttachmentQuiz extends BaseModel {
	
	@ManyToOne 
	@JoinColumn(name = "file_id" ,nullable = false)
	private File fileId;
	
	@ManyToOne 
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quizId;

	public File getFileId() {
		return fileId;
	}

	public void setFileId(File fileId) {
		this.fileId = fileId;
	}

	public Quiz getQuizId() {
		return quizId;
	}

	public void setQuizId(Quiz quizId) {
		this.quizId = quizId;
	}

}
