package com.example.demo.api.consultarPostIdGet.consultaGetId;

import com.example.demo.core.evidence.PdfReportApi;
import com.example.demo.api.consultarPostIdGet.EndPoints;
import com.example.demo.api.consultarPostIdGet.consultaGetId.consultaIdResponse.ConsultaIdResponse;
import io.cucumber.messages.ndjson.internal.com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.apache.http.HttpStatus.SC_OK;

@Getter
@Log4j2
public class ConsultaLogic {

    private HttpRequest request;
    private HttpResponse<String> response;
    private ConsultaIdResponse consultaIdResponse;
    private PdfReportApi pdfReport;

    public ConsultaLogic() {
        pdfReport = new PdfReportApi();
    }
    public void montarRequisicao() {

        request = HttpRequest.newBuilder()
                .uri(URI.create(EndPoints.HOST + EndPoints.PATH))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        log.info("========== REQUEST ==========");
        log.info("Endpoint: {}", request.uri());
        log.info("Method: {}", request.method());

    }
    public void fazerRequisicao() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        log.info("Enviando a requisição... ");
    }

    public void validarResponse() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        consultaIdResponse = mapper.readValue(response.body(), ConsultaIdResponse.class);

        int statusCode = response.statusCode();

        log.info("========== RESPONSE ==========");
        log.info("Status Code: {}", statusCode);
        log.info("Body: {}", response.body());

        if(statusCode == SC_OK) {
            Assert.assertEquals(1, consultaIdResponse.getUserId());
            Assert.assertEquals(1, consultaIdResponse.getId());
        }

        pdfReport.gerarRelatorio(
                statusCode,
                request,
                null, //se for requisição GET mandar null;
                response.body()
        );
    }

}

