package com.example.demo.web.navegador;

import com.example.demo.web.driver.DriverFactory;
import io.cucumber.java.After;

public class WebHooks {

    @After
    public void fecharBrowser(){

        DriverFactory.quitDriver();

    }
}
