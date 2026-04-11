package com.example.demo.web.hooks;

import com.example.demo.web.driver.DriverFactory;
import com.example.demo.web.screenshot.PdfEvidenceManager;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotPdfHook {

    @AfterStep("@web")
    public void capturarStep(Scenario scenario) {

        try {

            WebDriver driver = DriverFactory.getDriver();

            if (driver == null) return;

            // Nome REAL do step (Given/When/Then)
            String stepNome = scenario.getName(); // fallback

            // Melhor opção (pegar o step atual)
            String stepAtual = scenario.getStatus().toString();

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            PdfEvidenceManager.adicionarStep(stepAtual, screenshot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
