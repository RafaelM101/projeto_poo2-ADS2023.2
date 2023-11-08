package servicos;

import java.util.Comparator;

public class ServicoComparator implements Comparator<Servico> {

    @Override
    public int compare(Servico s1, Servico s2) {
        int dataComparison = s1.getData_servico().compareTo(s2.getData_servico());
        if (dataComparison == 0) {
            return s1.getHora_servico().compareTo(s2.getHora_servico());
        }
        return dataComparison;
    }
}
