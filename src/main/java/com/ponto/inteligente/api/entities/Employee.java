package com.ponto.inteligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ponto.inteligente.api.enums.RoleEnum;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2561226304518811916L;

	private Long id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private BigDecimal hourValue;
	private Float workingHoursQty;
	private Float lunchHourQty;
	private RoleEnum role;
	private Date creationTime;
	private Date modifiedTime;
	private Company company;
	private List<Time> times;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "cpf", nullable=false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "hour_value", nullable=true)
	public BigDecimal getHourValue() {
		return hourValue;
	}
	
	@Transient
	public Optional<BigDecimal> getHourValueOpt() {
		return Optional.ofNullable(hourValue);
	}

	public void setHourValue(BigDecimal hourValue) {
		this.hourValue = hourValue;
	}

	@Column(name = "working_hours_qty", nullable=true)
	public Float getWorkingHoursQty() {
		return workingHoursQty;
	}
	
	@Transient
	public Optional<Float> getWorkingHoursQtyOpt() {
		return Optional.ofNullable(workingHoursQty);
	}

	public void setWorkingHoursQty(Float workingHoursQty) {
		this.workingHoursQty = workingHoursQty;
	}

	@Column(name = "lunch_hour_qty", nullable=true)
	public Float getLunchHourQty() {
		return lunchHourQty;
	}
	
	@Transient
	public Optional<Float> getLunchHourQtyOpt() {
		return Optional.ofNullable(lunchHourQty);
	}

	public void setLunchHourQty(Float lunchHourQty) {
		this.lunchHourQty = lunchHourQty;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable=false)
	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	@Column(name="creation_time", nullable=false)
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name="modified_time", nullable=false)
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@OneToMany(mappedBy="employee", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
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
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", cpf=" + cpf
				+ ", hourValue=" + hourValue + ", workingHoursQty=" + workingHoursQty + ", lunchHourQty=" + lunchHourQty
				+ ", role=" + role + ", creationTime=" + creationTime + ", modifiedTime=" + modifiedTime + ", company="
				+ company + ", times=" + times + "]";
	}

	
	

}
