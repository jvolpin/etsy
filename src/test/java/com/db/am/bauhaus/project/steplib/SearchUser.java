package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.db.am.bauhaus.project.pages.ResultsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends ScenarioSteps {

    MainSearchPage mainSearchPage;
    ResultsPage resultsPage;

    String searchText = "craft";

    private String dropDownOption = null;

    private String selectedIcon = null;

    @Step
    public void search_from_input_box() {
        mainSearchPage.searchFromInputBox(searchText);
    }

    @Step
    public String search_from_drop_down() {
        dropDownOption = mainSearchPage.searchFromDropDown();
        return dropDownOption;
    }

    @Step
    public String search_from_icons() {
        selectedIcon = mainSearchPage.searchFromIcons();
        return selectedIcon;
    }

    @Step
    public void verify_result_for_top_categories() {
        assertThat(resultsPage.getTopCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_for_all_categories() {
        assertThat(resultsPage.getAllCategoriesHeader(), containsString(searchText));
    }

    @Step
    public void verify_result_as_category() {
        assertThat(resultsPage.getCategory(), containsString(dropDownOption));
    }

    @Step
    public void verify_result_as_description() {
        assertThat(resultsPage.getCategory(), containsString(selectedIcon));
    }


}
