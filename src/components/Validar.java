package components;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CRMVInvalidoException;
import exceptions.CpfInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.SomenteLetrasException;


public class Validar implements Terminal{
    //regex valida cpf
    private static final String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    //regex valida email
    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    //regex valida letras
    private static final String regexLetras = ".*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
    //regex valida CRMV
    private static final String regexCRMV = "^CRMV-(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO) \\d{4}$";
    //cria o pattern pra usar o regex
    static Pattern patternCPF = Pattern.compile(regexCpf);
    static Pattern patternEmail = Pattern.compile(regexEmail);
    static Pattern patternLetras = Pattern.compile(regexLetras);
    static Pattern patternCRMV = Pattern.compile(regexCRMV);

    //faz a operação
    static Matcher matcher;
    public static boolean validarCPF(String validarCpf) throws CpfInvalidoException{
        matcher = patternCPF.matcher(validarCpf);
        if(matcher.matches()) {
            return true;
        }
        throw new CpfInvalidoException(VERMELHO+"CPF INVÁLIDO!"+RESETAR);
    }

    public static boolean validarEmail(String validarEmail) throws EmailInvalidoException{
        matcher = patternEmail.matcher(validarEmail);
        if(matcher.matches()) {
            return true;
        }
        throw new EmailInvalidoException(VERMELHO+"EMAIL INVÁLIDO!"+RESETAR);
    }

    public static boolean validarLetras(String validarLetras) throws SomenteLetrasException{
        matcher = patternLetras.matcher(validarLetras);
        if(!matcher.matches()) {
            return true;
        }
        throw new SomenteLetrasException(VERMELHO+"INSIRA SOMENTE LETRAS!"+RESETAR);
    }

    public static boolean valarCRMV(String validarCRMV) throws CRMVInvalidoException{
        matcher = patternCRMV.matcher(validarCRMV);
        if (matcher.matches()) {
            return true;
        }
        throw new CRMVInvalidoException(VERMELHO+"CRMV INVÁLIDO!"+RESETAR);
    }
}
