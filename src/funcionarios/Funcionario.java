package funcionarios;

import java.time.LocalDate;
import java.time.LocalTime;
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
			System.out.printf("Nome: %s\nMatricula: %s\nSalário: %.2f\nCPF: %s\nSetor: %s\n",funcionario.nome, funcionario.matricula, funcionario.salario, funcionario.CPF, funcionario.setor);
		}
		
	}

	public static void atualizar() {
		System.out.print("Digite a matrícula do funcionário que deseja atualizar os dados: ");
		String matFuncionarioAtt = teclado.nextLine();
		for(Funcionario funcionario: lista_funcionarios){
			if(funcionario.matricula.equals(matFuncionarioAtt)){
				while (true) {
					System.out.print("Digite o campo que deseja mudar: ");
					String option = teclado.nextLine();
					switch (option.toLowerCase()) {
						case "sair":
							return;
						case "matricula":
							System.out.printf("Digite a nova matrícula do funcionário %s: ", funcionario.nome);
							String novaMatricula = teclado.nextLine();
							//Implementar um bloco try-catch posteriormente
							funcionario.setMatricula(novaMatricula);
							System.out.println("Matrícula atualizada com sucesso!");
							break;
						case "cpf":
							System.out.printf("Digite novo CPF do funcionário %s: ", funcionario.nome);
							String novoCPF = teclado.nextLine();
							//Implementar um bloco try-catch posteriormente
							funcionario.setCPF(novoCPF);
							System.out.println("CPF atualizado com sucesso!");
							break;
						case "salario":
							System.out.printf("Digite novo salário do funcionário %s: ", funcionario.nome);
							double novoSalario = teclado.nextDouble();
							teclado.nextLine();
							//Implementar um bloco try-catch posteriormente
							funcionario.setSalario(novoSalario);
							System.out.println("Salário atualizado com sucesso!");
							break;
						case "nome":
							System.out.printf("Digite a nova matrícula do funcionário %s: ", funcionario.nome);
							String novoNome = teclado.nextLine();
							//Implementar um bloco try-catch posteriormente
							funcionario.setNome(novoNome);
							System.out.println("Nome atualizado com sucesso!");
							System.out.printf("Olá %s :)\n", funcionario.nome);
							break;
						case "setor":
							System.out.printf("Digite a nova matrícula do funcionário %s: ", funcionario.nome);
							String novoSetor = teclado.nextLine();
							//Implementar um bloco try-catch posteriormente
							funcionario.setSetor(novoSetor);
							System.out.println("Matrícula atualizada com sucesso!");
							break;
						default:
							System.out.println("Opção não existe!");
							break;
					}
					System.out.println("Caso não precise fazer mais nenhuma alteração digite sair...");

				}
			}
		}
		
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
		if(verificarHorario(novo_servico.getData_servico(), novo_servico.getHora_servico()))
			{
		AgendaDia agenda = AgendaDiariaFuncionario.get(novo_servico.getData_servico());
		agenda.agendarHorario(novo_servico.getHora_servico(), novo_servico);
		}
		else System.out.println("Horário escolhido não está disponível. Verifique outro funcionário.");
	}
	public void desmarcarHorario(Servico servico){
		AgendaDia agenda = AgendaDiariaFuncionario.get(servico.getData_servico());
		agenda.desmarcarHorario(servico.getHora_servico());
	}
	public boolean verificarHorario(LocalDate data, LocalTime hora){
		AgendaDia agenda = AgendaDiariaFuncionario.get(data);
		return agenda.verificarHorario(hora);
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
