package lt.rokas.uzd1.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

@ApplicationScoped
@Default
public class Logging {

    public void log(String message) {
        System.out.println("[Logging]" + message);
    }
}
