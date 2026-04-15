package com.example.demo.core.context;

public class StepContext {

    private static String step;

    public static void setStep(String nomeStep) {
        step = nomeStep;
    }

    public static String getStep() {
        return step;
    }
}