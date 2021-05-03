package lt.rokas.uzd1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "ExpenseGroupTag.findAll", query = "select tag from ExpenseGroupTag as tag")
})
@Getter @Setter
public class ExpenseGroupTag {

    public ExpenseGroupTag() {

    }

    public ExpenseGroupTag(String name) {
        this.name = name;
    }

    public ExpenseGroupTag(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "expenseGroupTags")
    private List<ExpenseGroup> expenseGroups;

    @Column(unique = true, nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        System.out.println(o);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseGroupTag that = (ExpenseGroupTag) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
