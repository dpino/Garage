package org.garage.business.dao;

import java.util.List;

import org.garage.business.entities.Car;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CarDAO extends GenericDAOHibernate<Car, Long> implements
		ICarDAO {	

	@Override
	public List<Car> getAll() {
		Criteria criteria = getSession().createCriteria(Car.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	public boolean exists(Car car) {
		Criteria criteria = getSession().createCriteria(Car.class);
		criteria.add(Restrictions.eq("licenseNumber", car.getLicenseNumber()));
		return !criteria.list().isEmpty();
	}

}
