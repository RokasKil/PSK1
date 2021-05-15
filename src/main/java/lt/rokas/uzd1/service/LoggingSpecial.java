package lt.rokas.uzd1.service;

import javax.enterprise.inject.Specializes;

@Specializes
public class LoggingSpecial extends Logging{
    @Override
    public void log(String message) {
        System.out.println("[Logging][Special]" + message);
    }
}