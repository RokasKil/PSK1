package lt.rokas.uzd1.decorator;

import lt.rokas.uzd1.service.Logging;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class LoggingDecorator implements Logging {
    @Inject
    @Delegate
    @Any
    Logging logging;

    @Override
    public void log(String message) {
        logging.log("[Decorator]" + message);
    }
}
