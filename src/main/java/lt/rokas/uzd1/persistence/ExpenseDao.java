package lt.rokas.uzd1.persistence;

import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ExpenseDao {
    @Inject
    private EntityManager em;

    public void persist(Expense expense){
        this.em.persist(expense);
    }

    public Expense findOne(Integer id){
        return em.find(Expense.class, id);
    }

    public Expense update(Expense expense){
        return em.merge(expense);
    }
}
