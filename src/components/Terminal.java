package components;

public interface Terminal {


    // Formatação de texto
    public static final String NEGRITO = "\u001B[1m";
    public static final String FRACO = "\u001B[2m";

    // Cores do texto
    public static final String RESETAR = "\u001B[0m";
    public static final String PRETO = "\u001B[30m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BRANCO = "\u001B[37m";

    // Fundo do texto
    public static final String FUNDO_PRETO = "\u001B[40m";
    public static final String FUNDO_VERMELHO = "\u001B[41m";
    public static final String FUNDO_VERDE = "\u001B[42m";
    public static final String FUNDO_AMARELO = "\u001B[43m";
    public static final String FUNDO_AZUL = "\u001B[44m";
    public static final String FUNDO_MAGENTA = "\u001B[45m";
    public static final String FUNDO_CYAN = "\u001B[46m";
    public static final String FUNDO_BRANCO = "\u001B[47m";

    // Estilos adicionais
    public static final String SUBLINHADO = "\u001B[4m";
    public static final String PISCAR = "\u001B[5m";
    public static final String INVERTIDO = "\u001B[7m";
    public static final String OCULTO = "\u001B[8m";
}
