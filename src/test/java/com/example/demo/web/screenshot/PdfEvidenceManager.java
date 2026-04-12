package com.example.demo.web.screenshot;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfEvidenceManager {

    private static Document document;
    private static PdfFont fontTitle;
    private static PdfFont fontStep;
    private static PdfFont fontNormal;

    private static boolean primeiroStep = true;

    // 🔹 INICIAR PDF
    public static void iniciarPdf(String nomeCenario) throws Exception {

        fontTitle = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        fontStep = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        fontNormal = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String pasta = "evidence/" + data;

        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        nomeCenario = nomeCenario
                .replaceAll("[^a-zA-Z0-9 ]", "")
                .replace(" ", "_");

        String caminho = pasta + "/" + nomeCenario + "_" + System.currentTimeMillis() + ".pdf";

        PdfWriter writer = new PdfWriter(caminho);
        PdfDocument pdf = new PdfDocument(writer);

        document = new Document(pdf);

        // Cabeçalho
        document.add(new Paragraph("RELATÓRIO DE EXECUÇÃO WEB")
                .setFont(fontTitle)
                .setFontSize(18));

        document.add(new Paragraph("Cenário: " + nomeCenario)
                .setFont(fontNormal)
                .setFontSize(11));

        document.add(new Paragraph("Data: " + new Date())
                .setFont(fontNormal)
                .setFontSize(10));

        document.add(new Paragraph(" "));

        primeiroStep = true;
    }

    // 🔹 ADICIONAR STEP (cada um em nova página)
    public static void adicionarStep(String stepNome, byte[] screenshot) {

        try {

            if (document == null) return;

            stepNome = limparNomeStep(stepNome);

            // 🔥 quebra página apenas depois do primeiro step
            if (!primeiroStep) {
                document.add(new AreaBreak());
            }

            primeiroStep = false;

            // Step
            document.add(new Paragraph("✔ " + stepNome)
                    .setFont(fontStep)
                    .setFontSize(12));

            document.add(new Paragraph(" "));

            // Screenshot
            if (screenshot != null && screenshot.length > 0) {

                Image img = new Image(ImageDataFactory.create(screenshot));
                img.setAutoScale(true);

                document.add(img);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 FINALIZAR PDF
    public static void finalizarPdf() {

        try {
            if (document != null) {
                document.close();
                document = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 LIMPAR STEP
    private static String limparNomeStep(String step) {

        return step.replaceAll(
                "^(Dado que:|Quando:|Então:|Given:|When:|Then:)\\s*",
                ""
        );
    }
}