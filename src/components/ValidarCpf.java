package components;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.CpfInvalidoException;


public class ValidarCpf {
    //regex valida cpf
    String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
    //cria o patter pra usar o regex
    Pattern pattern = Pattern.compile(regexCpf);
    //faz a operação
    Matcher matcher;
    public void validar(String validarCpf) throws CpfInvalidoException{
        matcher = pattern.matcher(validarCpf);
        if(matcher.matches()) {
            System.out.println("CPF VERIFICADO E VALIDADO COM SUCESSO!");
            return;
        }
        throw new CpfInvalidoException("CPF INVÁLIDO");
    }
}
