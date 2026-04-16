package com.example.demo.core.data;

import java.util.*;

public class DataManager {

    private static List<Map<String, String>> massa;
    private static int index = 0;

    public static void carregar(String caminho, String aba) {
        massa = ExcelReader.lerExcel(caminho, aba);
        index = 0;
    }

    public static Map<String, String> proximaMassa() {

        if (massa == null || massa.isEmpty()) {
            throw new RuntimeException("Massa de dados não carregada");
        }

        if (index >= massa.size()) {
            index = 0; // reinicia (ou pode parar)
        }

        return massa.get(index++);
    }
}