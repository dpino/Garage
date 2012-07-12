package org.garage.web.model;

import java.util.List;

import org.garage.business.entities.Car;
import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public interface ICarModel {

	List<Car> getAll();

	Car getCar();

	void prepareForCreate();

	void prepareForEdit(Long id) throws InstanceNotFoundException;	

	void save() throws DuplicateName;
	
	void setCar(Car car);

	void delete(Car car) throws InstanceNotFoundException;

	List<Model> getModels();
	
}
