package com.ponto.inteligente.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.NotEmpty;

public class TimeDto extends AbstractDto {
	
	private Optional<Long> id = Optional.empty();
	private String date;
	private String type;
	private String description;
	private String location;
	private Long employeeId;

	public TimeDto() {
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotEmpty(message = "Date cannot be empty.")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "TimeDto [id=" + id + ", date=" + date + ", type=" + type + ", description=" + description
				+ ", location=" + location + ", employeeId=" + employeeId + "]";
	}
	
	
}
