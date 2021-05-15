package lt.rokas.uzd1.interceptor;

import lt.rokas.uzd1.service.Logging;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable {
    @Inject
    private Logging logger;

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        logger.log("Called method: " + context.getMethod().getName());
        return context.proceed();
    }
}
