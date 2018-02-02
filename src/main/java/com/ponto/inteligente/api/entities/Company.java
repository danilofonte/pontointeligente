package com.ponto.inteligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1277586386492886704L;

	private Long id;
	private String companyName;
	private String cnpj;
	private Date creationTime;
	private Date modifiedTime;
	private List<Employee> employees;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="company_name", nullable=false)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="cnpj", nullable=false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	@OneToMany(mappedBy="company", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
		return "Company [id=" + id + ", companyName=" + companyName + ", cnpj=" + cnpj + ", creationTime="
				+ creationTime + ", modifiedTime=" + modifiedTime + ", employees=" + employees + "]";
	}
	
	

}
