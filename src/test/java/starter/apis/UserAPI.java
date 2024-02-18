package starter.apis;

import com.google.gson.JsonObject;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserAPI {

	private static String USERS_ENDPOINT = "https://thinking-tester-contact-list.herokuapp.com/users/";
	
	private static String LOGIN_ENDPOINT = "login/";
	
	private static String LOGOUT_ENDPOINT = "logout/";

	@Step("Add new user")
	public void addUser(JsonObject requestBody) {
		SerenityRest
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody.toString())
				.baseUri(USERS_ENDPOINT)
			.when()
				.post();
	}
	
	@Step("I login")
	public void login(JsonObject requestBody) {
		SerenityRest
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody.toString())
				.baseUri(USERS_ENDPOINT)
			.when()
				.post(LOGIN_ENDPOINT);
	}
	
	@Step("I logout")
	public void logout(String token) {
		SerenityRest
			.given()
				.baseUri(USERS_ENDPOINT)
				.header("Authorization", "Bearer " + token)
			.when()
				.post(LOGOUT_ENDPOINT);
	}
}
