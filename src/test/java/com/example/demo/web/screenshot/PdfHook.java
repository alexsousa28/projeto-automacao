package com.example.demo.web.hooks;

import com.example.demo.web.driver.DriverFactory;
import com.example.demo.web.screenshot.PdfEvidenceManager;
import com.example.demo.web.screenshot.StepContext;
import io.cucumber.java.*;
import org.openqa.selenium.*;

public class PdfHook {

    // 🔹 INICIA PDF
    @Before("@web")
    public void iniciarPdf(Scenario scenario) {

        try {
            PdfEvidenceManager.iniciarPdf(scenario.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 CAPTURA CADA STEP
    @AfterStep("@web")
    public void capturarStep(Scenario scenario) {

        try {

            WebDriver driver = DriverFactory.getDriver();

            if (driver == null) return;

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            if (screenshot == null || screenshot.length == 0) return;

            // 🔥 nome do step vindo do contexto
            String stepNome = StepContext.getStep();

            PdfEvidenceManager.adicionarStep(stepNome, screenshot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 FINALIZA PDF
    @After("@web")
    public void finalizarPdf(Scenario scenario) {

        try {

            // opcional: print final se falhar
            if (scenario.isFailed()) {

                WebDriver driver = DriverFactory.getDriver();

                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                PdfEvidenceManager.adicionarStep("FALHA NO TESTE", screenshot);
            }

            PdfEvidenceManager.finalizarPdf();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}