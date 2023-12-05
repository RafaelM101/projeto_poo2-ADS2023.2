package components;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import exceptions.*;



public class Validar implements Terminal{
    private static final String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private static final String regexTelefone = "^\\((0\\d{2}|\\d{2})\\)\\s?9\\d{4}-?\\d{4}$";

    private static final String regexLetras = ".*[0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";
    private static final String regexCRMV = "^CRMV-(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO) \\d{4}$";

    private static final String regexData = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    private static final String regexMatricula = "^\\d{4}$";

    private static final String regexHora = "^(0[8-9]|1[0-7]):(00|30)$";

    static Pattern patternCPF = Pattern.compile(regexCpf);
    static Pattern patternEmail = Pattern.compile(regexEmail);
    static Pattern patternTelefone = Pattern.compile(regexTelefone);
    static Pattern patternLetras = Pattern.compile(regexLetras);
    static Pattern patternCRMV = Pattern.compile(regexCRMV);
    static Pattern patternDate = Pattern.compile(regexData);
    static Pattern patternMatricula = Pattern.compile(regexMatricula);
    
    private static final Pattern patternHora = Pattern.compile(regexHora);
  

    static Matcher matcher;
    public static boolean validarCPF(String validarCpf) throws CpfInvalidoException{
        matcher = patternCPF.matcher(validarCpf);
        if(matcher.matches()) {
            return true;
        }
        throw new CpfInvalidoException(NEGRITO + VERMELHO+"CPF INVÁLIDO! INSIRA NO PADRÃO xxx.xxx.xxx-xx"+RESETAR);
    }

    public static boolean validarEmail(String validarEmail) throws EmailInvalidoException{
        matcher = patternEmail.matcher(validarEmail);
        if(matcher.matches()) {
            return true;
        }
        throw new EmailInvalidoException(NEGRITO + VERMELHO+"EMAIL INVÁLIDO!"+RESETAR);
    }

    public static boolean validarTelefone(String validarTelefone) throws TelefoneInvalidoException{
        matcher = patternTelefone.matcher(validarTelefone);
        if (matcher.matches()) {
            return true;
        }
        throw new TelefoneInvalidoException(NEGRITO + VERMELHO+ "TELEFONE INVÁLIDO!" +RESETAR);
    }

    public static boolean validarLetras(String validarLetras) throws SomenteLetrasException{
        matcher = patternLetras.matcher(validarLetras);
        if(!matcher.matches()) {
            return true;
        }
        throw new SomenteLetrasException(NEGRITO + VERMELHO+"INSIRA SOMENTE LETRAS!"+RESETAR);
    }

    public static boolean validarCRMV(String validarCRMV) throws CRMVInvalidoException{
        matcher = patternCRMV.matcher(validarCRMV);
        if (matcher.matches()) {
            return true;
        }
        throw new CRMVInvalidoException(NEGRITO + VERMELHO+"CRMV INVÁLIDO! Insira no padrão CRMV-UF XXXX , onde UF representa o Estado do CRMV."+RESETAR);
    }

    public static boolean ValidarDate(String validarData) throws DataInvalidaException {
        matcher = patternDate.matcher(validarData);
        if (matcher.matches()){
            return true;
        }
        throw new DataInvalidaException(VERMELHO + "\n\t\t\tDATA INVÁLIDA. INSIRA NO FORMATO dd/mm/aaaa." + RESETAR);
    }

    public static boolean ValidarMatricula(String validarMatricula) throws MatriculaInvalidaException {
        matcher = patternMatricula.matcher(validarMatricula);
        if (matcher.matches()) {
            return true;
        }
        throw new MatriculaInvalidaException(NEGRITO+VERMELHO+ "MATRÍCULA INVÁLIDA!" +RESETAR);
    }

    public static boolean validarHora(String validarHora) throws HoraInvalidaException{
        matcher = patternHora.matcher(validarHora);
        if(matcher.matches()){
            return true;
        }
        throw new HoraInvalidaException(VERMELHO + "\n\t\t\tHORA INVÁLIDA. INSIRA NO FORMATO hh:mm . Lembrando de se atentar aos horários disponíveis." + RESETAR);
    }
  
}
