package lt.rokas.uzd1.rest.contract;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ExpenseGroupDto {
    private Integer id;

    private List<String> tags;

    private List<ExpenseDto> expenses;

    private String date;

    private String name;

    private String description;

    private Double totalCost;

    static public DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static public ExpenseGroupDto fromEntity(ExpenseGroup expenseGroup) {
        ExpenseGroupDto expenseGroupDto = new ExpenseGroupDto();
        expenseGroupDto.setName(expenseGroup.getName());
        expenseGroupDto.setDate(dateFormat.format(expenseGroup.getDate()));
        expenseGroupDto.setDescription(expenseGroup.getDescription());
        expenseGroupDto.setId(expenseGroup.getId());
        expenseGroupDto.setTotalCost(expenseGroup.getTotalCost());
        if(expenseGroup.getExpenseGroupTags() != null)
            expenseGroupDto.setTags(expenseGroup.getExpenseGroupTags().stream().map(ExpenseGroupTag::getName).collect(Collectors.toList()));
        if(expenseGroup.getExpenses() != null)
            expenseGroupDto.setExpenses(expenseGroup.getExpenses().stream().map(ExpenseDto::fromEntity).collect(Collectors.toList()));
        return expenseGroupDto;
    }
}
