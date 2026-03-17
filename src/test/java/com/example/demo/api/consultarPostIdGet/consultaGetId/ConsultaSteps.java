package com.example.demo.api.consultarPostIdGet.consultaGetId;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class ConsultaSteps {
    private ConsultaLogic consultaLogic;

    public ConsultaSteps() {
        consultaLogic = new ConsultaLogic();
    }
    @Given("que precise fazer a requisicao para consulta de um post")
    public void que_precise_fazer_a_requisicao_para_consulta_de_um_post() {
        consultaLogic.montarRequisicao();

    }
    @When("chamar a api com os parametros corretos")
    public void chamar_a_api_com_os_parametros_corretos() throws IOException, InterruptedException {
        consultaLogic.fazerRequisicao();

    }
    @Then("então devo receber a resposta desejada")
    public void então_devo_receber_a_resposta_desejada() throws IOException {
        consultaLogic.validarResponse();
    }
}
