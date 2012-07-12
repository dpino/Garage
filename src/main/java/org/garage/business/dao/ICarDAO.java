package org.garage.business.dao;

import java.util.List;

import org.garage.business.entities.Car;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public interface ICarDAO extends IGenericDAO<Car, Long> {

	List<Car> getAll();

	boolean exists(Car car);
	
}
