package com.excilys.formation.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.dao.ComputerDao;
import com.excilys.formation.om.Computer;

@Repository
@Transactional
public class ComputerDaoImpl implements ComputerDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Computer getComputerById(long computerId) {
		return (Computer) sessionFactory.getCurrentSession().get(
				Computer.class, computerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputersByName(String name, String s, String o,
			int p, int n) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				Computer.class);
		c.add(Restrictions.like("name", "%" + name + "%"));
		if (o.equals("desc")) {
			c.addOrder(Order.desc(s));
		} else if (o.equals("asc")) {
			c.addOrder(Order.asc(s));
		}
		c.setFirstResult(p).setMaxResults(n);
		return (List<Computer>) c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAllComputers() {
		return (List<Computer>) sessionFactory.getCurrentSession()
				.createCriteria(Computer.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAllComputersLimited(String s, String o, int p,
			int n) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				Computer.class);
		if (o.equals("desc")) {
			c.addOrder(Order.desc(s));
		} else if (o.equals("asc")) {
			c.addOrder(Order.asc(s));
		}
		c.setFirstResult(p).setMaxResults(n);
		return (List<Computer>) c.list();
	}

	@Override
	public long countComputers(String name) {
		return (long) sessionFactory.getCurrentSession()
				.createCriteria(Computer.class)
				.add(Restrictions.like("name", "%" + name + "%"))
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public void saveComputer(Computer computer) {
		sessionFactory.getCurrentSession().save(computer);
	}

	@Override
	public void updateComputer(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
	}

	@Override
	public void deleteComputerById(long computerId) {
		Computer computer = getComputerById(computerId);
		sessionFactory.getCurrentSession().delete(computer);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
