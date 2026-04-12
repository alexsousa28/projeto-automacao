package com.example.demo.web.hooks;

import com.example.demo.web.screenshot.PdfEvidenceManager;
import com.example.demo.web.screenshot.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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

    // 🔹 FINALIZA PDF
    @After("@web")
    public void finalizarPdf(Scenario scenario) {

        try {

            // 🔥 SOMENTE EM CASO DE ERRO (OPCIONAL)
            if (scenario.isFailed()) {

                byte[] screenshot = ScreenshotUtil.tirarScreenshot();

                PdfEvidenceManager.adicionarStep(
                        "Erro na execução do teste",
                        screenshot
                );
            }

            PdfEvidenceManager.finalizarPdf();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}