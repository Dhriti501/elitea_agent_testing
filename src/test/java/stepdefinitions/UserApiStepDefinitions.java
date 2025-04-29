package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApiStepDefinitions {

    private static final Logger logger = LogManager.getLogger(UserApiStepDefinitions.class);
    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        logger.info("Sending GET request to: {}", endpoint);
        response = given()
                .baseUri("https://reqres.in") // Base URI for the API
                .when()
                .get(endpoint);
        logger.info("Received response: {}", response.asString());
    }

    @When("I receive the response")
    public void i_receive_the_response() {
        logger.info("Response received successfully.");
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        logger.info("Verifying response status code: {}", statusCode);
        response.then().statusCode(statusCode);
    }

    @Then("the user object should have an ID of {int}")
    public void the_user_object_should_have_an_id_of(int userId) {
        logger.info("Verifying user ID in response: {}", userId);
        response.then().body("data.id", equalTo(userId));
    }

    @Then("the user object should contain the following fields:")
    public void the_user_object_should_contain_the_following_fields(io.cucumber.datatable.DataTable dataTable) {
        logger.info("Verifying user object fields: {}", dataTable.asList());
        dataTable.asList().forEach(field -> {
            response.then().body("data." + field).isNotNull();
        });
    }

    @Then("the email field should be {string}")
    public void the_email_field_should_be(String expectedEmail) {
        logger.info("Verifying email field: {}", expectedEmail);
        response.then().body("data.email", equalTo(expectedEmail));
    }

    @Then("the first_name field should be {string}")
    public void the_first_name_field_should_be(String expectedFirstName) {
        logger.info("Verifying first_name field: {}", expectedFirstName);
        response.then().body("data.first_name", equalTo(expectedFirstName));
    }

    @Then("the last_name field should be {string}")
    public void the_last_name_field_should_be(String expectedLastName) {
        logger.info("Verifying last_name field: {}", expectedLastName);
        response.then().body("data.last_name", equalTo(expectedLastName));
    }

    @Then("the avatar field should contain a valid URL")
    public void the_avatar_field_should_contain_a_valid_url() {
        logger.info("Verifying avatar field contains a valid URL.");
        String avatarUrl = response.jsonPath().getString("data.avatar");
        // Basic URL validation
        if (!avatarUrl.startsWith("http")) {
            throw new AssertionError("Avatar URL is invalid: " + avatarUrl);
        }
    }
}