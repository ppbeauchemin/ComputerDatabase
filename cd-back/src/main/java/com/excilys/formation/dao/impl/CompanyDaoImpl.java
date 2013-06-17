package com.excilys.formation.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dao.CompanyDao;
import com.excilys.formation.om.Company;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Company getCompanyById(long companyId) {
		return (Company) sessionFactory.getCurrentSession().get(Company.class,
				companyId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAllCompanies() {
		return (List<Company>) sessionFactory.getCurrentSession()
				.createCriteria(Company.class).list();
	}

	@Override
	public void saveCompany(Company company) {
		sessionFactory.getCurrentSession().save(company);
	}

	@Override
	public void updateCompany(Company company) {
		sessionFactory.getCurrentSession().update(company);
	}

	@Override
	public void deleteCompanyById(long companyId) {
		Company company = getCompanyById(companyId);
		sessionFactory.getCurrentSession().delete(company);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
