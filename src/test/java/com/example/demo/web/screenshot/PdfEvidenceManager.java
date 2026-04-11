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
    private static PdfFont fontNormal;
    private static PdfFont fontBold;

    public static void iniciarPdf(String nomeCenario) throws Exception {

        // 🔤 fontes (resolve problema do setBold)
        fontNormal = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String pasta = "evidence/" + data;

        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 🔥 limpa nome do cenário
        nomeCenario = nomeCenario
                .replaceAll("[^a-zA-Z0-9 ]", "")
                .replace(" ", "_");

        String caminho = pasta + "/" + nomeCenario + "_" + System.currentTimeMillis() + ".pdf";

        PdfWriter writer = new PdfWriter(caminho);
        PdfDocument pdf = new PdfDocument(writer);

        document = new Document(pdf);

        // 🎯 Cabeçalho
        document.add(new Paragraph("RELATÓRIO DE TESTE WEB")
                .setFont(fontBold)
                .setFontSize(16));

        document.add(new Paragraph("Cenário: " + nomeCenario)
                .setFont(fontNormal));

        document.add(new Paragraph("Data: " + new Date())
                .setFont(fontNormal));

        document.add(new Paragraph(" "));
    }

    public static void adicionarStep(String stepNome, byte[] screenshot) {

        try {

            if (document == null) return;

            // 🧾 Nome do step
            document.add(new Paragraph("Step: " + stepNome)
                    .setFont(fontBold));

            // 📸 Screenshot (com validação)
            if (screenshot != null && screenshot.length > 0) {

                Image img = new Image(ImageDataFactory.create(screenshot));
                img.setAutoScale(true);

                document.add(img);
            }

            document.add(new Paragraph(" "));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}