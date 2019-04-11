package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AfterBeforeRules implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(AfterBeforeRules.class);
    public static Map<Description, Long> testsElapses = new HashMap<>();
    private Long startTime;
    private Long timeExecuting;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                startTime = System.nanoTime();
                try {
                    base.evaluate();
                } finally {
                    timeExecuting = System.nanoTime() - startTime;
                    testsElapses.put(description, timeExecuting);
                    log.info("End test {}, time elapsed = {} nanoseconds", description.toString(),
                            timeExecuting);
                }
            }
        };
    }
}
