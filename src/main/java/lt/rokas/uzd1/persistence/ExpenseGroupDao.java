package lt.rokas.uzd1.persistence;

import lt.rokas.uzd1.entity.ExpenseGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ExpenseGroupDao {
    @Inject
    private EntityManager em;

    public void persist(ExpenseGroup group){
        this.em.persist(group);
    }

    public ExpenseGroup findOne(Integer id) {
        return em.find(ExpenseGroup.class, id);
    }

    public ExpenseGroup update(ExpenseGroup group){
        return em.merge(group);
    }

    public List<ExpenseGroup> loadAll() {
        return em.createNamedQuery("ExpenseGroup.findAll", ExpenseGroup.class).getResultList();
    }
}