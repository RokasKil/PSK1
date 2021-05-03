package lt.rokas.uzd1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Getter @Setter
public class ExpenseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    private List<ExpenseGroupTag> expenseGroupTags;

    @OneToMany
    private List<Expense> expenses;

    private Date date;

    private String name;

    private String description;
}
