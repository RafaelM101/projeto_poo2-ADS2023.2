package funcionarios;

public class Veterinario extends Funcionario{
	private String CRMV;
	private String especializacao;
	
	public Veterinario( Double salario, String CPF, String nome, String setor, String CRMV, String especializacao) {
		super(salario, CPF, nome, setor);
		this.CRMV = CRMV;
		this.especializacao = especializacao;
	}
	
	public String getCRMV() {
		return this.CRMV;
	}
}
