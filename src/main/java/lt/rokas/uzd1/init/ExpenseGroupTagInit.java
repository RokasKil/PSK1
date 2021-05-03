package lt.rokas.uzd1.init;

import lt.rokas.uzd1.entity.ExpenseGroupTag;
import lt.rokas.uzd1.persistence.ExpenseGroupTagDao;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Startup
@Singleton
public class ExpenseGroupTagInit {
    @Inject
    ExpenseGroupTagDao expenseGroupTagDao;

    private Logger logger = Logger.getLogger(ExpenseGroupTagInit.class);

    @PostConstruct
    @Transactional
    public void init() {
        //Labai blogas sprendimas bet mažai tags so it's fine for now
        List<String> tagNames = expenseGroupTagDao.loadAll().stream().map(ExpenseGroupTag::getName).collect(Collectors.toList());
        Stream.of(
                "Entertainment",
                "Food",
                "Pay",
                "Bills",
                "Shopping"
        ).forEach(tag -> {
            try {
                if(!tagNames.contains(tag)) {
                    expenseGroupTagDao.update(new ExpenseGroupTag(tag));

                }
            }
            catch(Exception e) {
                logger.info("Failed to persist tag: " + tag);
            }
        });
    }
}