package lt.rokas.uzd1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="EXPENSEGROUP_ID")
    private ExpenseGroup expenseGroup;

    private String name;

    private Double cost;
}
