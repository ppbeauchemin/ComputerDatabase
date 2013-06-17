package com.excilys.formation.om;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

@Entity
@Table(name = "computer")
public class Computer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837900460007604101L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long computerId;
	@NotBlank
    @Length(min = 1, max = 80)
	@Column(name = "name")
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "introduced")
	private Date introduced;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "discontinued")
	private Date discontinued;
	@OneToOne(cascade = CascadeType.ALL)
	private Company company;

	public long getComputerId() {
		return computerId;
	}

	public void setComputerId(long computerId) {
		this.computerId = computerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + (int) (computerId ^ (computerId >>> 32));
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (computerId != other.computerId)
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.toString().equals(other.discontinued.toString()))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.toString().equals(other.introduced.toString()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [computerId=" + computerId + ", name=" + name
				+ ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

}
