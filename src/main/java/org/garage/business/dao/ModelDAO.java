package org.garage.business.dao;

import java.util.List;

import org.garage.business.entities.Model;
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
public class ModelDAO extends GenericDAOHibernate<Model, Long> implements
		IModelDAO {	

	@Override
	public List<Model> getAll() {
		Criteria criteria = getSession().createCriteria(Model.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean exists(Model model) {
		Criteria criteria = getSession().createCriteria(Model.class);
		criteria.add(Restrictions.eq("name", model.getName()));
		criteria.add(Restrictions.eq("trademark", model.getTrademark()));
		return !criteria.list().isEmpty();
	}

}
