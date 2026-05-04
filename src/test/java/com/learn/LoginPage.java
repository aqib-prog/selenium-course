package com.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage {

    WebDriver driver;

    //Locators
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By loginErrorBox = By.cssSelector(".error-message-container");

    //Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage(){
        return driver.findElement(loginErrorBox).getText();
    }
}
