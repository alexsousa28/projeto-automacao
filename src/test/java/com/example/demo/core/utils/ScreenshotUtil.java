package com.example.demo.core.utils;

import com.example.demo.core.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static byte[] tirarScreenshot() {

        WebDriver driver = DriverFactory.getDriver();

        if (driver == null) return null;

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}