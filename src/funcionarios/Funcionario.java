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
	public static void cadastrar() {
		try{
			System.out.println(MAGENTA + NEGRITO + "\n| Cadastro de Funcionários |\n\n"+ RESETAR);
			System.out.print(AZUL + "Digite nome do funcionário: " + RESETAR);
			String nome = teclado.nextLine();
			System.out.print(AZUL + "Digite o sobrenome do funcionário: " +RESETAR);
			String sobrenome = teclado.next();
			nome += " "+sobrenome;
			teclado.nextLine();
			try {
				Validar.validarLetras(nome);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				cadastrar();
				return;
			}
			System.out.print(AZUL + "Digite o salário do funcionário: " + RESETAR);
			Double salario = teclado.nextDouble();
			teclado.nextLine();
			String CPF;
			while(true){
				try{
					System.out.print(AZUL + "Digite o CPF do funcionário: " + RESETAR) ;
					String cpfValidando = teclado.nextLine();
					if(Validar.validarCPF(cpfValidando)) {
						CPF = cpfValidando;
						break; 
					}
				}
				catch(CpfInvalidoException e){
					System.out.println(e.getMessage());
				}
        }
			System.out.print(AZUL + "\t\t\tEscolha o setor do funcionário:\nDigite 1 PARA SERVICOS_GERAIS ou 2 PARA CLINICA_VET : "+ RESETAR);
			Integer escolha = teclado.nextInt();
			teclado.nextLine();
			Setores setor = null;
			if(escolha==1){
				setor = Setores.SERVICOS_GERAIS;
				Funcionario func = new Funcionario(salario, CPF, nome, setor);
				System.out.println(MAGENTA + NEGRITO +"\nFuncionário cadastrado!\nDados:" + RESETAR);
				System.out.printf(func.toString());
				LimparTela();
				lista_funcionarios.add(func);
			} else if (escolha==2) {
				setor = Setores.CLINICA_VET;
				String crmv;
				while(true){
					try{
						System.out.print(AZUL + "Insira o CRMV do Veterinário: " + RESETAR) ;
						String CRMVValidando = teclado.nextLine();
						if(Validar.validarCRMV(CRMVValidando)) {
							crmv = CRMVValidando;
							break; 
						}
					}
					catch(CRMVInvalidoException e){
						System.out.println(e.getMessage());
					}
				}
				System.out.println(AZUL + "Escolha a Especialização do Veterinário:\n 1 - CLINICO,\n" +
						" 2 - CIRURGIAO,\n" +
						" 3 - ORTOPEDISTA,\n" +
						" 4 - ONCOLOGISTA\n" + RESETAR);
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
						System.out.println(NEGRITO+ VERMELHO+"Escolha inválida."+RESETAR);
						break;
				} Veterinario vet = new Veterinario(salario, CPF, nome, setor, crmv, espec);
					lista_funcionarios.add(vet);
			}else System.out.println(NEGRITO+ VERMELHO+"OPÇÃO INVÁLIDA."+RESETAR);
		}
		catch(InputMismatchException e){
			System.out.println(NEGRITO+ VERMELHO+"INSIRA UM VALOR VÁLIDO"+RESETAR);
			return;
		}
	}

	public static void listar() throws ListaVaziaException{
		if(lista_funcionarios.size() < 1) {
			throw new ListaVaziaException(NEGRITO+ VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		for(Funcionario funcionario: lista_funcionarios) {
			System.out.printf(AMARELO+"\nNome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\n"+RESETAR,funcionario.nome, funcionario.getMatricula(), funcionario.salario, funcionario.CPF, funcionario.setor);
		}
		System.out.println(NEGRITO+VERMELHO+"Nenhum funcionario encontrado!"+RESETAR);
		return;
	}

	public static void atualizar() throws ListaVaziaException{
		if(lista_funcionarios.isEmpty()) {
			throw new ListaVaziaException(NEGRITO+ VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		System.out.print(NEGRITO+MAGENTA+"Digite a matrícula do funcionário que deseja atualizar os dados: "+RESETAR);
		String matFuncionarioAtt = teclado.nextLine();
		for(Funcionario funcionario: lista_funcionarios){
			if(funcionario.matricula.numero_matricula.equals(matFuncionarioAtt)){
				System.out.printf(NEGRITO+MAGENTA+"Digite novo salário do funcionário %s: ", funcionario.nome+RESETAR);
				double novoSalario = teclado.nextDouble();
				teclado.nextLine();
				funcionario.setSalario(novoSalario);
				System.out.println(NEGRITO + VERDE+"Salário atualizado com sucesso!"+RESETAR);
				return;				
			}
		}
		System.out.println(NEGRITO+VERMELHO+"Nenhum funcionario encontrado!"+RESETAR);
		return;
		
	}

	public static Funcionario consultarFuncionario(String matricula) {
		for (Funcionario funcionario : lista_funcionarios) {
			if (funcionario.getMatricula().equals(matricula)) {
				return funcionario;}
		}
		return null;
	}
	public static void deletar() throws ListaVaziaException{
		if(lista_funcionarios.isEmpty()) {
			throw new ListaVaziaException(NEGRITO+VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		System.out.print(NEGRITO+MAGENTA+"Digite a matrícula do funcionário que deseja demitir: "+RESETAR);
		String matFuncionarioDel = teclado.nextLine();
		for(Funcionario funcionario: lista_funcionarios) {
			if(funcionario.matricula.numero_matricula.equals(matFuncionarioDel)) {
				lista_funcionarios.remove(funcionario);
				System.out.println(NEGRITO+VERDE+"Removido com sucesso!"+RESETAR);
				return;
			}
		}
		System.out.println(NEGRITO+VERMELHO+"Nenhum funcionario encontrado!"+RESETAR);
		return;
		
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
