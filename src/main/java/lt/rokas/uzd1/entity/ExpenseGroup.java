package lt.rokas.uzd1.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "ExpenseGroup.findAll", query = "select e from ExpenseGroup as e order by e.date ASC")
})
@Getter @Setter
public class ExpenseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ExpenseGroupTag> expenseGroupTags;

    @OneToMany(mappedBy = "expenseGroup")
    private List<Expense> expenses;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @NotNull
    private String name;

    private String description;

    @Formula(value = "(Select SUM(e.cost) from Expense e where e.expenseGroup_ID = id)")
    private Double totalCost;
}
