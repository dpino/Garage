package org.garage.business.entities;


/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class Car extends BaseEntity {

	private String licenseNumber;
	
	private Integer kilometers;
	
	private Model model;
	

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Integer getKilometers() {
		return kilometers;
	}

	public void setKilometers(Integer kilometers) {
		this.kilometers = kilometers;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String toString() {
		return licenseNumber;
	}
	
}
