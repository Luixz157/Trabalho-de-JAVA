package service;

import utils.Agendamento;

public class AgendamentoRetirada implements AgendamentoStrategy {
    @Override
    public void agendar(Agendamento agendamento) {
        System.out.println("Retirada agendada com sucesso para a data: " + agendamento.getDataAgendada());
    }
}
