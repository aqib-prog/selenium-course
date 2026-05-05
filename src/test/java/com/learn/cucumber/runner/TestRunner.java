package com.learn.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = "com.learn.cucumber.steps",
            plugin = {"pretty", "html:target/cucumber-reports.html"}
    )
    public class TestRunner extends AbstractTestNGCucumberTests{

    }
