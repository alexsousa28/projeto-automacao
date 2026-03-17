package com.example.demo.api.gerarRelatorio;

import com.example.demo.api.consultarPostIdGet.EndPoints;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfReport {

    private String PROJETO = EndPoints.PROJETO;
    private String EXECUTOR = EndPoints.EXECUTOR;

    public void gerarRelatorio(int statusCode,
                               HttpRequest request,
                               String requestBody,
                               String responseBody) throws IOException {

        // Data da pasta
        String dataHoje = LocalDate.now().toString();

        // Hora do arquivo
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH-mm-ss");
        String hora = LocalDateTime.now().format(formatterHora);

        // Data e hora completa
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraCompleta = LocalDateTime.now().format(formatterDataHora);

        // Criar pasta de evidência
        File dir = new File("evidence/" + dataHoje);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = "evidence/" + dataHoje + "/evidence-" + hora + ".pdf";

        try (PdfWriter writer = new PdfWriter(path);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("=== RELATÓRIO DE TESTE DE API ==="));
            document.add(new Paragraph("Projeto: " + PROJETO));
            document.add(new Paragraph("Executor: " + EXECUTOR));
            document.add(new Paragraph("Data/Hora Execução: " + dataHoraCompleta));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Request URI: " + request.uri()));
            document.add(new Paragraph("Método: " + request.method()));

            // Request Body (se existir)
            if (requestBody != null && !requestBody.isEmpty()) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Request Body:"));
                document.add(new Paragraph(requestBody));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Status Code: " + statusCode));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Response Body:"));
            document.add(new Paragraph(responseBody));
        }
    }
}