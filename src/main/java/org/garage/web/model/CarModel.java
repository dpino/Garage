package org.garage.web.model;

import java.util.List;

import org.garage.business.dao.ICarDAO;
import org.garage.business.dao.IModelDAO;
import org.garage.business.entities.Car;
import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CarModel implements ICarModel {

	@Autowired
	private ICarDAO carDAO;
	
	@Autowired
	private IModelDAO modelDAO;
	
	private Car car;
	
	@Override
	@Transactional(readOnly = true)
	public List<Car> getAll() {
		List<Car> cars = carDAO.getAll();
		initializeCars(cars);
		return cars;
	}

	/**
	 * Model is lazy, so I need to initialize it if I want to display model in
	 * the list of cars
	 * 
	 * @param cars
	 */
	private void initializeCars(List<Car> cars) {
		for (Car each: cars) {
			Hibernate.initialize(each.getModel());
		}
	}

	@Override
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public Car getCar() {
		return car;
	}

	@Override
	@Transactional
	public void save() throws DuplicateName {
		if (car.getId() == null && carDAO.exists(car)) {
			throw new DuplicateName();
		}
		if (!car.getLicenseNumber().isEmpty()) {
			carDAO.save(car);
		}
	}

	@Override
	public void prepareForCreate() {
		car = new Car();
	}	

	@Override
	@Transactional(readOnly = true)
	public void prepareForEdit(Long id) throws InstanceNotFoundException {
		car = carDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Car car) throws InstanceNotFoundException {
		carDAO.remove(car.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Model> getModels() {
		return modelDAO.getAll();
	}	
	
}