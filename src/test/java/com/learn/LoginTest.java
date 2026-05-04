package com.learn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest {
WebDriver driver;
LoginPage loginPage;

@BeforeMethod
    public void setup(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://www.saucedemo.com");
    loginPage = new LoginPage(driver);
}

@Test
    public void validLogin(){
    loginPage.login("standard_user", "secret_sauce");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("inventory"));

    System.out.println("URL "+ driver.getCurrentUrl());
}

@Test
public void verifyProductsPage(){
    loginPage.login("standard_user", "secret_sauce");
    InventoryPage inventoryPage = new InventoryPage(driver);
    List<String> products = inventoryPage.getAllProductTitles();

    // Asset 6 product
    Assert.assertEquals(products.size(),6);
    System.out.println("Products: "+ products);

}

@Test
public void errorMessageLogin(){
    loginPage.login("standard_user11", "secret_sauce");
    String error= loginPage.getErrorMessage();
    Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service");

}


@AfterMethod
    public void teardown(){
    driver.quit();
}


}
