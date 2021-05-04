package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Model
public class ExpenseGroups {

    @Inject
    ExpenseGroupDao expenseGroupDao;

    @Inject
    ExpenseGroupTagDao expenseGroupTagDao;

    @Getter
    @Setter
    private ExpenseGroup expenseGroupToCreate = new ExpenseGroup();

    @Getter
    @Setter
    private List<ExpenseGroup> allExpenseGroups;

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
        //expenseGroupToCreate.getExpenseGroupTags().stream().forEach(x->System.out.println(x.getId() + " " + x.getName()));
        expenseGroupDao.persist(expenseGroupToCreate);
        return "expenses?faces-redirect=true&groupId=" + expenseGroupToCreate.getId();
    }

    private void loadAllExpenseGroups() {
        this.allExpenseGroups = expenseGroupDao.loadAll();
    }

    private void loadAllExpenseGroupsTags() {
        this.allExpenseGroupTags = expenseGroupTagDao.loadAll();
    }
}
