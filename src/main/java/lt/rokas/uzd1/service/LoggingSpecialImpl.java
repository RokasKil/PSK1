package lt.rokas.uzd1.service;

import javax.enterprise.inject.Specializes;

@Specializes
public class LoggingSpecialImpl extends LoggingImpl {
    @Override
    public void log(String message) {
        super.log("[Special]" + message);
    }
}