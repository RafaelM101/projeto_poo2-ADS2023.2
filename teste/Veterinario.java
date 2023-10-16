public class Veterinario extends Funcionario{
	private String CRMV;
	private String especializacao;
	
	public Veterinario(String matricula, double salario, String CPF, String nome, String setor, String CRMV, String especializacao) {
		super(matricula, salario, CPF, nome, setor);
		this.CRMV = CRMV;
		this.especializacao = especializacao;
	}
}
