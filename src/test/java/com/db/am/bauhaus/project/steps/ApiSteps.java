package com.db.am.bauhaus.project.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by Juan on 05/06/2017.
 */
public class ApiSteps {

    private Response response;
    private RequestSpecification request;
    private String BASEURI = "https://openapi.etsy.com/v2/";
    private String ENDPOINT_GET_SHOPS = "shops?api_key={api_key}";


//  In order for these tests to work, a valid api key must be inserted in the next line, else all requests will
//  end up in status code 403 errors
    private String API_KEY = "Replace this for a real dev api key";
    private String ResponseAsString = null;

    @Given("^John is a developer with a valid api-key$")
    public void johnIsADeveloperWithAValidApiKey() {
        request = given().pathParam("api_key", API_KEY).and().baseUri(BASEURI);
    }

    @Given("^John is not a developer with a valid api-key$")
    public void johnIsNotADeveloperWithAValidApiKey() {
        request = given().pathParam("api_key", "INVALID").and().baseUri(BASEURI);
    }

    @When("^he requests a list of shops$")
    public void heRequestsAListOfShops() {
        response = request.expect().when().get(ENDPOINT_GET_SHOPS);
    }

    @Then("^the status code should be (\\d+)$")
    public void theStatusCodeShouldBe(int arg0) {
        response.then().statusCode(arg0);
    }

    @And("^content type should be \"([^\"]*)\"$")
    public void contentTypeShouldBe(String type) {
        ResponseAsString = response.then().contentType(type).extract().response().asString();
    }

    @And("^response should include (\\d+) of \"([^\"]*)\"$")
    public void responseIncludesTheFollowing(int times, String target) {
        int i = 0;
        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher( ResponseAsString );
        while (m.find()) {
            i++;
        }

        assertThat(i, is(equalTo(times)));

    }

}
