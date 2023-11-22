package components;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CpfInvalidoException;
import exceptions.EmailInvalidoException;


public class Validar implements Terminal{
    //regex valida cpf
    private static String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    //regex valida email
    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    //cria o pattern pra usar o regex
    static Pattern patternCPF = Pattern.compile(regexCpf);
    static Pattern patternEmail = Pattern.compile(regexEmail);

    //faz a operação
    static Matcher matcher;
    public static boolean validarCPF(String validarCpf) throws CpfInvalidoException{
        matcher = patternCPF.matcher(validarCpf);
        if(matcher.matches()) {
            return true;
        }
        throw new CpfInvalidoException(VERMELHO+"CPF INVÁLIDO"+RESETAR);
    }

    public static boolean validarEmail(String validarEmail) throws EmailInvalidoException{
        matcher = patternEmail.matcher(validarEmail);
        if(matcher.matches()) {
            return true;
        }
        throw new EmailInvalidoException(VERMELHO+"EMAIL INVÁLIDO!"+RESETAR);
    }
}
