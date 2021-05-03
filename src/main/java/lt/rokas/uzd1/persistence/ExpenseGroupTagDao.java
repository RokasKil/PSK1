package lt.rokas.uzd1.persistence;

import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class ExpenseGroupTagDao {
    @Inject
    private EntityManager em;

    public void persist(ExpenseGroupTag expenseGroupTag){
        this.em.persist(expenseGroupTag);
    }

    public ExpenseGroupTag findOne(Integer id) {
        return em.find(ExpenseGroupTag.class, id);
    }
    public ExpenseGroupTag getReference(Integer id) {
        return em.getReference(ExpenseGroupTag.class, id);
    }

    public ExpenseGroupTag update(ExpenseGroupTag expenseGroupTag){
        return em.merge(expenseGroupTag);
    }

    public List<ExpenseGroupTag> loadAll() {
        return em.createNamedQuery("ExpenseGroupTag.findAll", ExpenseGroupTag.class).getResultList();
    }
}
