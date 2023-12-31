package controllers;

import static main.Main.LimparTela;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;

import components.CRUD;
import components.EspecializacoesVet;
import components.Setores;
import components.Terminal;
import components.Validar;
import exceptions.CRMVInvalidoException;
import exceptions.CpfInvalidoException;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;
import servicos.AgendaDia;
import servicos.Servico;

public class FuncionarioController implements CRUD, Terminal{
    public static ArrayList<Funcionario> lista_funcionarios = new ArrayList<>();
    private Funcionario funcionario = new Funcionario(null, NEGRITO, NEGRITO, null);

    public static void data_seed_funcionario() {
        Funcionario funcionario_1 = new Funcionario(1500.55, "123.123.123-55", "Rafael Marques", Setores.SERVICOS_GERAIS);
        FuncionarioController.lista_funcionarios.add(funcionario_1);
        Funcionario funcionario_2 = new Funcionario(1500.55, "123.123.123-55", "Emmanoel Barros", Setores.SERVICOS_GERAIS);
        FuncionarioController.lista_funcionarios.add(funcionario_2);
        System.out.println(funcionario_1.toString());
        System.out.println(funcionario_2.toString());

        Veterinario veterinario_1 = new Veterinario(2500.55, "123.123.123-55", "Luísa Mell", Setores.CLINICA_VET,"1478-99", EspecializacoesVet.CLINICO);
        FuncionarioController.lista_funcionarios.add(veterinario_1);
        Veterinario veterinario_2 = new Veterinario(2500.55, "123.123.123-55", "Marina Ministra", Setores.CLINICA_VET,"5697-99", EspecializacoesVet.CIRURGIAO);
        FuncionarioController.lista_funcionarios.add(veterinario_2);

        System.out.println(veterinario_1.toString());
        System.out.println(veterinario_2.toString());
    }
    public void cadastrar(){
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
                    funcionario.getListaF().add(func);
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
                        funcionario.getListaF().add(vet);
                }else System.out.println(NEGRITO+ VERMELHO+"OPÇÃO INVÁLIDA."+RESETAR);
            }
            catch(InputMismatchException e){
                System.out.println(NEGRITO+ VERMELHO+"INSIRA UM VALOR VÁLIDO"+RESETAR);
                return;
            }
    }
    public void listar() throws ListaVaziaException{
		if(funcionario.getListaF().size() < 1) {
			throw new ListaVaziaException(NEGRITO+ VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		for(Funcionario funcionario: funcionario.getListaF()) {
			System.out.printf(AMARELO+"\nNome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\n"+RESETAR,funcionario.getNome(), funcionario.getMatricula(), funcionario.getSalario(), funcionario.getCPF(), funcionario.getSetor());
		}
	}
    public void atualizar() throws ListaVaziaException{
		if(funcionario.getListaF().isEmpty()) {
			throw new ListaVaziaException(NEGRITO+ VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		System.out.print(NEGRITO+MAGENTA+"Digite a matrícula do funcionário que deseja atualizar os dados: "+RESETAR);
		String matFuncionarioAtt = teclado.nextLine();
		for(Funcionario funcionario: funcionario.getListaF()){
			if(funcionario.getMatricula().equals(matFuncionarioAtt)){
				System.out.printf(NEGRITO+MAGENTA+"Digite novo salário do funcionário %s: ", funcionario.getNome()+RESETAR);
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
    public void deletar() throws ListaVaziaException{
		if(funcionario.getMatricula().isEmpty()) {
			throw new ListaVaziaException(NEGRITO+VERMELHO+"Nenhum funcionario cadastrado!"+RESETAR);
		}
		System.out.print(NEGRITO+MAGENTA+"Digite a matrícula do funcionário que deseja demitir: "+RESETAR);
		String matFuncionarioDel = teclado.nextLine();
		for(Funcionario funcionario: funcionario.getListaF()) {
			if(funcionario.getMatricula().equals(matFuncionarioDel)) {
				funcionario.getListaF().remove(funcionario);
				System.out.println(NEGRITO+VERDE+"Removido com sucesso!"+RESETAR);
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

    //METODOS RELACIONADOS À AGENDA DO FUNCIONÁRIO
    public static void agendarHorario(Servico novo_servico){
        Funcionario func = novo_servico.getNome_funcionario();
        if(verificarHorario(func,novo_servico.getData_servico(), novo_servico.getHora_servico())){
            AgendaDia agenda = func.getAgendaDiariaFuncionario().get(novo_servico.getData_servico());
            agenda.agendarHorario(novo_servico.getHora_servico(), novo_servico);
        }
        else System.out.println(NEGRITO+ VERMELHO+"Horário escolhido não está disponível. Verifique outro funcionário."+RESETAR);
    }
    public static void desmarcarHorario(Servico servico){
        Funcionario func = servico.getNome_funcionario();
        AgendaDia agenda = func.getAgendaDiariaFuncionario().get(servico.getData_servico());
        agenda.desmarcarHorario(servico.getHora_servico());
    }
    public static boolean verificarHorario(Funcionario funcionario, LocalDate data, LocalTime hora){
        AgendaDia agenda = funcionario.getAgendaDiariaFuncionario().get(data);
        return agenda.verificarHorario(hora);
    }
    public static void listarAgenda(Funcionario funcionario){
        for (Map.Entry<LocalDate, AgendaDia> entry : funcionario.getAgendaDiariaFuncionario().entrySet()) {
            LocalDate data_agenda = entry.getKey();
            String dateFormatted = data_agenda.format(formatter);
            AgendaDia agendaDia = entry.getValue();
            System.out.println("DATA: " + dateFormatted);
            agendaDia.imprimirAgenda();
        }
    }

    public static void imprimirAgendaDia(Funcionario funcionario, LocalDate dia){
        AgendaDia agenda_escolhida = funcionario.getAgendaDiariaFuncionario().get(dia);
        System.out.printf(CYAN + NEGRITO +"\tFUNCIONÁRIO: %s\n\tAGENDA DIA: %s ",funcionario.getNome(),formatter.format(dia) + RESETAR);
        System.out.println(" ");
        agenda_escolhida.imprimirAgenda();
    }





}
