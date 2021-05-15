package lt.rokas.uzd1.service;

import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Alternative
@ApplicationScoped
public class FastAverageExpenseGroupCostImpl  implements AverageExpenseGroupCost, Serializable {
    @Asynchronous
    public Double getAverageCost(List<ExpenseGroup> expenseGroups) {
        Double cost = 0.0;
        for (ExpenseGroup allExpenseGroup : expenseGroups) {
            cost += (allExpenseGroup.getTotalCost() == null ? 0 : allExpenseGroup.getTotalCost());
        }
        return cost / expenseGroups.size();
    }
}
