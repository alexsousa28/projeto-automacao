package com.example.demo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example.demo",
        plugin = {"json:target/cucumber-report.json"},
        monochrome = true,
        tags = "@34659"
)
public class WebRunner {
}
