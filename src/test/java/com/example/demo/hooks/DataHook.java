package com.example.demo.hooks;

import com.example.demo.core.data.DataManager;
import io.cucumber.java.Before;

public class DataHook {

    @Before("@data")
    public void carregarMassa() {

        DataManager.carregar(
                "src/test/resources/data/login.xlsx",
                "Sheet1"
        );
    }
}