package components;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CpfInvalidoException;


public class Validar {
    //regex valida cpf
    private static String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    //cria o pattern pra usar o regex
    static Pattern pattern = Pattern.compile(regexCpf);
    //faz a operação
    static Matcher matcher;
    public static boolean validarCPF(String validarCpf) throws CpfInvalidoException{
        matcher = pattern.matcher(validarCpf);
        if(matcher.matches()) {
            System.out.println("CPF VERIFICADO E VALIDADO COM SUCESSO!");
            return true;
        }
        throw new CpfInvalidoException("CPF INVÁLIDO");
    }
}
