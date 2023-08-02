package org.openwebinars.course.gettingStarted;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RealExpensiveService implements ExpensiveService{
    @Override
    public int calculate() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 100;
    }
}
