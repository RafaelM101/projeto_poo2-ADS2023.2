package funcionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import components.CRUD;
import servicos.AgendaDia;
import servicos.Servico;

public class Funcionario implements CRUD{
	protected String matricula;
	protected double salario;
	protected String CPF;
	protected String nome;
	protected String setor;

	private HashMap<LocalDate, AgendaDia> AgendaDiariaFuncionario;
	protected static ArrayList<Funcionario> lista_funcionarios = new ArrayList<>();
		
	public Funcionario(String matricula, double salario, String CPF, String nome, String setor) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.matricula = matricula;
		this.salario = salario;
		this.CPF = CPF;
		this.nome = nome;
		this.setor = setor;
		this.AgendaDiariaFuncionario = new LinkedHashMap<>();

		LocalDate date_agenda = LocalDate.now();
		formatter.format(date_agenda);

		for (int i = 0; i < 8; i++) {
			LocalDate currentDate = date_agenda.plusDays(i);
			AgendaDia agendaDia = new AgendaDia();
			String dateFormatted = currentDate.format(formatter);
			System.out.println(dateFormatted + " = " + agendaDia);
			AgendaDiariaFuncionario.put(currentDate, agendaDia);
		}
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
		lista_funcionarios.add(func);	
	}

	public static void listar() {
		for(Funcionario funcionario: lista_funcionarios) {
			System.out.println(funcionario.nome);
		}
		
	}

	public static void atualizar() {

	}

	public static Funcionario consultarFuncionario(String matricula) {
		for (Funcionario funcionario : lista_funcionarios) {
			if (funcionario.getMatricula().equals(matricula)) {
				return funcionario;}
		}
		return null;
	}
	public void deletar() {
		// TODO Stub de método gerado automaticamente
		
	}
	//METODOS RELACIONADOS À AGENDA DO FUNCIONÁRIO
	public void agendarHorario(Servico novo_servico){
		AgendaDia agenda = AgendaDiariaFuncionario.get(novo_servico.getData_servico());
		agenda.agendarHorario(novo_servico.getHora_servico(), novo_servico);
	}
	public void desmarcarHorario(Servico servico){
		AgendaDia agenda = AgendaDiariaFuncionario.get(servico.getData_servico());
		agenda.desmarcarHorario(servico.getHora_servico());
	}

	public void remarcarHorario(Servico servico){
		AgendaDia agenda = AgendaDiariaFuncionario.get(servico.getData_servico());
		agenda.mudarHorario(servico.getHora_servico(), servico);
	}
	public void listarHorarios(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Map.Entry<LocalDate, AgendaDia> entry : AgendaDiariaFuncionario.entrySet()) {
			LocalDate data_agenda = entry.getKey();
			String dateFormatted = data_agenda.format(formatter);
			System.out.println(dateFormatted + " = " + entry.getValue() + "\n");
		}
	}
}
