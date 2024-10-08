import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    private static final String FILE_PATH = "usuarios.xlsx";
    private static final String AGENDAMENTO_PATH = "agendamentos.xlsx";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Criar novo usuário (Create)");
            System.out.println("2 - Ler todos os usuários (Read)");
            System.out.println("3 - Atualizar um usuário (Update)");
            System.out.println("4 - Excluir um usuário (Delete)");
            System.out.println("5 - Agendar entrega ou retirada");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    create(scanner);
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    update(scanner);
                    break;
                case 4:
                    delete(scanner);
                    break;
                case 5:
                    agendar(scanner);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // CREATE
    private static void create(Scanner scanner) throws IOException {
        // Entrada de dados
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite seu endereço: ");
        String endereco = scanner.nextLine();

        // Criar um novo objeto Doador
        Doador doador = new Doador(nome, cpf, email, telefone, endereco);
        salvarDoador(doador);
    }

    // Método para salvar o doador no arquivo
    private static void salvarDoador(Doador doador) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File(FILE_PATH);

        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileIn);
            sheet = workbook.getSheetAt(0);
            fileIn.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Usuários");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nome");
            headerRow.createCell(1).setCellValue("CPF");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Telefone");
            headerRow.createCell(4).setCellValue("Endereço");
        }

        // Adiciona uma nova linha com os dados do doador
        int rowCount = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(rowCount + 1);
        dataRow.createCell(0).setCellValue(doador.getNome());
        dataRow.createCell(1).setCellValue(doador.getCpf());
        dataRow.createCell(2).setCellValue(doador.getEmail());
        dataRow.createCell(3).setCellValue(doador.getTelefone());
        dataRow.createCell(4).setCellValue(doador.getEndereco());

        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOut);
            System.out.println("Usuário salvo com sucesso.");
        } finally {
            workbook.close();
        }
    }

    // READ
    private static void read() throws IOException {
        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
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
        fileIn.close();
    }

    // UPDATE
    private static void update(Scanner scanner) throws IOException {
        System.out.print("Digite o CPF do usuário que deseja atualizar: ");
        String cpf = scanner.nextLine();

        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);
        boolean found = false;

        for (Row row : sheet) {
            Cell cpfCell = row.getCell(1); // CPF na coluna 1
            if (cpfCell != null && cpfCell.getStringCellValue().equals(cpf)) {
                found = true;
                System.out.println("Usuário encontrado. Insira novos dados:");

                System.out.print("Novo nome: ");
                row.getCell(0).setCellValue(scanner.nextLine());

                System.out.print("Novo email: ");
                row.getCell(2).setCellValue(scanner.nextLine());

                System.out.print("Novo telefone: ");
                row.getCell(3).setCellValue(scanner.nextLine());

                System.out.print("Novo endereço: ");
                row.getCell(4).setCellValue(scanner.nextLine());

                break;
            }
        }

        fileIn.close();

        if (found) {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
            System.out.println("Dados atualizados com sucesso.");
        } else {
            workbook.close();
            System.out.println("Usuário com CPF " + cpf + " não encontrado.");
        }
    }

    // DELETE
    private static void delete(Scanner scanner) throws IOException {
        System.out.print("Digite o CPF do usuário que deseja excluir: ");
        String cpf = scanner.nextLine();

        FileInputStream fileIn = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fileIn);
        Sheet sheet = workbook.getSheetAt(0);
        boolean found = false;
        int rowIndex = -1;

        for (Row row : sheet) {
            Cell cpfCell = row.getCell(1); // CPF na coluna 1
            if (cpfCell != null && cpfCell.getStringCellValue().equals(cpf)) {
                rowIndex = row.getRowNum();
                found = true;
                break;
            }
        }

        if (found && rowIndex >= 0) {
            sheet.removeRow(sheet.getRow(rowIndex));

            // Reorganizar as linhas após a exclusão
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
        fileIn.close();
    }

    // AGENDAR
    private static void agendar(Scanner scanner) throws IOException {
        System.out.print("Digite o CPF do doador: ");
        String cpf = scanner.nextLine();

        // Aqui você poderia buscar o doador pelo CPF se necessário
        // Para simplicidade, vamos apenas criar um agendamento

        System.out.print("Digite a data do agendamento (dd/MM/yyyy): ");
        String dataString = scanner.nextLine();
        Date dataAgendada = new Date(); // Parse a dataString para um objeto Date
        // Adicione o código para converter a dataString em um objeto Date

        System.out.print("Digite o tipo de agendamento (entrega ou retirada): ");
        String tipo = scanner.nextLine();

        // Criar um novo objeto Agendamento
        Agendamento agendamento = new Agendamento(1, null, dataAgendada, tipo); // Adicione o objeto Doacao real
        salvarAgendamento(agendamento);
    }

    // Método para salvar o agendamento no arquivo
    private static void salvarAgendamento(Agendamento agendamento) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File(AGENDAMENTO_PATH);

        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileIn);
            sheet = workbook.getSheetAt(0);
            fileIn.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Agendamentos");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Data Agendada");
            headerRow.createCell(2).setCellValue("Tipo");
        }

        // Adiciona uma nova linha com os dados do agendamento
        int rowCount = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(rowCount + 1);
        dataRow.createCell(0).setCellValue(agendamento.getId());
        dataRow.createCell(1).setCellValue(agendamento.getDataAgendada().toString());
        dataRow.createCell(2).setCellValue(agendamento.getTipo());

        try (FileOutputStream fileOut = new FileOutputStream(AGENDAMENTO_PATH)) {
            workbook.write(fileOut);
            System.out.println("Agendamento salvo com sucesso.");
        } finally {
            workbook.close();
        }
    }
}
