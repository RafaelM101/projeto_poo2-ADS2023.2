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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
}
