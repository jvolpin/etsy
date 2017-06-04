package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by ongshir on 05/10/2016.
 */
@DefaultUrl("/")
public class MainSearchPage extends PageObject {

    @FindBy(id = "search-query")
    WebElementFacade inputBox;

    @FindBy(css = "div.search-button-wrapper.hide > button")
    WebElementFacade searchButton;

    @FindBy(id = "catnav-primary-link-2938-link")
    WebElementFacade category;

    @FindBy(id = "category-nav-side-nav-3090-link")
    WebElementFacade accesories;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/span[2]/div/div/div[1]/a/div[2]/div/span[2]")
    WebElementFacade icon;


    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }

    public String searchFromDropDown() {
        Actions action = new Actions(getDriver());
        action.moveToElement(category.waitUntilPresent()).build().perform();
        String target = accesories.getText();
        accesories.click();
        return target;
    }

    public String searchFromIcons() {
        String target = icon.waitUntilPresent().getText();
        icon.waitUntilPresent().click();
        return target;
    }

}
