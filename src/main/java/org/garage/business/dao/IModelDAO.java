package org.garage.business.dao;

import java.util.List;

import org.garage.business.entities.Model;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public interface IModelDAO extends IGenericDAO<Model, Long> {

	List<Model> getAll();

	boolean exists(Model car);
	
}
