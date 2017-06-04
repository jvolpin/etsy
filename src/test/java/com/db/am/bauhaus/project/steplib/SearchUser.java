package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.MainSearchPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends ScenarioSteps {

    MainSearchPage mainSearchPage;

    String searchText = "craft";

    String searchOption = "Accessories";

    String searchElement = null;

    @Step
    public void search_from_input_box() {
        mainSearchPage.searchFromInputBox(searchText);
    }

    @Step
    public void search_from_drop_down() {
        mainSearchPage.searchFromDropDown(searchOption);
    }

    @Step
    public String search_from_icons() {
        searchElement = mainSearchPage.searchFromIcons();
        return searchElement;
    }

    @Step
    public void verify_result_for_top_categories() {
        assertThat(mainSearchPage.getTopCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_for_all_categories() {
        assertThat(mainSearchPage.getAllCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_as_category() {
        assertThat(mainSearchPage.getCategory(), containsString(searchOption));
    }

    @Step
    public void verify_result_as_description() {
        assertThat(mainSearchPage.getCategory(), containsString(searchElement));
    }


}
