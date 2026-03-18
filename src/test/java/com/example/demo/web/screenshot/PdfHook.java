package com.example.demo.web.screenshot;

import com.example.demo.web.screenshot.PdfEvidenceManager;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class PdfHook {

    @Before("@web")
    public void iniciar(Scenario scenario) throws Exception {

        PdfEvidenceManager.iniciarPdf(scenario.getName());

    }
}
