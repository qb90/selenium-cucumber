package stepDefs;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.Scenario;
import webPages.SomePage;

public class PageSteps {
	private SomePage page = new SomePage();

	@Before
	public void before(Scenario scenario) {
		page.initializing();
	}
	
	@After
	public void after(Scenario scenario) {
		page.ending();
	}

	@Given("^I am on main page$")
	public void i_am_on_main_page() {
		page.openUrl();
	}

	@When("I type login (.*)$")
	public void i_type_login(String userLogin) {
		page.typeLogin(userLogin);
	}

	@When("I type password (.*)$")
	public void i_type_password(String userPassword) {
		page.typePassword(userPassword);
	}

	@When("I log in$")
	public void i_log_in() {
		page.login();
	}

	@Then("I see my name (.*)$")
	public void i_see_my_name(String userName) {
		page.verifyName(userName);
	}

	@Then("I see my city (.*)$")
	public void i_see_my_city(String userCity) {
		page.verifyCity(userCity);
	}
	
	@Then("I see message section$")
	public void i_see_message_section() {
		page.checkMessageSection();
	}
	
	@Then("I log out$")
	public void i_log_out() {
		page.logout();
	}
}
