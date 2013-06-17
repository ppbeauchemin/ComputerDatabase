package com.excilys.formation.utils;

import java.util.List;

import org.springframework.validation.FieldError;

public class Errors {
	private String name;
	private String introduced;
	private String discontinued;
	private boolean error;

	public Errors() {
		this.name = "";
		this.introduced = "";
		this.discontinued = "";
		this.error = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!name.isEmpty()) {
			this.setError(true);
		}
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		if (!introduced.isEmpty()) {
			this.setError(true);
		}
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		if (!discontinued.isEmpty()) {
			this.setError(true);
		}
		this.discontinued = discontinued;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setAllErrors(List<FieldError> fieldErrors) {
		for (FieldError fe : fieldErrors) {
			if (fe.getField().equals("name")) {
				this.setName(Var.ERROR);
			}
			if (fe.getField().equals("introduced")) {
				this.setIntroduced(Var.ERROR);
			}
			if (fe.getField().equals("discontinued")) {
				this.setDiscontinued(Var.ERROR);
			}
		}
	}

	@Override
	public String toString() {
		return "Errors [name=" + name + ", introduced=" + introduced
				+ ", discontinued=" + discontinued + ", error=" + error + "]";
	}
	
	

}
