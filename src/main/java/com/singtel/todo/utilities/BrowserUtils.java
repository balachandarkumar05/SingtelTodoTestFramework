package com.singtel.todo.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public class BrowserUtils {

    public static WebDriver driver = null;
    DesiredCapabilities desiredCapabilities = null;

    public static WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public static List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public static String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    public static String getElementAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    public static void typeInElement(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public static void doubleClick(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).build().perform();
    }

    public WebDriver openBrowser() {
        if (driver == null) {
            createNewInstanceDriver();
        }
        driver = getDriver();
        return driver;
    }

    private static WebDriver getDriver() {
        return driver;
    }

    private void createNewInstanceDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("start-maximized");
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.merge(desiredCapabilities);
        driver = new ChromeDriver(chromeOptions);
    }
}
