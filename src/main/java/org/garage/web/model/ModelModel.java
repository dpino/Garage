package org.garage.web.model;

import java.util.List;

import org.garage.business.dao.IModelDAO;
import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;
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
public class ModelModel implements IModelModel {

	@Autowired
	private IModelDAO modelDAO;
	
	private Model model;
	
	@Override
	@Transactional(readOnly = true)
	public List<Model> getAll() {
		return modelDAO.getAll();
	}

	@Override
	public void setModel(Model car) {
		this.model = car;
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	@Transactional
	public void save() throws DuplicateName {
		if (model.getId() == null && modelDAO.exists(model)) {
			throw new DuplicateName();
		}
		if (!model.getName().isEmpty() && !model.getTrademark().isEmpty()) {
			modelDAO.save(model);
		}
	}

	@Override
	public void prepareForCreate() {
		model = new Model();
	}	

	@Override
	@Transactional(readOnly = true)
	public void prepareForEdit(Long id) throws InstanceNotFoundException {
		model = modelDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Model car) throws InstanceNotFoundException {
		modelDAO.remove(car.getId());
	}	
	
}
