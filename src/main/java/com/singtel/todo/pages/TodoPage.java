package com.singtel.todo.pages;

import com.singtel.todo.utilities.BrowserUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodoPage {

    private WebDriver driver;

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By header = By.cssSelector(".header h1");
    public static final By newTodoTxtBox = By.className("new-todo");
    public static final By todoItems = By.className("todo");
    public static final By todoItemsCount = By.className("todo-count");
    public static final By footer = By.className("footer");
    public static final By footerLinks = By.cssSelector(".footer a");
    public static final By selectedFooterLink = By.cssSelector("a.selected");
    public static final By deleteTodoItems = By.className("destroy");
    public static final By checkTodoItems = By.className("toggle");
    public static final By todoItemsLabels = By.cssSelector(".todo label");
    public static final By editableTxt = By.className("edit");
    public static final By clearCompletedBtn = By.className("clear-completed");

    public String getHeaderText() {
        return BrowserUtils.getElementText(header);
    }

    public String getNewTodoItemPlaceHolder() {
        return BrowserUtils.getElementAttribute(newTodoTxtBox,"placeholder");
    }

    public void addNewTodoItem(String newTodoItem) {
        BrowserUtils.typeInElement(newTodoTxtBox, newTodoItem);
        BrowserUtils.getElement(newTodoTxtBox).sendKeys(Keys.ENTER);
    }

    public int getTodoItemsListSize() {
        return BrowserUtils.getElements(todoItems).size();
    }

    public String lastAddedTodoItemText() {
        return BrowserUtils.getElementText(todoItems);
    }

    public List<String> listOfAddedTodoItemText() {
        List<WebElement> todoItemsElements = BrowserUtils.getElements(todoItems);
        List<String> todoItemsTexts = new ArrayList<>();

        for(WebElement todoItem: todoItemsElements) {
            todoItemsTexts.add(todoItem.getText());
        }
        return todoItemsTexts;
    }

    public String getFooterItemsCountText() {
        return BrowserUtils.getElementText(todoItemsCount);
    }

    public List<String> getFooterLinksText() {
        List<WebElement> footerLinksElements = BrowserUtils.getElements(footerLinks);

        List<String> footerLinksText = new ArrayList<>();
        for(WebElement element: footerLinksElements) {
            footerLinksText.add(element.getText());
        }

        return footerLinksText;
    }

    public void addMultipleTodoItems(List<String> todoItemsList) {
        for(String todoItem: todoItemsList) {
            addNewTodoItem(todoItem);
        }
    }

    public String getSelectedLinkText() {
        return BrowserUtils.getElementText(selectedFooterLink);
    }

    public void clickOnLinkText(String linkTextToClick) {
        List<WebElement> footerLinksElements = BrowserUtils.getElements(footerLinks);
        for(WebElement element: footerLinksElements) {
            if(element.getText().equalsIgnoreCase(linkTextToClick)) {
                element.click();
                break;
            }
        }
    }

    public void deleteOnlyTodoItem() {
        BrowserUtils.getElement(todoItems).click();
        BrowserUtils.getElement(deleteTodoItems).click();
    }

    public void deleteBasedOnTodoText(String todoItemText) {
        int itemIndex = findIndexOfTodoListItem(todoItemText);
        BrowserUtils.getElements(todoItems).get(itemIndex).click();
        BrowserUtils.getElements(deleteTodoItems).get(itemIndex).click();
    }

    public boolean verifyFooterNotDisplaying() {
        return !BrowserUtils.getElement(footer).isDisplayed();
    }

    public void deleteMultipleTodoItems(List<String> todoItemsList) throws InterruptedException {
        for(String todoItem: todoItemsList) {
            deleteBasedOnTodoText(todoItem);
        }
    }

    public void markCompletedBasedOnTodoText(String todoItemText) throws InterruptedException {
        int itemIndex = findIndexOfTodoListItem(todoItemText);
        BrowserUtils.getElements(checkTodoItems).get(itemIndex).click();
        Thread.sleep(2000);
    }

    public boolean verifyTodoItemDisplay(String todoItemText) {
        return findIndexOfTodoListItem(todoItemText) >= 0;
    }

    private int findIndexOfTodoListItem(String todoItemText) {
        int index = -1;
        List<WebElement> todoItemsElements = BrowserUtils.getElements(todoItems);
        for(int i=0; i<todoItemsElements.size();i++) {
            if(todoItemsElements.get(i).getText().equalsIgnoreCase(todoItemText)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean verifyCompletedTodoItemStrikedThrough(String todoItemText) {
        int itemIndex = findIndexOfTodoListItem(todoItemText);
        String textDecoration = BrowserUtils.getElements(todoItemsLabels).get(itemIndex).getCssValue("text-decoration");
        return textDecoration.contains("line-through");
    }

    public boolean verifyCompletedTodoItemGrayedOut(String todoItemText) {
        int itemIndex = findIndexOfTodoListItem(todoItemText);
        String textColor = BrowserUtils.getElements(todoItemsLabels).get(itemIndex).getCssValue("color");
        String textColorInHex = Color.fromString(textColor).asHex();
        return textColorInHex.equalsIgnoreCase("#d9d9d9");
    }

    public void markCompleteMultipleTodoItems(List<String> todoItemsList) throws InterruptedException {
        for(String todoItem: todoItemsList) {
            markCompletedBasedOnTodoText(todoItem);
        }
    }

    public boolean verifyClearCompletedBtnDisplay() {
        return BrowserUtils.getElement(clearCompletedBtn).isDisplayed();
    }

    public void clickClearCompletedButton() {
        BrowserUtils.getElement(clearCompletedBtn).click();
    }

    public void updateTodoItem(String currentItem, String updateItem) throws InterruptedException {
        int currentItemIndex = findIndexOfTodoListItem(currentItem);
        BrowserUtils.doubleClick(BrowserUtils.getElements(todoItems).get(currentItemIndex));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerText='" + updateItem + "'", BrowserUtils.getElements(todoItemsLabels).get(currentItemIndex));
        BrowserUtils.getElement(newTodoTxtBox).sendKeys(Keys.ENTER);
    }
}
