package com.devEx.stepDef;

import com.devEx.pages.LoginPage;
import com.devEx.request.DevExRequest;
import com.devEx.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.junit.Assert.*;


public class SingUpStepDef {

    Response response;

    String token;

    int id;

    @Given("User creates a POST request with {string} and {string} and {string} and {string} and {string} and {string}")
    public void userCreatesAPOSTRequestWithAndAndAndAndAnd(String email, String password, String name, String google, String facebook, String github) {
        response = DevExRequest.registerNewUser(email, password, name, google, facebook, github);
        response.prettyPrint();
    }

    @Then("Verify that body contains {string}")
    public void verifyThatBodyContains(String token) {
        assertTrue(response.body().asString().contains(token));
    }

    @Then("Verify that status code should be {int}")
    public void verifyThatStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @And("Compiler gets the token")
    public void compilerGetsTheToken() {
        token = response.path("token");
        ConfigurationReader.set("neUserToken", token);
    }

    @Given("User creates a POST request and send the token with {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void userCreatesAPOSTRequestAndSendTheTokenWith(String company, String website, String location, String status, String skills,
                                                           String githubusername, String youtube, String twitter, String facebook,
                                                           String linkedin, String instagram) {
        response = DevExRequest.postSaveProfile(company, website, location, status, skills, githubusername, youtube, twitter, facebook, linkedin, instagram);
    }

    @Then("verify that name {string} and email as {string}")
    public void verifyThatNameAndEmailAs(String expectedName, String expectedEmail) {
        assertEquals(expectedName, response.path("user.name"));
        assertEquals(expectedEmail, response.path("user.email"));
    }

    @Given("User creates a POST request for add an experience with {string} {string} {string} {string}  {string} {string} {string}")
    public void userCreatesAPOSTRequestForAddAnExperienceWith(String title, String company, String location, String from, String to, String current, String description) {

        response = DevExRequest.postExperience(title, company, location, from, to, current, description);
    }

    @And("Compiler gets the experience id")
    public void compilerGetsTheExperienceId() {
        id = response.path("experience.id[0]");
    }

    @And("User creates GET request to get experience with id")
    public void userCreatesGETRequestToGetExperienceWithId() {
        response = DevExRequest.getExperience(id);
    }

    @And("User is on the dashboard page")
    public void userIsOnTheDashboardPage() throws InterruptedException{
        new LoginPage().setup();
    }

    @Then("verify that UI experience and API experience must be matched on company as {string}")
    public void verifyThatUIExperienceAndAPIExperienceMustBeMatchedOnCompanyAs(String expectedCompany) {
        String actualCompanyFromUI = new LoginPage().getNewCompany(expectedCompany);  //UI için

        String actualCompanyFromAPI = response.path("company");  //API için

        //api ve ui'nin karşılaştırması
        assertEquals(actualCompanyFromAPI,actualCompanyFromUI);
    }
}
