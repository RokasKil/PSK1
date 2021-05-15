package lt.rokas.uzd1.rest.contract;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.Expense;

@Getter
@Setter
public class ExpenseDto {
    private Integer id;

    private String name;

    private Double cost;

    static public ExpenseDto fromEntity(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setName(expense.getName());
        expenseDto.setId(expense.getId());
        expenseDto.setCost(expense.getCost());
        return expenseDto;
    }
}
