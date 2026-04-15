package com.example.demo.hooks;

import com.example.demo.core.context.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ScenarioHook {

    @Before
    public void pegarId(Scenario scenario){
        for(String tag: scenario.getSourceTagNames()){

            if (tag.startsWith("@")){
                ScenarioContext.setScenarioId(tag.replace("@", ""));
                break;
            }
        }
    }
}
