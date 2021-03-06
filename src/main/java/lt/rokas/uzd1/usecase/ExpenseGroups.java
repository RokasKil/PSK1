package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.interceptor.LoggedInvocation;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;
import lt.rokas.uzd1.service.AverageExpenseGroupCost;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Named
@ViewScoped
public class ExpenseGroups implements Serializable {

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
    @LoggedInvocation
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
