package com.example.demo.web.screenshot;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfEvidenceManager {

    private static Document document;

    public static void iniciarPdf(String nomeCenario) throws Exception {

        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String pasta = "evidence/" + data;

        new File(pasta).mkdirs();

        String caminho = pasta + "/" + nomeCenario.replace(" ", "_") + ".pdf";

        PdfWriter writer = new PdfWriter(caminho);
        PdfDocument pdf = new PdfDocument(writer);

        document = new Document(pdf);

        document.add(new Paragraph("Relatório de Teste WEB"));
        document.add(new Paragraph("Cenário: " + nomeCenario));
        document.add(new Paragraph("Data: " + new Date()));
        document.add(new Paragraph(" "));
    }

    public static void adicionarStep(String stepNome, byte[] screenshot) {

        try {
            document.add(new Paragraph("Step: " + stepNome));

            Image img = new Image(ImageDataFactory.create(screenshot));
            img.setAutoScale(true);

            document.add(img);
            document.add(new Paragraph(" "));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finalizarPdf() {
        if (document != null) {
            document.close();
        }
    }
}

