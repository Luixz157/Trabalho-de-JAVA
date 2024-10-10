package utils;

import java.util.Date;

import model.Doador;
import service.AgendamentoStrategy;

public class Agendamento {
    private int id;
    private Doador doador;
    private Date dataAgendada;
    private AgendamentoStrategy strategy;

    public Agendamento(int id, Doador doador, Date dataAgendada, AgendamentoStrategy strategy) {
        this.id = id;
        this.doador = doador;
        this.dataAgendada = dataAgendada;
        this.strategy = strategy;
    }

    public void realizarAgendamento() {
        strategy.agendar(this);
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public Doador getDoador() {
        return doador;
    }

    public int getId() {
        return 0;
    }
}
