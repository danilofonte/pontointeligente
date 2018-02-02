package com.ponto.inteligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ponto.inteligente.api.enums.TypeEnum;

@Entity
@Table(name = "time")
public class Time implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874754659005543446L;

	private Long id;
	private Date date;
	private String description;
	private String location;
	private Date creationTime;
	private Date modifiedTime;
	private TypeEnum type;
	private Employee employee;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date", nullable=false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name="creation_time")
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name="modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}	

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable=false)
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@PrePersist
	public void prePersist() {
		Date currentDate = new Date();
		creationTime = currentDate;
		modifiedTime = currentDate;
	}
	
	@PreUpdate
	public void preUpdate() {
		modifiedTime = new Date();
	}

	@Override
	public String toString() {
		return "Time [id=" + id + ", date=" + date + ", description=" + description + ", location=" + location
				+ ", creationTime=" + creationTime + ", modifiedTime=" + modifiedTime + ", type=" + type + ", employee="
				+ employee + "]";
	}


}
