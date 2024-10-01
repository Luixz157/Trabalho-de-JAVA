import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

        //LIB PARA ENTRADA DE DADOS
                    Scanner scanner = new Scanner(System.in);

        //ENTRAR COM NOME E CPF DO USUARIO/
                    System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();

        //MANIPULACAO DE ARQUIVO DE EXCEL/
                    // Criar um novo arquivo Excel
                    Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Usuários");

            // Adicionar dados do usuário
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(nome);
            dataRow.createCell(1).setCellValue(cpf);


            //USANDO TRY-CATCH PARA GARANTIR A INSERÇÃO
            // Salvar o arquivo
            try (FileOutputStream fileOut = new FileOutputStream("usuarios.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Dados salvos em usuarios.xlsx");
            } catch (IOException e) {
                System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o workbook: " + e.getMessage());
                }
            }

            //ENCERRAR A ENTRADA DE DADOS
            scanner.close();


        }

    }
