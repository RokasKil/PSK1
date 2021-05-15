package lt.rokas.uzd1.service;

import lt.rokas.uzd1.entity.ExpenseGroup;

import java.util.List;

public interface AverageExpenseGroupCost {
    Double getAverageCost(List<ExpenseGroup> expenseGroups);
}
