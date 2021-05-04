package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.mybatis.dao.ExpenseGroupMapper;
import lt.rokas.uzd1.mybatis.dao.ExpenseGroupTagMapper;
import lt.rokas.uzd1.mybatis.model.ExpenseGroup;
import lt.rokas.uzd1.mybatis.model.ExpenseGroupTag;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Model
public class MyBatisExpenseGroups {

    @Inject
    private ExpenseGroupMapper expenseGroupMapper;

    @Inject
    private ExpenseGroupTagMapper expenseGroupTagMapper;

    @Getter
    @Setter
    private List<ExpenseGroup> allExpenseGroups;

    @Getter
    @Setter
    private ExpenseGroup expenseGroupToCreate = new ExpenseGroup();

    @Getter
    @Setter
    private List<ExpenseGroupTag> allExpenseGroupTags;

    @PostConstruct
    public void init() {
        expenseGroupToCreate.setDate(new Date());
        loadAllExpenseGroups();
        loadAllExpenseGroupsTags();
    }
    @Transactional
    public String createGroup() {
        expenseGroupMapper.insert(expenseGroupToCreate);
        expenseGroupToCreate.getExpenseGroupTags().forEach(tag -> expenseGroupMapper.addGroupTag(expenseGroupToCreate, tag));
        return "expenses?faces-redirect=true&groupId=" + expenseGroupToCreate.getId();
    }

    private void loadAllExpenseGroups() {
        this.allExpenseGroups = expenseGroupMapper.selectAll();
    }

    private void loadAllExpenseGroupsTags() {
        this.allExpenseGroupTags = expenseGroupTagMapper.selectAll();
    }
}
