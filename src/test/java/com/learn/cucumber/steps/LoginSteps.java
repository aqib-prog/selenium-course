package com.learn.cucumber.steps;
import com.learn.LoginPage;
import com.learn.LoginTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage(){
        System.out.println("On the login page: "+ driver.getCurrentUrl());
    }

    @When("I enter username {string} and password {string}")
    public void iEnterCredentials(String username, String password){
        loginPage.login(username,password);
    }

    @Then("I should be redirected to inventory page")
    public void iShouldSeeInventoryPage(){
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Then("I should see an error message")
    public void iShouldSeeErrorMessage(){
        String error = loginPage.getErrorMessage();
        Assert.assertNotNull(error);
    }

    @After
    public void teardown(){
        driver.quit();
    }



}
