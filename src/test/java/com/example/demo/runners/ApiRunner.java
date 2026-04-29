package com.example.demo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
     features = "src/test/resources/features",          // Caminho das features
     glue = "com.example.demo",                         // Caminho dos steps
     plugin = {"json:target/cucumber-report.json"},     // Relatórios
     monochrome = true,                                 // Deixa os logs mais limpos
     tags = "@235467"                                   // Tags para filtrar/cenarios
)
public class ApiRunner {
}
