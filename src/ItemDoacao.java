import java.util.ArrayList;
import java.util.List;

public class ItemDoacao {
    private int id;
    private String nome;
    private String descricao;
    private String categoria;

    // Lista para armazenar os itens de doação
    private static List<ItemDoacao> itens = new ArrayList<>();

    public ItemDoacao(int id, String nome, String descricao, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        // Adiciona o item à lista
        itens.add(this);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void atualizarItem(String nome, String descricao, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public static void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item de doação cadastrado.");
            return;
        }
        for (ItemDoacao item : itens) {
            System.out.println(item);
        }
    }

    @Override
    public String toString() {
        return "ItemDoacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
