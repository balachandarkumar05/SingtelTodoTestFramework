package com.singtel.todo;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@addItems",
        glue = {"classpath:com.singtel.todo.steps"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void setUp() {
        //read properties and clear report folder if any
    }

    @AfterClass
    public static void tearDown() {
        //close DBConn and create reports folder
    }
}
