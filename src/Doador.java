public class Doador {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    public Doador(String nome, String cpf, String email, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    // Método para exibir informações do doador
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + endereco);
    }

    // Método para atualizar informações do doador
    public void atualizarInformacoes(String nome, String email, String telefone, String endereco) {
        if (nome != null) this.nome = nome;
        if (email != null) this.email = email;
        if (telefone != null) this.telefone = telefone;
        if (endereco != null) this.endereco = endereco;
    }
}
