package org.garage.web.controllers;

import java.io.IOException;
import java.util.List;

import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;
import org.garage.web.common.Notificator;
import org.garage.web.common.OnlyOneVisible;
import org.garage.web.common.Util;
import org.garage.web.model.IModelModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.api.Window;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class ModelCRUDController extends GenericForwardComposer {

    private Label notificationMessage;

    private Notificator notificator;

    private Window listWindow;

    private Window editWindow;
	
    private IModelModel modelModel;
    
	private OnlyOneVisible visibility;

    
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this);
		notificator = Notificator.create(notificationMessage);
	}    
    
    public List<Model> getModels() {
    	return modelModel.getAll();
    }
    
    
    public void openCreateForm() {
    	modelModel.prepareForCreate();
    	showEditWindow("New Model");
    }

    private void showEditWindow(String title) {
    	setTitle(title);
    	Util.reloadBindings(editWindow);
    	getVisibility().showOnly(editWindow);
	}

	private void setTitle(String title) {
		Tab tab = (Tab) editWindow.getFellowIfAny("tab");
		if (tab != null) {
			tab.setLabel(title);
		}
	}
	
	public void saveAndExit() throws IOException {
		try {
			modelModel.save();
			cancel();
		} catch (DuplicateName e) {
			notificator.error("Duplicated Model");
		}
	}
	
	public void saveAndContinue() {		
		try {
			modelModel.save();
			modelModel.prepareForCreate();
			Util.reloadBindings(editWindow);
			notificator.info("Model added");
		} catch (DuplicateName e) {
			notificator.error("Duplicated Model");
		}
	}
	
	public void cancel() throws IOException {
		Executions.sendRedirect("/models/models.zul");
	}
	
	public Model getModel() {
    	return modelModel.getModel();
    }
	
    private OnlyOneVisible getVisibility() {
        if (visibility == null) {
            visibility = new OnlyOneVisible(listWindow, editWindow);
        }
        return visibility;
    }

	public void openEditForm(Model model) {
    	try {
			modelModel.prepareForEdit(model.getId());
			showEditWindow(String.format("Edit Model: %s", model));
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
	public void delete(Model model) throws InstanceNotFoundException {
		modelModel.delete(model);
		Util.reloadBindings(listWindow);
	}
    
}
