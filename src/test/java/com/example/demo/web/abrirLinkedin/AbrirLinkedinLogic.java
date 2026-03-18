package com.example.demo.web.abrirLinkedin;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AbrirLinkedinLogic {

    private WebDriver driver;

    public AbrirLinkedinLogic(WebDriver driver){
        this.driver = driver;
    }

    public void acessarSite(){
        String step = "Acessando LinkedIn";
        log.info("Abrindo Linkedin");

        driver.get("https://www.linkedin.com/");

    }
}