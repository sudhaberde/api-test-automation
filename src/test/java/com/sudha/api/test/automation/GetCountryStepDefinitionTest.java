package com.sudha.api.test.automation;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class GetCountryStepDefinitionTest {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("^user navigates to (.*?)$")
    public void givenStatment(String uri) {
        request = given().baseUri(uri);
    }

    @When("^retrieve the list of countries$")
    public void whenStatement(){
        response = request.when().get();
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("^the status code is (\\d+)$")
    public void thenStatment(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("^response contains the following <tag> and <value>$")
    public void response_contains_the_following_tag_and_value(DataTable table) throws Throwable {
        List<Map<String,String>> data = table.asMaps(String.class, String.class);
        for(Map<String, String> map : data) {
            json.body(map.get("tag"), hasItem(map.get("value")));
        }
    }

    @Given("^user calls country specific url (.*?) for US$")
    public void navigate_country_specific_US(String url) {
        request = given().baseUri("http://services.groupkt.com/country/get/iso2code/" + "US");
    }

    @Given("^user calls country specific url (.*?) for DE$")
    public void navigate_country_specific_DE(String url) {
        request = given().baseUri("http://services.groupkt.com/country/get/iso2code/" + "DE");
    }

    @Given("^user calls country specific url (.*?) for GB$")
    public void navigate_country_specific_GB(String url) {
        request = given().baseUri("http://services.groupkt.com/country/get/iso2code/" + "GB");
    }

    @When("^retrieve the country details$")
    public void retrieve_details(){
        response = request.when().get();
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("^the response code is (\\d+)$")
    public void thenRespStatement(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("^response contains United States of America$")
    public void response_validation_US() throws Throwable {
        json.body("RestResponse.result.name", Matchers.equalTo("United States of America"));
    }

    @And("^response contains Germany$")
    public void response_validation_DE() throws Throwable {
        json.body("RestResponse.result.name", Matchers.equalTo("Germany"));
    }

    @And("^response contains United Kingdom of Great Britain and Northern Ireland$")
    public void response_validation_GB() throws Throwable {
        json.body("RestResponse.result.name", Matchers.equalTo("United Kingdom of Great Britain and Northern Ireland"));
    }

    @Given("^user calls country specific url (.*?) for XX$")
    public void navigate_country_specific_XX(String url) throws Throwable {
        request = given().baseUri("http://services.groupkt.com/country/get/iso2code/" + "XX");
    }

    @Then("^response contains Invalid$")
    public void response_contains_Invalid() throws Throwable {
        json.body("RestResponse.messages", Matchers.hasItem("No matching country found for requested code [XX]."));
    }
}
