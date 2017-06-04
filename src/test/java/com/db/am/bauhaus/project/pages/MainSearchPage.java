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

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/span[2]/div/div/div[1]/a")
    WebElementFacade icon;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/span[2]/div/div/div[1]/a/div[2]/div/span[2]")
    WebElementFacade iconTitle;


    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }

    public void searchFromDropDown(String searchOption) {
        Actions action = new Actions(getDriver());
        action.moveToElement(category.waitUntilPresent()).build().perform();
        accesories.click();
    }

    public String searchFromIcons() {
        String target = iconTitle.getText();
        icon.waitUntilPresent().click();
        return target;
    }

    public String getTopCategoriesHeader() {
        return find(By.cssSelector("h4.pb-xs-1-5")).getText();
    }

    public String getAllCategoriesHeader() {
        return find(By.cssSelector("h1.conform-heading.display-inline")).getText();
    }

    public String getCategory() {
        return find(By.cssSelector("div.float-left > h1")).getText();
    }
}
