package entities;

import entities.util.JsfUtil;
import entities.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("trabajaController")
@SessionScoped
public class TrabajaController implements Serializable {

    private Trabaja current;
    private DataModel items = null;
    @EJB
    private entities.TrabajaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TrabajaController() {
    }

    public Trabaja getSelected() {
        if (current == null) {
            current = new Trabaja();
            current.setTrabajaPK(new entities.TrabajaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private TrabajaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Trabaja) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Trabaja();
        current.setTrabajaPK(new entities.TrabajaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getTrabajaPK().setRFCEmpresa(current.getEmpresa().getRfc());
            current.getTrabajaPK().setCurp(current.getEmpleado().getCurp());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrabajaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Trabaja) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getTrabajaPK().setRFCEmpresa(current.getEmpresa().getRfc());
            current.getTrabajaPK().setCurp(current.getEmpleado().getCurp());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrabajaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Trabaja) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TrabajaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Trabaja getTrabaja(entities.TrabajaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Trabaja.class)
    public static class TrabajaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrabajaController controller = (TrabajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trabajaController");
            return controller.getTrabaja(getKey(value));
        }

        entities.TrabajaPK getKey(String value) {
            entities.TrabajaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.TrabajaPK();
            key.setRFCEmpresa(values[0]);
            key.setCurp(values[1]);
            return key;
        }

        String getStringKey(entities.TrabajaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getRFCEmpresa());
            sb.append(SEPARATOR);
            sb.append(value.getCurp());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Trabaja) {
                Trabaja o = (Trabaja) object;
                return getStringKey(o.getTrabajaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Trabaja.class.getName());
            }
        }

    }

}
