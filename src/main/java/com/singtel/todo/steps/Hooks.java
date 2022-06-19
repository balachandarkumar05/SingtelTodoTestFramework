package com.singtel.todo.steps;

import com.singtel.todo.utilities.BrowserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.concurrent.TimeUnit;

public class Hooks extends BrowserUtils {

    public static Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        Hooks.scenario = scenario;
        driver = openBrowser();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void afterScenario(Scenario scenario) {
        driver.quit();
        driver = null;
    }
}
