package lt.rokas.uzd1.converter;

import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.mybatis.dao.ExpensegrouptagMapper;
import lt.rokas.uzd1.mybatis.model.Expensegrouptag;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MyBatisExpenseGroupTagConverter implements Converter {
    @Inject
    private ExpensegrouptagMapper expensegrouptagMapper;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        return expensegrouptagMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Expensegrouptag) o).getId().toString();
    }
}
