package org.garage.business.entities;

import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class Model extends BaseEntity {

	private String name;
	
	private String trademark;
	
	private Integer power;
	
	private Set<Car> cars;
	

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Car> getCars() {
		return Collections.unmodifiableSet(cars);
	}
	
	public void addCar(Car car) {
		car.setModel(this);
		cars.add(car);
	}
	
	public void removeCar(Car car) {
		car.setModel(null);
		cars.remove(car);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}
	
	public String toString() {
		return String.format("%s - %s", name, trademark);
	}
	
}