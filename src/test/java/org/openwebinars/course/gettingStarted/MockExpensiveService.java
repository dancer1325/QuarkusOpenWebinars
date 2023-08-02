package org.openwebinars.course.gettingStarted;

import io.quarkus.test.Mock;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Mock
public class MockExpensiveService implements ExpensiveService{
    @Override
    public int calculate() {
        return 5;
    }
}
