package com.learn;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;

public class TestListener implements ITestListener{
    @Override
    public void onTestFailure(ITestResult result){
        Object testInstance = result.getInstance();
        WebDriver driver = null;

        try {
            driver = (WebDriver) testInstance.getClass()
                    .getDeclaredField("driver")
                    .get(testInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver !=null){
            try {
                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);
                Files.copy(screenshot.toPath(),
                        new File("screenshots/" + result.getName() + ".png").toPath());
                System.out.println("Screenshot saved: " + result.getName() + ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}