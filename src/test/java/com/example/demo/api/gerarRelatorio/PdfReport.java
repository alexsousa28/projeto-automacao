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

    private final String PROJETO = EndPoints.PROJETO;
    private final String EXECUTOR = EndPoints.EXECUTOR;

    public void gerarRelatorio(int statusCode,
                               HttpRequest request,
                               String requestBody,
                               String responseBody) {

        try {

            String path = gerarCaminhoArquivo();

            try (PdfWriter writer = new PdfWriter(path);
                 PdfDocument pdf = new PdfDocument(writer);
                 Document document = new Document(pdf)) {

                adicionarCabecalho(document);
                adicionarRequest(document, request, requestBody);
                adicionarResponse(document, statusCode, responseBody);

                System.out.println("📄 PDF gerado em: " + path);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório PDF", e);
        }
    }

    // 🔹 Gera pasta + nome do arquivo
    private String gerarCaminhoArquivo() {

        String dataHoje = LocalDate.now().toString();
        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));

        File dir = new File("evidence/" + dataHoje);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return "evidence/" + dataHoje + "/evidence-" + hora + ".pdf";
    }

    // 🔹 Cabeçalho
    private void adicionarCabecalho(Document document) {

        String dataHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        document.add(new Paragraph("=== RELATÓRIO DE TESTE DE API ==="));
        document.add(new Paragraph("Projeto: " + PROJETO));
        document.add(new Paragraph("Executor: " + EXECUTOR));
        document.add(new Paragraph("Data/Hora Execução: " + dataHora));
        document.add(new Paragraph(" "));
    }

    // 🔹 Request
    private void adicionarRequest(Document document,
                                  HttpRequest request,
                                  String requestBody) {

        document.add(new Paragraph("=== REQUEST ==="));
        document.add(new Paragraph("URI: " + request.uri()));
        document.add(new Paragraph("Método: " + request.method()));

        if (requestBody != null && !requestBody.isEmpty()) {
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Body:"));
            document.add(new Paragraph(requestBody));
        }

        document.add(new Paragraph(" "));
    }

    // 🔹 Response
    private void adicionarResponse(Document document,
                                   int statusCode,
                                   String responseBody) {

        document.add(new Paragraph("=== RESPONSE ==="));
        document.add(new Paragraph("Status Code: " + statusCode));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Body:"));
        document.add(new Paragraph(responseBody));
    }
}