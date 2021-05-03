package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.mybatis.dao.ExpensegroupMapper;
import lt.rokas.uzd1.mybatis.dao.ExpensegrouptagMapper;
import lt.rokas.uzd1.mybatis.model.Expensegroup;
import lt.rokas.uzd1.mybatis.model.Expensegrouptag;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Model
public class MyBatisExpenseGroups {

    @Inject
    private ExpensegroupMapper expenseGroupMapper;

    @Inject
    private ExpensegrouptagMapper expensegrouptagMapper;

    @Getter
    @Setter
    private List<Expensegroup> allExpenseGroups;

    @Getter
    @Setter
    private Expensegroup expenseGroupToCreate = new Expensegroup();

    @Getter
    @Setter
    private List<Expensegrouptag> allExpenseGroupTags;

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
        return "/myBatis/groups?faces-redirect=true";
    }

    private void loadAllExpenseGroups() {
        this.allExpenseGroups = expenseGroupMapper.selectAll();
    }

    private void loadAllExpenseGroupsTags() {
        this.allExpenseGroupTags = expensegrouptagMapper.selectAll();
    }
}
