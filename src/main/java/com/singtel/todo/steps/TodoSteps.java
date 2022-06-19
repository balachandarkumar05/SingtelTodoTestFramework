package com.singtel.todo.steps;

import com.singtel.todo.pages.TodoPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TodoSteps {

    WebDriver driver = Hooks.driver;
    private final TodoPage todoPage = new TodoPage(driver);

    @Given("^user launches Todo application$")
    public void userLaunchesTodoApplication() {
        String url = "https://todomvc.com/examples/vue/";
        driver.get(url);
    }

    @Then("I should see header as {string}")
    public void iShouldSeeHeaderAs(String expectedHdrTxt) {
        assert expectedHdrTxt.equalsIgnoreCase(todoPage.getHeaderText()) : "Header Text not matching";
    }

    @And("I should see text box with placeholder {string}")
    public void iShouldSeeTextBoxWithPlaceholder(String placeholderText) {
        assert placeholderText.equalsIgnoreCase(todoPage.getNewTodoItemPlaceHolder()) : "Placeholder Text not matching";
    }

    @When("I add todo item {string}")
    public void iAddTodoItem(String newTodoItem) {
        todoPage.addNewTodoItem(newTodoItem);
    }

    @Then("I should see todo list size as {int}")
    public void iShouldSeeSizeOfTodoListAs(int sizeOfList) {
        assert todoPage.getTodoItemsListSize() == sizeOfList : "Todo List Item size not matching";
    }

    @And("I should see footer displays number of todo items left")
    public void iShouldSeeFooterDisplaysNumberOfTodoItemsLeft() {
        int itemsSize = todoPage.getTodoItemsListSize();
        String expectedItemsLeftText = itemsSize + " items left";
        if (itemsSize == 1) {
            expectedItemsLeftText = itemsSize + " item left";
        }
        assert Objects.equals(expectedItemsLeftText, todoPage.getFooterItemsCountText()) : "Recently added todo item not matching";
    }

    @And("I should see footer have {string}, {string} and {string} buttons")
    public void iShouldSeeFooterHaveAndButtons(String allBtnTxt, String activeBtnTxt, String completedBtnTxt) {
        List<String> expectedListOfFooterLinksText = Arrays.asList(allBtnTxt, activeBtnTxt, completedBtnTxt);
        List<String> actualListOfFooterLinksText = todoPage.getFooterLinksText();
        assert expectedListOfFooterLinksText.containsAll(actualListOfFooterLinksText) : "Footer Links text not matching";
    }

    @When("^I (add|delete|complete) below todo items:$")
    public void iAddBelowTodoItems(String operation, DataTable dataTable) throws InterruptedException {
        List<String> todoItemsList = dataTable.asList();
        if(operation.equalsIgnoreCase("add")) {
            todoPage.addMultipleTodoItems(todoItemsList);
        } else if(operation.equalsIgnoreCase("delete")){
            todoPage.deleteMultipleTodoItems(todoItemsList);
        } else {
            todoPage.markCompleteMultipleTodoItems(todoItemsList);
        }
    }

    @And("I should see todo list with below items:")
    public void iShouldSeeTodoListWithBelowItems(DataTable dataTable) {
        List<String> expectedTodoItemsList = dataTable.asList();
        List<String> actualTodoItemsList = todoPage.listOfAddedTodoItemText();
        assert expectedTodoItemsList.containsAll(actualTodoItemsList) : "Todo List not matching";
    }

    @Then("I should see {string} link selected as default")
    public void iShouldSeeLinkSelectedAsDefault(String selectedLinkText) {
        assert Objects.equals(selectedLinkText,todoPage.getSelectedLinkText()) : "Selected Footer Link text not matching";
    }

    @When("I click on {string} link")
    public void iClickOnLink(String linkTextToClick) {
        todoPage.clickOnLinkText(linkTextToClick);
    }

    @And("^I should see todo list with (first|last) item as \"(.*)\"$")
    public void iShouldSeeTodoListWithFirstItemAs(String firstOrLast, String itemText) {
        List<String> actualTodoItemsList = todoPage.listOfAddedTodoItemText();
        if(firstOrLast.equalsIgnoreCase("first")) {
            assert Objects.equals(itemText,actualTodoItemsList.get(0)) : "Selected Footer Link text not matching";
        } else {
            assert Objects.equals(itemText,actualTodoItemsList.get(actualTodoItemsList.size()-1)) : "Selected Footer Link text not matching";
        }
    }

    @And("I should see todo list with below items in same order:")
    public void iShouldSeeTodoListWithBelowItemsInSameOrder(DataTable dataTable) {
        List<String> expectedTodoItemsList = dataTable.asList();
        List<String> actualTodoItemsList = todoPage.listOfAddedTodoItemText();
        assert expectedTodoItemsList.equals(actualTodoItemsList) : "Todo List Order not matching";
    }

    @When("I delete the item {string}")
    public void iDeleteTheItem(String todoItem) throws InterruptedException {
        todoPage.deleteBasedOnTodoText(todoItem);
    }

    @And("^I (should|should not) see footer$")
    public void iShouldNotSeeFooter(String condition) {
        if(condition.equalsIgnoreCase("should")) {
            assert !todoPage.verifyFooterNotDisplaying() : "Footer not available in page";
        } else {
            assert todoPage.verifyFooterNotDisplaying() : "Footer available in page";
        }
    }

    @And("I mark {string} item as completed")
    public void iMarkItemAsCompleted(String todoItem) throws InterruptedException {
        todoPage.markCompletedBasedOnTodoText(todoItem);
        Thread.sleep(2000);
    }

    @And("^I (should|should not) see todo list with item \"(.*)\"$")
    public void iShouldNotSeeTodoListWithItem(String condition, String todoListItem) {
        boolean isItemAvailable = todoPage.verifyTodoItemDisplay(todoListItem);

        if(condition.equalsIgnoreCase("should")) {
            assert isItemAvailable : "TodoItem not available";
        } else {
            assert !isItemAvailable : "TodoItem available";
        }
    }

    @Then("I should see {string} item should be striked through")
    public void iShouldSeeItemShouldBeStrikedThrough(String todoItem) {
        assert todoPage.verifyCompletedTodoItemStrikedThrough(todoItem) : "Todo Item not striked through";
    }

    @And("I should see {string} item should be grayed out")
    public void iShouldSeeItemShouldBeGrayedOut(String todoItem) {
        assert todoPage.verifyCompletedTodoItemGrayedOut(todoItem) : "Todo Item not grayed out";
    }

    @And("I should see todo list with below items as striked through and grayed out:")
    public void iShouldSeeTodoListWithBelowItemsAsStrikedThroughAndGrayedOut(DataTable dataTable) {
        List<String> todoItemsList = dataTable.asList();
        for(String todoItem: todoItemsList) {
            assert todoPage.verifyCompletedTodoItemStrikedThrough(todoItem) : todoItem + " Todo Item not striked through";
            assert todoPage.verifyCompletedTodoItemGrayedOut(todoItem) : todoItem + " Todo Item not striked through";
        }
    }

    @And("^I (should|should not) see \"Clear completed\" button$")
    public void iShouldSeeButton(String condition) {
        if(condition.equalsIgnoreCase("should")) {
            assert todoPage.verifyClearCompletedBtnDisplay() : "Clear Completed Button not display";
        } else {
            assert !todoPage.verifyClearCompletedBtnDisplay() : "Clear Completed Button not display";
        }
    }

    @When("I click on \"Clear completed\" button")
    public void iClickOnButton() {
        todoPage.clickClearCompletedButton();
    }

    @When("I update {string} item as {string}")
    public void iUpdateItemAs(String currentItem, String updateItem) throws InterruptedException {
        todoPage.updateTodoItem(currentItem, updateItem);
    }
}
