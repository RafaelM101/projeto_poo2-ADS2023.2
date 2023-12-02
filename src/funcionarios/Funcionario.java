package funcionarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import components.*;
import exceptions.CRMVInvalidoException;
import exceptions.CpfInvalidoException;
import exceptions.ListaVaziaException;
import servicos.AgendaDia;
import servicos.Servico;

import static main.Main.LimparTela;

public class Funcionario implements CRUD, Terminal{
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

		LocalDate date_agenda = LocalDate.now(), currentDate;
		AgendaDia agendaDia;
		formatter.format(date_agenda);
		if(!(LocalTime.now().isAfter(LocalTime.of(18,30)))){
			LocalTime hora_atual = LocalTime.now();
			agendaDia = new AgendaDia();
			AgendaDiariaFuncionario.put(date_agenda, agendaDia);
			Iterator<LocalTime> iterator = agendaDia.getHoraDisponivel().iterator();
			while (iterator.hasNext()) {
				LocalTime hora = iterator.next();
				if (hora.isBefore(hora_atual)) {
					iterator.remove();
				} else {
					break;
				}
		}
		for (int i = 1; i < 8; i++) {
			currentDate = date_agenda.plusDays(i);
			agendaDia = new AgendaDia();
			AgendaDiariaFuncionario.put(currentDate, agendaDia);
			}
			}
		}

	public ArrayList<Funcionario> getListaF(){
		return lista_funcionarios;
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

	@Override
	public String toString() {
		return 	"\t" + AZUL +"Nome: " + RESETAR + MAGENTA + nome + RESETAR + "\n" +
				"\t" +AZUL +"Matrícula: " + RESETAR + MAGENTA + matricula.numero_matricula + RESETAR +"\n" +
				"\t" +AZUL +"Salário: " + RESETAR + MAGENTA + salario + RESETAR +"\n" +
				"\t" +AZUL +"CPF: " + RESETAR + MAGENTA + CPF + RESETAR +"\n" +
				"\t" +AZUL +"Nome: " +RESETAR + MAGENTA + nome + RESETAR +"\n" +
				"\t" +AZUL +"Setor: " + RESETAR + MAGENTA + setor + RESETAR +"\n\n";
	}

	public static void data_seed_funcionario() {
		Funcionario funcionario_1 = new Funcionario(1500.55, "123.123.123-55", "Rafael Marques", Setores.SERVICOS_GERAIS);
		Funcionario.lista_funcionarios.add(funcionario_1);
		Funcionario funcionario_2 = new Funcionario(1500.55, "123.123.123-55", "Emmanoel Barros", Setores.SERVICOS_GERAIS);
		Funcionario.lista_funcionarios.add(funcionario_2);
		System.out.println(funcionario_1.toString());
		System.out.println(funcionario_2.toString());

		Veterinario veterinario_1 = new Veterinario(2500.55, "123.123.123-55", "Luísa Mell", Setores.CLINICA_VET,"1478-99", EspecializacoesVet.CLINICO);
		Funcionario.lista_funcionarios.add(veterinario_1);
		Veterinario veterinario_2 = new Veterinario(2500.55, "123.123.123-55", "Marina Ministra", Setores.CLINICA_VET,"5697-99", EspecializacoesVet.CIRURGIAO);
		Funcionario.lista_funcionarios.add(veterinario_2);

		System.out.println(veterinario_1.toString());
		System.out.println(veterinario_2.toString());
	}

	public static Funcionario consultarFuncionario(String matricula) {
		for (Funcionario funcionario : lista_funcionarios) {
			if (funcionario.getMatricula().equals(matricula)) {
				return funcionario;}
		}
		return null;
	}	
	//METODOS RELACIONADOS À AGENDA DO FUNCIONÁRIO
	public void agendarHorario(Servico novo_servico){
		Funcionario func = novo_servico.getNome_funcionario();
		if(func.verificarHorario(novo_servico.getData_servico(), novo_servico.getHora_servico())){
			AgendaDia agenda = AgendaDiariaFuncionario.get(novo_servico.getData_servico());
			agenda.agendarHorario(novo_servico.getHora_servico(), novo_servico);
		}
		else System.out.println(NEGRITO+ VERMELHO+"Horário escolhido não está disponível. Verifique outro funcionário."+RESETAR);
	}
	public void desmarcarHorario(Servico servico){
		AgendaDia agenda = AgendaDiariaFuncionario.get(servico.getData_servico());
		agenda.desmarcarHorario(servico.getHora_servico());
	}
	public boolean verificarHorario(LocalDate data, LocalTime hora){
		AgendaDia agenda = AgendaDiariaFuncionario.get(data);
        return agenda.verificarHorario(hora);
    }
	public void listarAgenda(){
		for (Map.Entry<LocalDate, AgendaDia> entry : AgendaDiariaFuncionario.entrySet()) {
			LocalDate data_agenda = entry.getKey();
			String dateFormatted = data_agenda.format(formatter);
			AgendaDia agendaDia = entry.getValue();
			System.out.println("DATA: " + dateFormatted);
			agendaDia.imprimirAgenda();
		}
	}

	public void imprimirAgendaDia(LocalDate dia){
		AgendaDia agenda_escolhida = AgendaDiariaFuncionario.get(dia);
		System.out.printf(CYAN + NEGRITO +"\tFUNCIONÁRIO: %s\n\tAGENDA DIA: %s ",this.nome,formatter.format(dia) + RESETAR);
		System.out.println(" ");
		agenda_escolhida.imprimirAgenda();
	}


}
