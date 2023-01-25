package com.lawencon.e.learning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attachment_theory")
public class AttachmentTheory extends BaseModel {
	@ManyToOne 
	@JoinColumn(name = "file_id" ,nullable = false)
	private File fileId;
	
	@ManyToOne 
	@JoinColumn(name = "theory_id", nullable = false)
	private Theory theoryId;

	public File getFileId() {
		return fileId;
	}

	public void setFileId(File fileId) {
		this.fileId = fileId;
	}

	public Theory getTheoryId() {
		return theoryId;
	}

	public void setTheoryId(Theory theoryId) {
		this.theoryId = theoryId;
	}
}
