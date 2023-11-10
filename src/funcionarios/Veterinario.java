package funcionarios;

import components.EspecializacoesVet;
import components.Matricula;
import components.Setores;
import components.TipoEntidade;

public class Veterinario extends Funcionario{
	private String CRMV;
	private EspecializacoesVet especializacao;
	
	public Veterinario(Double salario, String CPF, String nome, Setores setor, String CRMV, EspecializacoesVet especializacao) {
		super(salario, CPF, nome, setor);
		this.matricula = Matricula.gerarMatricula(TipoEntidade.FUNCIONARIO);
		this.CRMV = CRMV;
		this.especializacao = especializacao;
	}
	
	public String getEscpecializacao() {
		return especializacao.toString();
	}

	public String getCRMV() {
		return this.CRMV;
	}
}
