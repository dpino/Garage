package org.garage.web.model;

import java.util.List;

import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public interface IModelModel {

	List<Model> getAll();

	Model getModel();

	void prepareForCreate();

	void prepareForEdit(Long id) throws InstanceNotFoundException;	

	void save() throws DuplicateName;
	
	void setModel(Model car);

	void delete(Model car) throws InstanceNotFoundException;
	
}
