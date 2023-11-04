package funcionarios;

import java.util.ArrayList;
import java.util.Scanner;

import components.CRUD;

public class Funcionario implements CRUD{
	protected String matricula;
	protected double salario;
	protected String CPF;
	protected String nome;
	protected String setor;
	protected static ArrayList<Funcionario> lista_funcionarios = new ArrayList<>();
		
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
	
	public static void cadastrar() {
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digite a matrícula do funcionário: ");
		String matricula = teclado.nextLine();
		System.out.print("Digite o salário do funcionário: ");
		double salario = teclado.nextDouble();
		teclado.nextLine();
		System.out.print("Digite o CPF do funcionário: ");
		String CPF = teclado.nextLine();
		System.out.print("Digite nome do funcionário: ");
		String nome = teclado.nextLine();
		System.out.print("Digite o setor do funcionário: ");
		String setor = teclado.nextLine();
		Funcionario func = new Funcionario(matricula, salario, CPF, nome, setor);
		teclado.close();
		lista_funcionarios.add(func);	
	}

	public static void listar() {
		for(Funcionario funcionario: lista_funcionarios) {
			System.out.println(funcionario.nome);
		}
		
	}

	public static void atualizar() {
		
		
	}

	public void deletar() {
		// TODO Stub de método gerado automaticamente
		
	}
}
