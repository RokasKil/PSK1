package lt.rokas.uzd1.converter;

import lt.rokas.uzd1.mybatis.dao.ExpenseGroupTagMapper;
import lt.rokas.uzd1.mybatis.model.ExpenseGroupTag;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MyBatisExpenseGroupTagConverter implements Converter {
    @Inject
    private ExpenseGroupTagMapper expenseGroupTagMapper;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return expenseGroupTagMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((ExpenseGroupTag) o).getId().toString();
    }
}
