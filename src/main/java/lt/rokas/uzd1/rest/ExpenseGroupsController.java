package lt.rokas.uzd1.rest;

import lombok.Getter;
import lombok.Setter;
import lt.rokas.uzd1.entity.Expense;
import lt.rokas.uzd1.entity.ExpenseGroup;
import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.persistence.ExpenseDao;
import lt.rokas.uzd1.persistence.ExpenseGroupDao;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;
import lt.rokas.uzd1.rest.contract.ExpenseGroupDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/expenseGroups")
public class ExpenseGroupsController {
    @Inject
    @Setter
    @Getter
    private ExpenseGroupDao expenseGroupDao;
    @Inject
    @Setter
    @Getter
    private ExpenseGroupTagDao expenseGroupTagDao;
    @Inject
    @Setter
    @Getter
    private ExpenseDao expenseDao;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        ExpenseGroup expenseGroup = expenseGroupDao.findOne(id);
        if (expenseGroup == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ExpenseGroupDto.fromEntity(expenseGroup)).build();
    }
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ExpenseGroupDto expenseGroupDto) {
        ExpenseGroup expenseGroup = new ExpenseGroup();
        expenseGroup.setName(expenseGroupDto.getName());
        expenseGroup.setDescription(expenseGroupDto.getDescription());
        try {
            expenseGroup.setDate(ExpenseGroupDto.dateFormat.parse(expenseGroupDto.getDate()));
        } catch (ParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (expenseGroupDto.getTags() != null) {
            expenseGroup.setExpenseGroupTags(expenseGroupDto.getTags()
                    .stream()
                    .map(expenseGroupTagDao::findOneByName)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
        }
        if (expenseGroupDto.getExpenses() != null) {
            expenseGroup.setExpenses(expenseGroupDto.getExpenses()
                    .stream()
                    .map(expenseDto -> {
                        Expense expense = new Expense();
                        expense.setName(expenseDto.getName());
                        expense.setCost(expenseDto.getCost());
                        return expense;
                    })
                    .collect(Collectors.toList())
            );
        }
        expenseGroupDao.persist(expenseGroup);
        if (expenseGroup.getExpenses() != null) {
            expenseGroup.getExpenses().forEach(expense -> {
                expense.setExpenseGroup(expenseGroup);
                expenseDao.persist(expense);
            });
        }
        expenseGroupDao.flush();
        expenseGroupDao.refresh(expenseGroup);
        return Response.ok(ExpenseGroupDto.fromEntity(expenseGroup)).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, ExpenseGroupDto expenseGroupDto) {
        try {
            ExpenseGroup expenseGroup = expenseGroupDao.findOne(id);
            expenseGroup.setName(expenseGroupDto.getName());
            expenseGroup.setDescription(expenseGroupDto.getDescription());
            try {
                expenseGroup.setDate(ExpenseGroupDto.dateFormat.parse(expenseGroupDto.getDate()));
            } catch (ParseException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            if (expenseGroupDto.getTags() != null) {
                expenseGroup.setExpenseGroupTags(expenseGroupDto.getTags()
                        .stream()
                        .map(expenseGroupTagDao::findOneByName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
            }
            expenseGroupDao.persist(expenseGroup);
            return Response.ok(ExpenseGroupDto.fromEntity(expenseGroup)).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
