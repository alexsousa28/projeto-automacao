package com.example.demo.api.apiServest.postCriarUsuario;

import com.example.demo.api.apiServest.Endpoint;
import com.example.demo.api.apiServest.model.UsuarioRequest;
import com.example.demo.api.gerarRelatorio.PdfReport;
import com.example.demo.core.context.StepContext;
import com.example.demo.core.utils.JsonUtil;
import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
public class CriarUsuarioLogic {

    private HttpRequest request;
    private HttpResponse<String> response;
    private final HttpClient client = HttpClient.newHttpClient();

    public void enviarRequest() {

        String step = "Quando: Envio request para criar usuário";
        StepContext.setStep(step);
        log.info(step);

        try {
            UsuarioRequest usuarioRequest = UsuarioRequest.builder()
                    .nome("Alex")
                    .email("teste@teste.com.br")
                    .password("abc123")
                    .administrador("true")
                    .build();

            String jsonRequest = JsonUtil.toJson(usuarioRequest);

            this.request = HttpRequest.newBuilder()
                    .uri(URI.create(Endpoint.URL + Endpoint.PATH_USUARIO))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            log.info("===== REQUEST =====");
            log.info("URI: {}", request.uri());
            log.info("Método: {}", request.method());
            log.info("Body: {}", jsonRequest);

            this.response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Status: {}", response.statusCode());
            log.info("Response: {}", response.body());

            // 🔥 Gera PDF
            gerarRelatorio(jsonRequest);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar request", e);
        }
    }

    public void validarResponse() {

        String step = "Então: Valido o retorno da API";
        StepContext.setStep(step);
        log.info(step);

        if (response == null) {
            throw new RuntimeException("Response não pode ser nula");
        }

        int status = response.statusCode();
        String body = response.body();

        if (status != 201) {
            throw new AssertionError("Status esperado 201, mas veio: " + status);
        }

        if (!body.contains("Cadastro realizado com sucesso")) {
            throw new AssertionError("Mensagem de sucesso não encontrada");
        }

        log.info("Validação realizada com sucesso");
    }

    // 🔧 Método isolado para gerar PDF
    private void gerarRelatorio(String jsonRequest) {

        PdfReport report = new PdfReport();

        report.gerarRelatorio(
                response.statusCode(),
                request,
                jsonRequest,
                response.body()
        );
    }
}