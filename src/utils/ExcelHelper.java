package utils;

import model.Doador;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelHelper {
    private static final String FILE_PATH = "usuarios.xlsx";

    // Função para salvar um novo doador no arquivo Excel
    public static void salvarDoadorNoArquivo(Doador doador) throws IOException {
        Workbook workbook = abrirOuCriarArquivo(FILE_PATH);
        Sheet sheet = workbook.getSheetAt(0);

        if (sheet == null) {
            sheet = workbook.createSheet("Usuários");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nome");
            headerRow.createCell(1).setCellValue("CPF");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Telefone");
            headerRow.createCell(4).setCellValue("Endereço");
        }

        int rowCount = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(rowCount + 1);
        dataRow.createCell(0).setCellValue(doador.getNome());
        dataRow.createCell(1).setCellValue(doador.getCpf());
        dataRow.createCell(2).setCellValue(doador.getEmail());
        dataRow.createCell(3).setCellValue(doador.getTelefone());
        dataRow.createCell(4).setCellValue(doador.getEndereco());

        salvarWorkbook(workbook, FILE_PATH);
        workbook.close();
    }

    // Função para ler todos os usuários do arquivo Excel
    public static void lerUsuarios() throws IOException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                }
            }
            System.out.println();
        }
        workbook.close();
    }

    // Função para atualizar um usuário no arquivo Excel baseado no CPF
    public static void atualizarUsuario(String cpf, String nome, String email, String telefone, String endereco) throws IOException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);
        boolean found = false;

        for (Row row : sheet) {
            Cell cpfCell = row.getCell(1); // CPF está na segunda coluna
            if (cpfCell != null && cpfCell.getStringCellValue().equals(cpf)) {
                found = true;
                // Atualizar os dados conforme inserido, sem modificar o que não for passado
                if (!nome.isEmpty()) row.getCell(0).setCellValue(nome);
                if (!email.isEmpty()) row.getCell(2).setCellValue(email);
                if (!telefone.isEmpty()) row.getCell(3).setCellValue(telefone);
                if (!endereco.isEmpty()) row.getCell(4).setCellValue(endereco);
                break;
            }
        }

        fileIn.close();

        if (found) {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Usuário com CPF " + cpf + " não encontrado.");
        }

        workbook.close();
    }

    // Função para excluir um usuário no arquivo Excel baseado no CPF
    public static void excluirUsuario(String cpf) throws IOException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);
        boolean found = false;
        int rowIndex = -1;

        for (Row row : sheet) {
            Cell cpfCell = row.getCell(1); // CPF está na segunda coluna
            if (cpfCell != null && cpfCell.getStringCellValue().equals(cpf)) {
                rowIndex = row.getRowNum();
                found = true;
                break;
            }
        }

        fileIn.close();

        if (found && rowIndex >= 0) {
            // Exclui a linha e reorganiza as linhas restantes
            sheet.removeRow(sheet.getRow(rowIndex));

            if (rowIndex < sheet.getLastRowNum()) {
                sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1);
            }

            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Usuário excluído com sucesso.");
        } else {
            System.out.println("Usuário com CPF " + cpf + " não encontrado.");
        }

        workbook.close();
    }

    // Método auxiliar para abrir ou criar um novo arquivo Excel
    private static Workbook abrirOuCriarArquivo(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            return new XSSFWorkbook(fileIn);
        } else {
            return new XSSFWorkbook();
        }
    }

    // Método auxiliar para salvar o arquivo Excel
    private static void salvarWorkbook(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }
}
