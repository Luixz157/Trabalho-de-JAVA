package service;

import utils.Agendamento;

public class AgendamentoEntrega implements AgendamentoStrategy {
    @Override
    public void agendar(Agendamento agendamento) {
        System.out.println("Entrega agendada com sucesso para a data: " + agendamento.getDataAgendada());
    }
}
