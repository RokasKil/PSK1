package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.persistence.ExpenseDao;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class Expenses implements Serializable {

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

    @Transactional
    public String updateGroup() {
        try {
            expenseGroupDao.update(expenseGroup);
        } catch (OptimisticLockException e) {
            return "expenses?faces-redirect=true&groupId=" + expenseGroup.getId() + "&error=optimistic-lock-exception";
        }
        return "expenses?faces-redirect=true&groupId=" + expenseGroup.getId();
    }

}
