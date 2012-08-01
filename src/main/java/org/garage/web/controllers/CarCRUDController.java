package org.garage.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.garage.business.entities.Car;
import org.garage.business.entities.Model;
import org.garage.business.exceptions.DuplicateName;
import org.garage.business.exceptions.InstanceNotFoundException;
import org.garage.web.common.Notificator;
import org.garage.web.common.OnlyOneVisible;
import org.garage.web.common.Util;
import org.garage.web.model.ICarModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.api.Window;

/**
 * 
 * @author Diego Pino García <dpino@igalia.com>
 *
 */
public class CarCRUDController extends GenericForwardComposer {

	private OnlyOneVisible visibility;

    private Label notificationMessage;

    private Window listWindow;

    private Window editWindow;
	
    private ICarModel carModel;
    
    private Notificator notificator;
        
    
    private ModelRenderer modelRenderer = new ModelRenderer();
    
    
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setAttribute("controller", this);
		notificator = Notificator.create(notificationMessage);
	}    
        
    public void selectModel(SelectEvent event) {
    	Car car = carModel.getCar();
    	
    	List<Listitem> items = new ArrayList(event.getSelectedItems());
    	if (!items.isEmpty()) {
    		Listitem item = items.get(0);
        	car.setModel((Model) item.getValue());
    	}
    }

	public List<Car> getCars() {
    	return carModel.getAll();
    }
    
    
    public void openCreateForm() {
    	carModel.prepareForCreate();
    	showEditWindow("New Car");
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
			carModel.save();
			cancel();
		} catch (DuplicateName e) {
			notificator.error("Duplicated Car");
		}
	}
	
	public void saveAndContinue() {		
		try {
			carModel.save();
			carModel.prepareForCreate();
			Util.reloadBindings(editWindow);
			notificator.info("Car added");
		} catch (DuplicateName e) {
			notificator.error("Duplicated Car");
		}
	}
	
	public void cancel() throws IOException {
		Executions.sendRedirect("/cars/cars.zul");
	}
	
	public Car getCar() {
    	return carModel.getCar();
    }
	
    private OnlyOneVisible getVisibility() {
        if (visibility == null) {
            visibility = new OnlyOneVisible(listWindow, editWindow);
        }
        return visibility;
    }

	public void openEditForm(Car car) {
    	try {
			carModel.prepareForEdit(car.getId());
			showEditWindow(String.format("Edit Car: %s", car.getLicenseNumber()));
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}    	
    }
	
	public void delete(Car car) throws InstanceNotFoundException {
		carModel.delete(car);
		Util.reloadBindings(listWindow);
	}
    
	public List<Model> getModels() {
		return carModel.getModels();
	}
	
	public ModelRenderer getModelRenderer() {
		return modelRenderer;
	}
	
	private class ModelRenderer implements ListitemRenderer {		
				
		@Override
		public void render(Listitem item, Object data) throws Exception {
			Model model = (Model) data;
			
			item.setValue(data);
			item.appendChild(new Listcell(data.toString()));
			
			// Select car.model in list
			Car car = carModel.getCar();
			if (car != null && car.getModel() != null && car.getModel().getId().equals(model.getId())) {
				item.setSelected(true);				
			}
		}
	
	}
	
}
