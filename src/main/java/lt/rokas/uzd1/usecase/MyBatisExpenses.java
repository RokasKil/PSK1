package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.mybatis.dao.ExpenseMapper;
import lt.rokas.uzd1.mybatis.dao.ExpenseGroupMapper;
import lt.rokas.uzd1.mybatis.model.Expense;
import lt.rokas.uzd1.mybatis.model.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseDao;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class MyBatisExpenses {

    @Inject
    ExpenseGroupDao expenseGroupDao;

    @Inject
    ExpenseDao expenseDao;

    @Inject
    ExpenseGroupMapper expenseGroupMapper;

    @Inject
    ExpenseMapper expenseMapper;

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
        expenseGroup = expenseGroupMapper.selectByPrimaryKey(groupId);
    }

    @Transactional
    public String createExpense() {
        expenseToCreate.setExpenseGroupId(expenseGroup.getId());
        expenseMapper.insert(expenseToCreate);
        return "expenses?faces-redirect=true&groupId=" + expenseGroup.getId();
    }

}
