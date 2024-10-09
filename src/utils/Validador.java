package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validador {

    // Validação de CPF
    public static boolean validarCPF(String cpf) {
        String regex = "\\d{11}"; // 11 dígitos numéricos

        if (cpf.matches(regex)) {
            // Aqui você pode adicionar validação adicional para o cálculo de CPF, se quiser
            return true;
        } else {
            System.out.println("CPF inválido. Deve conter 11 dígitos.");
            return false;
        }
    }

    // Validação de Data
    public static Date validarData(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Isso garante que a data seja estritamente no formato especificado
        try {
            return sdf.parse(data); // Se a data for válida, ela será convertida em um objeto Date
        } catch (ParseException e) {
            System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            return null;
        }
    }
}
