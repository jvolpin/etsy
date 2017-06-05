package com.db.am.bauhaus.project.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
    private String baseuri = null;
    private String ENDPOINT_GET_SHOPS = "shops?api_key={api_key}";


//  In order for these tests to work, a valid api key must be inserted in the next line, else all requests will
//  end up in status code 403 errors
    private String apiKey = null;
    private String ResponseAsString = null;


    @Before
    public void startup() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("serenity.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            baseuri = prop.getProperty("baseuri");
            apiKey = prop.getProperty("api_key");
            System.out.println(prop.getProperty("baseuri"));
            System.out.println(prop.getProperty("api_key"));


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Given("^John is a developer with a valid api-key$")
    public void johnIsADeveloperWithAValidApiKey() {
        request = given().pathParam("api_key", apiKey).and().baseUri(baseuri);
    }

    @Given("^John is not a developer with a valid api-key$")
    public void johnIsNotADeveloperWithAValidApiKey() {
        request = given().pathParam("api_key", "INVALID").and().baseUri(baseuri);
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
