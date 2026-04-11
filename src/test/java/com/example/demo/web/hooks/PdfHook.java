package com.example.demo.web.hooks;

import com.example.demo.web.screenshot.PdfEvidenceManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class PdfHook {

    // 🔹 INICIA O PDF
    @Before("@web")
    public void iniciarPdf(Scenario scenario) {

        try {
            PdfEvidenceManager.iniciarPdf(scenario.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 FINALIZA O PDF
    @After("@web")
    public void finalizarPdf(Scenario scenario) {

        try {

            // 🔥 Se quiser evidência final em caso de erro
            if (scenario.isFailed()) {
                PdfEvidenceManager.adicionarStep(
                        "FALHA NO TESTE",
                        null // ou pode colocar screenshot se quiser
                );
            }

            PdfEvidenceManager.finalizarPdf();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}