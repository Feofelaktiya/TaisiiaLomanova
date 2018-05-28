package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    private long initTime;

    @BeforeSuite
    public void beforeSuite() {
        initTime = System.currentTimeMillis();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Suite time is: " + (System.currentTimeMillis() - initTime));

    }
}
