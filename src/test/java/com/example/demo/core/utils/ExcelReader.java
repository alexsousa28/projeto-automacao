package com.example.demo.core.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static List<Map<String, String>> lerExcel(String caminho, String aba) {

        List<Map<String, String>> dados = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(caminho);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(aba);

            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                Map<String, String> linha = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                    String chave = headerRow.getCell(j).getStringCellValue();
                    Cell cell = row.getCell(j);

                    String valor = (cell == null) ? "" : cell.toString();

                    linha.put(chave, valor);
                }

                dados.add(linha);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dados;
    }
}
