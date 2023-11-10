package funcionarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import components.*;
import servicos.AgendaDia;
import servicos.Servico;

public class Funcionario implements CRUD{
	protected Matricula matricula;
	protected Double salario;
	protected String CPF;
	protected String nome;
	protected Setores setor;

	private HashMap<LocalDate, AgendaDia> AgendaDiariaFuncionario;
	protected static ArrayList<Funcionario> lista_funcionarios = new ArrayList<>();
		
	public Funcionario( Double salario, String CPF, String nome, Setores setor) {
		this.matricula = Matricula.gerarMatricula(TipoEntidade.FUNCIONARIO);
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
			AgendaDiariaFuncionario.put(currentDate, agendaDia);
		}
	}
	
	public String getSetor() {
		return setor.toString();
	}

	public String getCPF() {
		return CPF;
	}

	public String getMatricula() {
		return matricula.numero_matricula;
	}


	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}


	public String getNome() {
		return nome;
	}

	public void setSetor(Setores setor) {
		this.setor = setor;
	}
	
	public static void cadastrar() {
		System.out.print("Digite nome do funcionário: ");
		String nome = teclado.nextLine();
		System.out.print("Digite o sobrenome do funcionário: ");
		String sobrenome = teclado.next();
		nome += " "+sobrenome;
		teclado.nextLine();
		System.out.print("Digite o salário do funcionário: ");
		Double salario = teclado.nextDouble();
		teclado.nextLine();
		System.out.print("Digite o CPF do funcionário: ");
		String CPF = teclado.nextLine();
		System.out.print("Escolha o setor do funcionário:\nDigite 1 PARA SERVICOS_GERAIS ou 2 PARA CLINICA_VET :");
		Integer escolha = teclado.nextInt();
		teclado.nextLine();
		Setores setor = null;
		if(escolha==1){
			setor = Setores.SERVICOS_GERAIS;
			Funcionario func = new Funcionario(salario, CPF, nome, setor);
			System.out.printf("Matrícula do Funcionário %s: %s%n", nome, func.getMatricula());;
			lista_funcionarios.add(func);
		} else if (escolha==2) {
			setor =Setores.CLINICA_VET;
			System.out.println("Insira o CRMV do Veterinário: ");
			String crmv = teclado.nextLine();
			System.out.println("Escolha a Especialização do Veterinário:\n 1 - CLINICO,\n" +
					" 2 - CIRURGIAO,\n" +
					" 3 - ORTOPEDISTA,\n" +
					" 4 - ONCOLOGISTA\n:");
			EspecializacoesVet espec = null;
			Integer escolha_espec = teclado.nextInt();
			switch (escolha_espec){
				case 1:
					espec = EspecializacoesVet.CLINICO;
					break;
				case 2:
					espec = EspecializacoesVet.CIRURGIAO;
					break;
				case 3:
					espec = EspecializacoesVet.ORTOPEDISTA;
					break;
				case 4:
					espec = EspecializacoesVet.ONCOLOGISTA;
					break;
				default:
					System.out.println("Escolha inválida.");
					break;
			} Veterinario vet = new Veterinario(salario, CPF, nome, setor, crmv, espec);
				lista_funcionarios.add(vet);
		}else System.out.println("OPÇÃO INVÁLIDA.");

	}

	public static void listar() {
		for(Funcionario funcionario: lista_funcionarios) {
			System.out.printf("\nNome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\n",funcionario.nome, funcionario.getMatricula(), funcionario.salario, funcionario.CPF, funcionario.setor);
		}
		
	}

	public static void atualizar() {
		System.out.print("Digite a matrícula do funcionário que deseja atualizar os dados: ");
		String matFuncionarioAtt = teclado.nextLine();
		for(Funcionario funcionario: lista_funcionarios){
			if(funcionario.matricula.numero_matricula.equals(matFuncionarioAtt)){
				System.out.printf("Digite novo salário do funcionário %s: ", funcionario.nome);
				double novoSalario = teclado.nextDouble();
				teclado.nextLine();
				//Implementar um bloco try-catch posteriormente
				funcionario.setSalario(novoSalario);
				System.out.println("Salário atualizado com sucesso!");
				break;				
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
