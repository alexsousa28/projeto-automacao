package com.example.demo.web.login_orange;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class LoginOrangeLogic {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginOrangeLogic(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // STEP 1
    public void acessarSiteOrange(){

        log.info("Dado que: O usuário acessa site Orange HRM");

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // espera a página carregar
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_USERNAME()
        ));
    }

    // STEP 2
    public void preencherCamposUsuarioSenha(){

        log.info("Quando: O usuário preenche usuário e senha");

        log.info("Preenchendo username...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_USERNAME()
        )).sendKeys("Admin");

        log.info("Preenchendo password...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_PASSWORD()
        )).sendKeys("admin123");

        log.info("Clicando no botão login...");
        wait.until(ExpectedConditions.elementToBeClickable(
                LoginOrangePage.getBTN_LOGIN()
        )).click();
    }

    // STEP 3
    public void validarLogin(){

        log.info("Então: O usuário valida que o login foi realizado com sucesso");

        // espera elemento da home (dashboard)
        boolean loginSucesso = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginOrangePage.getVALIDAR_LOGIN()
                )
        ).isDisplayed();

        // valida
        Assert.assertTrue("Login não foi realizado com sucesso", loginSucesso);

        // valida adicional (URL)
        Assert.assertTrue("URL incorreta após login",
                driver.getCurrentUrl().contains("dashboard"));
    }
}