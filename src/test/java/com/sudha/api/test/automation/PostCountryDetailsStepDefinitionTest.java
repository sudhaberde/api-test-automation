package com.sudha.api.test.automation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostCountryDetailsStepDefinitionTest {
    private io.restassured.response.Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("^user calls post url (.*?) for XX$")
    public void getPostUri(String uri) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name","Test Country");
        jsonObject.addProperty("alpha2_code", "TC");
        jsonObject.addProperty("alpha2_code", "TCY");
        String body = jsonObject.toString();
        request = given().baseUri(uri).body(body);
    }

    @When("^post the country details$")
    public void makePostCall(){
        response = request.when().post();
    }

    @Then("^the post response code is (\\d+)$")
    public void validatePostResponse(int statusCode){
        response.then().statusCode(statusCode);
        System.out.println("response code: " + response.getStatusCode());
    }
}
