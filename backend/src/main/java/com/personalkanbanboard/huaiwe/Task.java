package com.personalkanbanboard.huaiwe;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
	private long id;
	@NotBlank
	private String title;
	private int status;
	@NotBlank
	private String description;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dueDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date completionDate;
	
//	public Task(
//			@JsonProperty("id") long id,
//			@JsonProperty("title") String title,
//			@JsonProperty("status") int status,
//			@JsonProperty("description") String description,
//			@JsonProperty("dueDate") Date dueDate,
//			@JsonProperty("completionDate") Date completionDate) {
//		this.id = id;
//		this.title = title;
//		this.status = status;
//		this.description = description;
//		this.dueDate = dueDate;
//		this.completionDate = completionDate;
//	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
