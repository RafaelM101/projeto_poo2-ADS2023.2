package funcionarios.funcionarios;

public class Funcionario {
	protected String matricula;
	protected double salario;
	protected String CPF;
	protected String nome;
	protected String setor;
		
	public Funcionario(String matricula, double salario, String CPF, String nome, String setor) {
		this.matricula = matricula;
		this.salario = salario;
		this.CPF = CPF;
		this.nome = nome;
		this.setor = setor;
	}
}
