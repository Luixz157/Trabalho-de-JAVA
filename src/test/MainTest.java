import org.junit.Test;
import model.Doador;
import utils.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MainTest {

    // Teste para CPF válido
    @Test
    public void testCPFValido() {
        assertTrue(Main.validarCPF("12345678901"));  // CPF válido
    }

    // Teste para CPF inválido (menos de 11 dígitos)
    @Test
    public void testCPFComMenosDigitos() {
        assertFalse(Main.validarCPF("1234567"));  // CPF inválido
    }

    // Teste para CPF inválido (com letras)
    @Test
    public void testCPFComLetras() {
        assertFalse(Main.validarCPF("12345a78901"));  // CPF inválido
    }

    // Teste para Data válida
    @Test
    public void testDataValida() {
        Date data = Main.validarData("09/10/2024");
        assertNotNull(data);  // Verifica se a data foi validada corretamente
    }

    // Teste para Data inválida (formato incorreto)
    @Test
    public void testDataFormatoInvalido() {
        Date data = Main.validarData("09-10-2024");
        assertNull(data);  // Verifica se a data foi identificada como inválida
    }

    // Teste para Data inválida (dia inexistente)
    @Test
    public void testDataInvalida() {
        Date data = Main.validarData("32/12/2024");
        assertNull(data);  // Verifica se a data foi identificada como inválida
    }

    // Teste para criação de Doador
    @Test
    public void testCriarDoador() {
        Doador doador = new Doador("João", "12345678901", "joao@email.com", "11987654321", "Rua A");

        assertEquals("João", doador.getNome());  // Verifica se o nome está correto
        assertEquals("12345678901", doador.getCpf());  // Verifica o CPF
        assertEquals("joao@email.com", doador.getEmail());  // Verifica o email
        assertEquals("11987654321", doador.getTelefone());  // Verifica o telefone
        assertEquals("Rua A", doador.getEndereco());  // Verifica o endereço
    }

    // Teste para agendamento de entrega
    @Test
    public void testAgendamentoEntrega() throws Exception {
        // Criar um mock doador
        Doador doador = new Doador("João", "12345678901", "joao@email.com", "11987654321", "Rua A");

        // Simular uma data válida
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAgendada = sdf.parse("10/10/2024");

        // Criação do agendamento de entrega
        Agendamento agendamento = new Agendamento(1, doador, dataAgendada, new AgendamentoEntrega());
        agendamento.realizarAgendamento(); // Deve imprimir "Entrega agendada com sucesso para a data: ..."

        assertEquals(1, agendamento.getId());
        assertEquals(dataAgendada, agendamento.getDataAgendada());
        assertEquals(doador, agendamento.getDoador());
    }

    // Teste para agendamento de retirada
    @Test
    public void testAgendamentoRetirada() throws Exception {
        // Criar um mock doador
        Doador doador = new Doador("João", "12345678901", "joao@email.com", "11987654321", "Rua A");

        // Simular uma data válida
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAgendada = sdf.parse("15/10/2024");

        // Criação do agendamento de retirada
        Agendamento agendamento = new Agendamento(2, doador, dataAgendada, new AgendamentoRetirada());
        agendamento.realizarAgendamento(); // Deve imprimir "Retirada agendada com sucesso para a data: ..."

        assertEquals(2, agendamento.getId());
        assertEquals(dataAgendada, agendamento.getDataAgendada());
        assertEquals(doador, agendamento.getDoador());
    }
}
