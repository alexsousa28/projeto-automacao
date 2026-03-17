package com.example.demo.web.screenshot;

import com.example.demo.web.driver.DriverFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHook {

    private int stepCount = 1;

    @AfterStep("@web")
    public void tirarScreenshot(Scenario scenario) {

        try {

            WebDriver driver = DriverFactory.getDriver();

            if (driver == null) {
                return;
            }

            String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            String nomeCenario = scenario.getName()
                    .replace(" ", "_")
                    .replace("/", "_");

            String pasta = "evidence/" + data + "/" + nomeCenario;

            File diretorio = new File(pasta);

            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            String nomeStep = "step_" + String.format("%02d", stepCount);

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File destino = new File(pasta + "/" + nomeStep + ".png");

            Files.copy(screenshot.toPath(), destino.toPath());

            byte[] screenshotBytes = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshotBytes, "image/png", nomeStep);

            stepCount++;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}