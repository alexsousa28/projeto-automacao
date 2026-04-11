package com.example.demo.web.login_orange;

import com.example.demo.web.screenshot.PdfEvidenceManager;
import com.example.demo.web.screenshot.ScreenshotUtil;
import com.example.demo.web.screenshot.StepContext;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import static com.example.demo.web.screenshot.StepContext.step;

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
        String step = "Dado que: O usuário acessa site Orange HRM";
        StepContext.setStep(step);
        log.info(step);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // espera a página carregar
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_USERNAME()
        ));
        //Evidencia após carregar a página!
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );
    }

    // STEP 2
    public void preencherCamposUsuarioSenha(){
        String step = "Quando: O usuário preenche usuário e senha";
        StepContext.setStep(step);
        log.info(step);

        log.info("Preenchendo username...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_USERNAME()
        )).sendKeys("Admin");

        log.info("Preenchendo password...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                LoginOrangePage.getINPUT_PASSWORD()
        )).sendKeys("admin123");

        //Evidencia após preencher os campos login e senha "Importante ser antes da ação de click!"
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );
        log.info("Clicando no botão login...");
        wait.until(ExpectedConditions.elementToBeClickable(
                LoginOrangePage.getBTN_LOGIN()
        )).click();
    }

    // STEP 3
    public void validarLogin(){
        String step = "Então: O usuário valida que o login foi realizado com sucesso";
        StepContext.setStep(step);
        log.info(step);

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
        //Evidencia a validação de login!
        PdfEvidenceManager.adicionarStep(
                step,
                ScreenshotUtil.tirarScreenshot()
        );
    }
}