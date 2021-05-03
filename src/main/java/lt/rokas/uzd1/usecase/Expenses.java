package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseDao;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class Expenses {

    @Inject
    ExpenseGroupDao expenseGroupDao;

    @Inject
    ExpenseDao expenseDao;

    @Getter
    @Setter
    private ExpenseGroup expenseGroup;

    @Getter @Setter
    private Expense expenseToCreate = new Expense();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer groupId = Integer.parseInt(requestParameters.get("groupId"));
        expenseGroup = expenseGroupDao.findOne(groupId);
    }

    @Transactional
    public String createExpense() {
        expenseToCreate.setExpenseGroup(expenseGroup);
        expenseDao.persist(expenseToCreate);
        return "expenses?faces-redirect=true&groupId=" + expenseGroup.getId();
    }

}
