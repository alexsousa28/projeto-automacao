package com.example.demo.hooks;

import com.example.demo.core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHook {

    @Before("@web")
    public void iniciarBrowser(){
        DriverFactory.getDriver();
    }

    @After("@web")
    public void fecharBrowser(){
        DriverFactory.quitDriver();
    }
}