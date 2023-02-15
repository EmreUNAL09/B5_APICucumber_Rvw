package com.devEx.stepDef;

import com.devEx.request.DevExRequest;
import com.devEx.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;

public class ProfileStepDef {

    Response response;

    @Given("User creates a get request and able to see all profiles")
    public void user_creates_a_get_request_and_able_to_see_all_profiles() {
        response = given().accept(ContentType.JSON)
                .when()
                .get(ConfigurationReader.get("allProfiles"));
        response.prettyPrint();

    }

    @Then("Verify that status code is {int}")
    public void verify_that_status_code_is(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());

    }

    @Given("User creates a get request and able to see all profiles second way")
    public void userCreatesAGetRequestAndAbleToSeeAllProfilesSecondWay() {
        response = DevExRequest.getAllProfiles();
    }

    @Given("User creates a GET request with path {string}")
    public void userCreatesAGETRequestWithPath(String id) {
        response = DevExRequest.getOneUserWithPathParam(id);
    }

    @Then("Verify that user info's {string} and {string} and {string} and {string}")
    public void verifyThatUserInfoSAndAndAnd(String name, String email, String company, String location) {
        System.out.println("response.path(\"user.name\") = " + response.path("user.name"));
        System.out.println("response.path(\"user.email\") = " + response.path("user.email"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"location\") = " + response.path("location"));

        assertEquals(name,response.path("user.name"));
        assertEquals(email,response.path("user.email"));
        assertEquals(company,response.path("company"));
        assertEquals(location,response.path("location"));
    }


}
