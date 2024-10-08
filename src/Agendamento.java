import java.util.Date;

public class Agendamento {
    private int id;
    private Doador doador;
    private Date dataAgendada;
    private String tipo; // "entrega" ou "retirada"

    // Construtor
    public Agendamento(int id, Doador doador, Date dataAgendada, String tipo) {
        this.id = id;
        this.doador = doador;
        this.dataAgendada = dataAgendada;
        this.tipo = tipo;
    }

    // Métodos de agendamento
    public void agendarEntrega() {
        System.out.println("Entrega agendada com sucesso para a data: " + dataAgendada);
    }

    public void agendarRetirada() {
        System.out.println("Retirada agendada com sucesso para a data: " + dataAgendada);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doador getDoacao() {
        return doador;
    }

    public void setDoacao(Doador doador) {
        this.doador = doador;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
