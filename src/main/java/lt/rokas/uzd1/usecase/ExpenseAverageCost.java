package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;
import lt.rokas.uzd1.service.AverageExpenseGroupCost;

import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Named
@SessionScoped
public class ExpenseAverageCost  implements Serializable {

    @Inject
    AverageExpenseGroupCost averageExpenseGroupCost;

    Future<Double> averageCost;

    @Inject
    ExpenseGroupDao expenseGroupDao;

    public String getAverageCost() {
        System.out.println("Waiting for cost");
        try {
            if (averageCost == null) {
                return "Not calculated";
            }
            else if (!averageCost.isDone()) {
                return "Calculating";
            }
            return averageCost.get().toString();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FacesException(e);
        } catch (ExecutionException e) {
            throw new FacesException(e);
        }
    }

    public Boolean getAverageCostDone() {
        return averageCost != null &&  averageCost.isDone();
    }

    public void waitForAverageCost(AjaxBehaviorEvent event) {
        if (this.averageCost == null)
            this.averageCost = CompletableFuture.supplyAsync(() -> averageExpenseGroupCost.getAverageCost(expenseGroupDao.loadAll()));
        try {
            averageCost.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FacesException(e);
        } catch (ExecutionException e) {
            throw new FacesException(e);
        }
    }

}
