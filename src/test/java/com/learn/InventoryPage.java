package com.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    WebDriver driver;

    //Locators
    By productTitle = By.cssSelector(".inventory_item_name");

    //Constructor
    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    // Action
    public String getTitle(){
        return driver.findElement(productTitle).getText();
    }

    public List<String> getAllProductTitles(){
        List<WebElement> products = driver.findElements(productTitle);
        List<String> titles = new ArrayList<>();
        for(WebElement product: products){
            titles.add(product.getText());
        }
        return titles;
    }


}
