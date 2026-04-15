package com.example.demo.web.login_orange;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import com.example.demo.core.driver.DriverFactory;

public class LoginOrangeSteps {
    private LoginOrangeLogic loginOrangeLogic;
    private WebDriver driver;
    public LoginOrangeSteps(){
        driver = DriverFactory.getDriver();
        loginOrangeLogic = new LoginOrangeLogic(driver);
    }

    @Given("que precise logar no site orange")
    public void que_precise_logar_no_site_orange() {
        loginOrangeLogic.acessarSiteOrange();
    }

    @When("preencher os dados de ususario e senha corretamente")
    public void preencher_os_dados_de_ususario_e_senha_corretamente() throws InterruptedException {
        loginOrangeLogic.preencherCamposUsuarioSenha();
    }

    @Then("entao devo validar que loguei corretamente")
    public void entao_devo_validar_que_loguei_corretamente() throws InterruptedException {
        loginOrangeLogic.validarLogin();
    }
}
