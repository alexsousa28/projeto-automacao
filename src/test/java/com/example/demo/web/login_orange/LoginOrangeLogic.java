package com.example.demo.web.login_orange;

import com.example.demo.core.data.DataManager;
import com.example.demo.core.evidence.PdfEvidenceManager;
import com.example.demo.core.utils.ScreenshotUtil;
import com.example.demo.core.context.StepContext;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Map;

@Log4j2
public class LoginOrangeLogic {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginOrangePage page;

    public LoginOrangeLogic(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.page = new LoginOrangePage(driver);
    }

    public void acessarSiteOrange(){
        String step = "Dado que: O usuário acessa o site Orange HRM";
        StepContext.setStep(step);
        log.info(step);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait.until(ExpectedConditions.visibilityOf(page.getInputUsername()));

        log.info("Acessando site: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //Evidencia que o usuário foi direcionado para o site desejado
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );
    }

    public void preencherCamposUsuarioSenha() throws InterruptedException {

        Map<String, String> dados = DataManager.proximaMassa();
        String username = dados.get("username");
        String password = dados.get("password");

        String step = "Quando: O usuário preenche os campos usuário e senha";
        StepContext.setStep(step);
        log.info(step);

        log.info("Preenchendo o campo usuário...");
        wait.until(ExpectedConditions.visibilityOf(page.getInputUsername()))
                .sendKeys(username);

        log.info("Preenchendo campo senha...");
        wait.until(ExpectedConditions.visibilityOf(page.getInputPassword()))
                .sendKeys(password);

        //Evidencia que o usuário preencheu o login e senha... "IMPORTANTE QUE SEJA EVIDENCIADO ANTES DA AÇÃO DE CLIQUE"
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );
        log.info("Clicando no botão login...");
        wait.until(ExpectedConditions.elementToBeClickable(page.getBtnLogin()))
                .click();

        Thread.sleep(2000);
    }

    public void validarLogin(){
        String step = "Então: O usuário consegue validar que o login foi feito com sucesso";
        StepContext.setStep(step);
        log.info(step);

        boolean sucesso = wait.until(
                ExpectedConditions.visibilityOf(page.getValidarLogin())
        ).isDisplayed();

        Assert.assertTrue("Login falhou", sucesso);
        //Evidenciando que o usuário logou no site
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );

    }
}