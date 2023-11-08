package components;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Matricula {

    public String numero_matricula;

    public static Set<Matricula> matriculas_pet = new HashSet<>();
    public static Set<Matricula> matriculas_tutor = new HashSet<>();
    public static Set<Matricula> matriculas_funcionario = new HashSet<>();

    public static Set<Matricula> lista_matriculas = new HashSet<>();

    private static final String CARACTERES_PERMITIDOS = "123456789";
    private static final int TAMANHO_MATRICULA = 4;

    private Matricula(StringBuilder numero_matricula) {this.numero_matricula = String.valueOf(numero_matricula);}

    public static Matricula gerarMatricula(TipoEntidade tipo_entidade) {
        StringBuilder matricula = new StringBuilder();
        Random random = new Random();

        while (true) {
            for (int i = 0; i < TAMANHO_MATRICULA; i++) {
                int index = random.nextInt(CARACTERES_PERMITIDOS.length());
                char caractere = CARACTERES_PERMITIDOS.charAt(index);
                matricula.append(caractere);
            }

            Matricula nova_matricula = new Matricula(matricula);

            if (!lista_matriculas.contains(nova_matricula)) {
                lista_matriculas.add(nova_matricula);

                switch (tipo_entidade) {
                    case PET:
                        matriculas_pet.add(nova_matricula);
                        return nova_matricula;
                    case TUTOR:
                        matriculas_tutor.add(nova_matricula);
                        return nova_matricula;
                    case FUNCIONARIO:
                        matriculas_funcionario.add(nova_matricula);
                        return nova_matricula;
                    default:
                        break;
                }
            }
        }
    }


    }

