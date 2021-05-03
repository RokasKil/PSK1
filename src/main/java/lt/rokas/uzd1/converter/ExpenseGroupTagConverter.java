package lt.rokas.uzd1.converter;

import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExpenseGroupTagConverter implements Converter {
    @Inject
    ExpenseGroupTagDao expenseGroupTagDao;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return expenseGroupTagDao.getReference(Integer.parseInt(id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((ExpenseGroupTag) o).getId().toString();
    }
}
