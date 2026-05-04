package com.learn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest {
LoginPage loginPage;

@DataProvider(name="loginData")
public Object[][] getLoginData(){
    return new Object[][]{
            {"standard_user", "secret_sauce", true},
            {"locked_out_user", "secret_sauce",false},
            {"wrong_user", "wrong_pass", false}
    };
}
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver.set(new ChromeDriver(options));
        driver.get().get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver.get());
    }

@Test
    public void validLogin(){
    loginPage.login("standard_user", "secret_sauce");

    WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("inventory"));

    System.out.println("URL "+ driver.get().getCurrentUrl());
}

@Test
public void verifyProductsPage(){
    loginPage.login("standard_user", "secret_sauce");
    InventoryPage inventoryPage = new InventoryPage(driver.get());
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

@Test(dataProvider = "loginData")
public void dataLoginTest(String username, String password, boolean shouldPass){
    loginPage.login(username, password);

    if(shouldPass){
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("inventory"));
        System.out.println("Login passed for: "+ username);
    }
    else{
        String error = loginPage.getErrorMessage();
        Assert.assertNotNull(error);
        System.out.println("Error for "+ username + ": "+ error);
    }
}


@AfterMethod
    public void teardown(){
    driver.get().quit();
    driver.remove();
}


}
