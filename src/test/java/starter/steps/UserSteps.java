package starter.steps;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.google.gson.JsonObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.apis.UserAPI;

public class UserSteps {

	@Steps
	UserAPI userAPI;

	@Given("I add a new user")
	public void i_add_a_new_user() {
		Faker user = new Faker();

		FakeValuesService fakeValuesService = new FakeValuesService(Locale.ENGLISH, new RandomService());

		String email = fakeValuesService.bothify("??????????#####@??????.com");

		String password = fakeValuesService.bothify("?????#####");

		Serenity.setSessionVariable("user").to(user);
		Serenity.setSessionVariable("email").to(email);
		Serenity.setSessionVariable("password").to(password);

		JsonObject requestBody = new JsonObject();
		requestBody.addProperty("firstName", user.name().firstName());
		requestBody.addProperty("lastName", user.name().lastName());
		requestBody.addProperty("email", email);
		requestBody.addProperty("password", password);

		userAPI.addUser(requestBody);

		restAssuredThat(response -> response.statusCode(201));
	}

	@When("I login")
	public void i_login() {

		String email = Serenity.getCurrentSession().get("email").toString();

		String password = Serenity.getCurrentSession().get("password").toString();

		JsonObject requestBody = new JsonObject();
		requestBody.addProperty("email", email);
		requestBody.addProperty("password", password);

		userAPI.login(requestBody);

		restAssuredThat(response -> response.statusCode(200));

		String token = SerenityRest.lastResponse().getBody().jsonPath().get("token");

		Serenity.setSessionVariable("token").to(token);
	}

	@Then("I logout")
	public void i_logout() {
		String token = Serenity.getCurrentSession().get("token").toString();
		userAPI.logout(token);

		restAssuredThat(response -> response.statusCode(200));
	}
}
