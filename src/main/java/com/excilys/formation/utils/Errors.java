package com.excilys.formation.utils;

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

}
