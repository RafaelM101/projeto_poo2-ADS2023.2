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

import static controllers.FuncionarioController.lista_funcionarios;
import static main.Main.LimparTela;

public class Funcionario implements CRUD, Terminal{
	protected Matricula matricula;
	protected Double salario;
	protected String CPF;
	protected String nome;
	protected Setores setor;
	private HashMap<LocalDate, AgendaDia> AgendaDiariaFuncionario;

		
	public Funcionario( Double salario, String CPF, String nome, Setores setor) {
		this.matricula = Matricula.gerarMatricula(TipoEntidade.FUNCIONARIO);
		this.salario = salario;
		this.CPF = CPF;
		this.nome = nome;
		this.setor = setor;
		this.AgendaDiariaFuncionario = new LinkedHashMap<>();

		LocalDate date_agenda = LocalDate.now(), currentDate;

		AgendaDia agendaHoje =  new AgendaDia();
		formatter.format(date_agenda);
		LocalTime hora_atual = LocalTime.now();
		AgendaDiariaFuncionario.put(date_agenda, agendaHoje);

		if(LocalTime.now().isAfter(LocalTime.of(18,30))){
			AgendaDiariaFuncionario.remove(LocalDate.now());
		}else{
			agendaHoje = AgendaDiariaFuncionario.get(LocalDate.now());
			Iterator<LocalTime> iterator = agendaHoje.getHoraDisponivel().iterator();
			while (iterator.hasNext()) {
				LocalTime hora = iterator.next();
				if (hora.isBefore(hora_atual)) {
					iterator.remove();
				}
				else {
					break;
				}
			}
		}
		for (int i = 1; i < 7; i++) {
			currentDate = date_agenda.plusDays(i);
			AgendaDia nova_agenda = new AgendaDia();
			AgendaDiariaFuncionario.put(currentDate, nova_agenda);
			}
	}


	public HashMap<LocalDate, AgendaDia> getAgendaDiariaFuncionario() {return AgendaDiariaFuncionario;}

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



}
