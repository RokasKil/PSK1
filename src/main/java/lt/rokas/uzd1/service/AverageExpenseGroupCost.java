package lt.rokas.uzd1.service;

import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class AverageExpenseGroupCost {
    @Inject
    ExpenseGroupDao expenseGroupDao;

    @Asynchronous
    public Future<Double> getAverageCost() {
        List<ExpenseGroup> allExpenseGroups = expenseGroupDao.loadAll();
        Double cost = 0.0;
        for (ExpenseGroup allExpenseGroup : allExpenseGroups) {
            cost += (allExpenseGroup.getTotalCost() == null ? 0 : allExpenseGroup.getTotalCost());
        }
        try {
            Thread.sleep(5000);
        }
        catch (Exception e) {

        }
        return new AsyncResult<Double>(cost / allExpenseGroups.size());
    }
}
