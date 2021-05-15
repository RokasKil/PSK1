package lt.rokas.uzd1.service;

import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@ApplicationScoped
public class AverageExpenseGroupCostImpl implements AverageExpenseGroupCost, Serializable {
    public Double getAverageCost(List<ExpenseGroup> expenseGroups) {
        Double cost = 0.0;
        for (ExpenseGroup allExpenseGroup : expenseGroups) {
            cost += (allExpenseGroup.getTotalCost() == null ? 0 : allExpenseGroup.getTotalCost());
        }
        try {
            Thread.sleep(5000);
        }
        catch (Exception e) {

        }
        return cost / expenseGroups.size();
    }
}
