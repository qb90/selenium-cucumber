package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webPages.AbstractPage;
import helpers.PageHelper;

public class SomePage extends AbstractPage {

	private String pageUrl = "https://some-page.com";
	private String login_cookie_name = "est_login";
	private String login_field_class = "login-field";
	private String password_field_class = "password-field";
	private String login_button_class = "x-btn-inner-default-small";
	private String login_box_class = "login-form-bg";
	private String user_panel_id = "AppMainTabPanel-body";
	private String toolbar_button_class = "x-btn-inner-default-toolbar-small";
	private String message_section_class = "fa-inbox";
	private String dashboard_info_class = "dashboard-info-item";
	private String logout_button_class = "estore-logout";
	private String message_add_button_class = "button-add";
	private String message_clear_button_class = "button-clear";

	public void initializing() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars", "--disable-extensions", "--start-maximized");
		driver = new ChromeDriver(options);
	}

	public void openUrl() {
		try {
			driver.get(pageUrl);
			WebDriverWait pageLayout = new WebDriverWait(driver, 10);
			pageLayout.until(ExpectedConditions.visibilityOfElementLocated(By.className(login_box_class)));
		} catch (Exception e) {
			System.out.println("Page is not loading.");
		}
	}

	public void typeLogin(String login) {
		try {
			WebElement loginField = driver.findElement(By.className(login_field_class));
			loginField.clear();
			loginField.sendKeys(login);
		} catch (Exception e) {
			System.out.println("Unable to type login.");
		}
	}

	public void typePassword(String password) {
		try {
			WebElement passwordField = driver.findElement(By.className(password_field_class));
			passwordField.clear();
			passwordField.sendKeys(password);
		} catch (Exception e) {
			System.out.println("Unable to type password.");
		}
	}

	public void login() {
		try {
			WebElement loginButton = driver.findElement(By.className(login_button_class));
			loginButton.click();
			WebDriverWait accountPanelLayout = new WebDriverWait(driver, 10);
			accountPanelLayout.until(ExpectedConditions.visibilityOfElementLocated(By.id(user_panel_id)));
		} catch (Exception e) {
			System.out.println("Unable to login.");
		}
	}

	public void verifyName(String expectedName) {
		try {
			WebElement userNameInHeader = driver.findElement(By.cssSelector("#dashboard-header > h1"));

			String currentName = userNameInHeader.getText();
			Assert.assertEquals(currentName, expectedName);

			WebElement userNameInToolbar = driver.findElement(By.className(toolbar_button_class));
			currentName = userNameInToolbar.getText();
			Assert.assertEquals(currentName, expectedName);
		} catch (Exception e) {
			System.out.println("User name is incorrect.");
		}
	}

	public void verifyCity(String expectedCity) {
		try {
			WebElement workplace = driver.findElement(By.cssSelector("ul[class='workplace-info'] li:nth-of-type(1)"));
			String workplaceText = workplace.getText();
			String currentCity = PageHelper.cityNameExtractor(workplaceText);
			Assert.assertEquals(currentCity, expectedCity);
		} catch (Exception e) {
			System.out.println("User city is incorrect.");
		}
	}

	public void checkMessageSection() {
		try {
			WebElement dashboardInfo = driver.findElement(By.className(dashboard_info_class));
			dashboardInfo.click();

			WebDriverWait messageLayout = new WebDriverWait(driver, 10);
			messageLayout.until(ExpectedConditions.visibilityOfElementLocated(By.className(message_add_button_class)));

			WebElement addMessageButton = driver.findElement(By.className(message_add_button_class));
			WebElement clearMessageButton = driver.findElement(By.className(message_clear_button_class));

			Assert.assertTrue(addMessageButton.isDisplayed());
			Assert.assertTrue(clearMessageButton.isDisplayed());

			WebElement messageSection = driver.findElement(By.className(message_section_class));
			Assert.assertTrue(messageSection.isEnabled());

		} catch (Exception e) {
			System.out.println("Message section is incorrect.");
		}
	}

	public void logout() {
		try {
			Boolean loginState = false;
			Boolean logoutState = false;

			WebElement logoutButton = driver.findElement(By.className(logout_button_class));
			Cookie loginCookie = driver.manage().getCookieNamed(login_cookie_name);

			if (loginCookie != null) {
				loginState = true;
			}
			Assert.assertTrue(loginState);

			logoutButton.click();

			WebDriverWait pageLayout = new WebDriverWait(driver, 10);
			pageLayout.until(ExpectedConditions.visibilityOfElementLocated(By.className(login_box_class)));
			Cookie loginCookieAfterLogout = driver.manage().getCookieNamed(login_cookie_name);

			if (loginCookieAfterLogout == null) {
				logoutState = true;
			}
			Assert.assertTrue(logoutState);
		} catch (Exception e) {
			System.out.println("Logout is incorrect.");
		}
	}

	public void ending() {
		driver.close();
	}
}
