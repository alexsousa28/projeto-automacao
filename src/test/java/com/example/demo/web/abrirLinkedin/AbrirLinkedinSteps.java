package com.example.demo.web.abrirLinkedin;

import com.example.demo.web.driver.DriverFactory;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class AbrirLinkedinSteps {

    private WebDriver driver;
    private AbrirLinkedinLogic abrirLinkedinLogic;

    public AbrirLinkedinSteps(){

        driver = DriverFactory.getDriver();
        abrirLinkedinLogic = new AbrirLinkedinLogic(driver);

    }

    @Given("que preciso acessar o linkedin")
    public void que_preciso_acessar_o_linkedin() {

        abrirLinkedinLogic.acessarSite();

    }
}