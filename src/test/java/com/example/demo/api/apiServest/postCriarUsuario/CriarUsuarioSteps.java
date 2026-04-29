package com.example.demo.api.apiServest.postCriarUsuario;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CriarUsuarioSteps {
    private CriarUsuarioLogic criarUsuarioLogic;
    public CriarUsuarioSteps(){
        criarUsuarioLogic = new CriarUsuarioLogic();
    }

    @When("preencher os dados e fazer a chamada da api")
    public void preencher_os_dados_e_fazer_a_chamada_da_api() {
        criarUsuarioLogic.enviarRequest();
    }

    @Then("devo validar a criação do usuario corretamente")
    public void devo_validar_a_criação_do_usuario_corretamente() {
        criarUsuarioLogic.validarResponse();
    }
}
