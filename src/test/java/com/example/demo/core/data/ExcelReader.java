package com.example.demo.core.data;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    /**
     * Lê o Excel e retorna todas as linhas como lista de mapas
     */
    public static List<Map<String, String>> lerExcel(String caminho, String aba) {

        List<Map<String, String>> dados = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(caminho);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(aba);

            if (sheet == null) {
                throw new RuntimeException("A aba não foi encontrada: " + aba);
            }

            Row headerRow = sheet.getRow(0);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) continue;

                Map<String, String> linha = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                    Cell headerCell = headerRow.getCell(j);
                    String chave = formatter.formatCellValue(headerCell);

                    Cell cell = row.getCell(j);
                    String valor = formatter.formatCellValue(cell);

                    linha.put(chave, valor);
                }

                dados.add(linha);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler Excel: " + e.getMessage(), e);
        }

        return dados;
    }

    /**
     * Busca uma linha específica pelo ID
     */
    public static Map<String, String> buscarPorId(
            String caminho,
            String aba,
            String idBuscado) {

        List<Map<String, String>> dados = lerExcel(caminho, aba);

        for (Map<String, String> linha : dados) {

            if (linha.get("id").equalsIgnoreCase(idBuscado)) {
                return linha;
            }
        }

        throw new RuntimeException("ID não encontrado no Excel: " + idBuscado);
    }
}