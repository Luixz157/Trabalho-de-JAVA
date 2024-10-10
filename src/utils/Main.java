package utils;

import model.Doador;
import model.ItemDoacao;
import service.AgendamentoEntrega;
import service.AgendamentoRetirada;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Criar novo usuário (Create)");
            System.out.println("2 - Ler todos os usuários (Read)");
            System.out.println("3 - Atualizar um usuário (Update)");
            System.out.println("4 - Excluir um usuário (Delete)");
            System.out.println("5 - Agendar entrega ou retirada");
            System.out.println("6 - Adicionar item de doação");
            System.out.println("7 - Listar itens de doação");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    criarNovoUsuario(scanner);
                    break;
                case 2:
                    lerUsuarios();
                    break;
                case 3:
                    atualizarUsuario(scanner);
                    break;
                case 4:
                    excluirUsuario(scanner);
                    break;
                case 5:
                    agendar(scanner);
                    break;
                case 6:
                    ItemDoacao.adicionarItemDoacao(scanner);
                    break;
                case 7:
                    ItemDoacao.listarItensDoacao();
                    break;
                case 0:
                    System.out.println("Sistema Encerrado!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // CREATE
    private static void criarNovoUsuario(Scanner scanner) throws IOException {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        // Validação de CPF
        String cpf;
        do {
            System.out.print("Digite seu CPF (11 dígitos): ");
            cpf = scanner.nextLine();
        } while (!validarCPF(cpf)); // Chama a função para validar o CPF

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite seu endereço: ");
        String endereco = scanner.nextLine();

        Doador doador = new Doador(nome, cpf, email, telefone, endereco);
        ExcelHelper.salvarDoadorNoArquivo(doador);
    }

    // READ
    private static void lerUsuarios() throws IOException {
        ExcelHelper.lerUsuarios();  // Função auxiliar para ler usuários do Excel
    }

    // UPDATE
    private static void atualizarUsuario(Scanner scanner) throws IOException {
        System.out.print("Digite o CPF do usuário que deseja atualizar: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o novo nome (ou deixe em branco para não alterar): ");
        String nome = scanner.nextLine();

        System.out.print("Digite o novo email (ou deixe em branco para não alterar): ");
        String email = scanner.nextLine();

        System.out.print("Digite o novo telefone (ou deixe em branco para não alterar): ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o novo endereço (ou deixe em branco para não alterar): ");
        String endereco = scanner.nextLine();

        ExcelHelper.atualizarUsuario(cpf, nome, email, telefone, endereco);
    }

    // DELETE
    private static void excluirUsuario(Scanner scanner) throws IOException {
        System.out.print("Digite o CPF do usuário que deseja excluir: ");
        String cpf = scanner.nextLine();

        ExcelHelper.excluirUsuario(cpf);  // Função auxiliar para excluir usuário
    }

    // AGENDAR ENTREGA OU RETIRADA
    private static void agendar(Scanner scanner) {
        String tipo;
        do {
            System.out.print("Digite o tipo de agendamento (entrega ou retirada): ");
            tipo = scanner.nextLine().toLowerCase();

            if (!tipo.equals("entrega") && !tipo.equals("retirada")) {
                System.out.println("Opção inválida. Por favor, escolha entre 'entrega' ou 'retirada'.");
            }
        } while (!tipo.equals("entrega") && !tipo.equals("retirada"));

        // Validação da data
        Date dataAgendada;
        do {
            System.out.print("Digite a data do agendamento (dd/MM/yyyy): ");
            String dataInput = scanner.nextLine();
            dataAgendada = validarData(dataInput); // Valida a data
        } while (dataAgendada == null); // Repetir até que uma data válida seja inserida

        // Criar um mock doador para o exemplo
        Doador doador = new Doador("João", "12345678900", "joao@email.com", "11987654321", "Rua A");

        Agendamento agendamento;

        if (tipo.equals("entrega")) {
            agendamento = new Agendamento(1, doador, dataAgendada, new AgendamentoEntrega());
        } else {
            agendamento = new Agendamento(1, doador, dataAgendada, new AgendamentoRetirada());
        }

        agendamento.realizarAgendamento();
    }

    // Validação de CPF
    public static boolean validarCPF(String cpf) {
        // Expressão regular para validar o CPF com 11 dígitos numéricos
        String regex = "\\d{11}";
        if (cpf.matches(regex)) {
            return true;
        } else {
            System.out.println("CPF inválido. Deve conter 11 dígitos.");
            return false;
        }
    }

    // Validação de Data
    public static Date validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Isso força a validação estrita da data
        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            return null;
        }
    }
}
