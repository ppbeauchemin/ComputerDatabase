package com.excilys.formation.controllerUtils;

import java.beans.PropertyEditorSupport;
import java.sql.SQLException;

import com.excilys.formation.om.Company;
import com.excilys.formation.service.ComputerDatabaseService;

public class CompanyConverter extends PropertyEditorSupport {

	private ComputerDatabaseService computerDatabaseService;

	public CompanyConverter(ComputerDatabaseService computerDatabaseService) {
		this.computerDatabaseService = computerDatabaseService;
	}

	@Override
	public String getAsText() {
		Company company = (Company) getValue();
		if (company == null) {
			return null;
		}
		return String.valueOf(company.getCompanyId());
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.trim().isEmpty()) {
			int companyId = Integer.parseInt(text);
			Company company = null;
			try {
				company = computerDatabaseService.getCompanyById(companyId);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			setValue(company);
		}
	}

}
