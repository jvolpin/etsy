package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

/**
 * Created by Juan on 04/06/2017.
 */
public class ResultsPage extends PageObject {

    @FindBy(css = "h4.pb-xs-1-5")
    WebElementFacade topCategoryHeader;

    @FindBy(css = "h1.conform-heading.display-inline")
    WebElementFacade allCategoriesHeader;

//    there seems to be some ab testing going on, so this xpath doesn't look pretty
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[3]/div[1]/h1 | //*[@id=\"content\"]/div/div/div[2]/h1 | //*[@id=\"content\"]/div/div[2]/div[1]/h1")
    WebElementFacade categoryTitle;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }



    public String getTopCategoriesHeader() {
        return topCategoryHeader.getText();
    }

    public String getAllCategoriesHeader() {
        return allCategoriesHeader.getText();
    }

    public String getCategory() {
        return categoryTitle.getText();
    }

}
