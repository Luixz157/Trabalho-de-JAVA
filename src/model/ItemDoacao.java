package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemDoacao {

    private int id;
    private String nome;
    private String descricao;
    private String categoria;

    // Lista de itens de doação (em memória)
    private static List<ItemDoacao> itens = new ArrayList<>();

    public ItemDoacao(int id, String nome, String descricao, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    // Método para adicionar um item de doação
    public static void adicionarItemDoacao(Scanner scanner) {
        System.out.print("Digite o ID do item: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Digite o nome do item: ");
        String nome = scanner.nextLine();

        System.out.print("Digite uma descrição do item: ");
        String descricao = scanner.nextLine();

        String categoria;
        do {
            System.out.println("Escolha uma categoria para o item:");
            System.out.println("1 - Roupas");
            System.out.println("2 - Alimentos");
            System.out.println("3 - Brinquedos");
            System.out.println("4 - Eletrônicos");
            System.out.println("5 - Móveis");
            System.out.println("6 - Outros");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    categoria = "Roupas";
                    break;
                case 2:
                    categoria = "Alimentos";
                    break;
                case 3:
                    categoria = "Brinquedos";
                    break;
                case 4:
                    categoria = "Eletrônicos";
                    break;
                case 5:
                    categoria = "Móveis";
                    break;
                case 6:
                    System.out.print("Digite a descrição da categoria: ");
                    categoria = scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma categoria válida.");
                    categoria = null;
                    break;
            }
        } while (categoria == null);

        // Criar um novo objeto ItemDoacao e adicioná-lo à lista
        ItemDoacao novoItem = new ItemDoacao(id, nome, descricao, categoria);
        itens.add(novoItem); // Adiciona o item à lista
        System.out.println("Item de doação adicionado com sucesso.");
    }


    // Método para listar os itens de doação
    public static void listarItensDoacao() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item de doação cadastrado.");
        } else {
            for (ItemDoacao item : itens) {
                System.out.println(item);
            }
        }
    }

    @Override
    public String toString() {
        return "model.ItemDoacao {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
