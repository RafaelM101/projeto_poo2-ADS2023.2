package main;


import Pets.Cachorro;
import Pets.Gato;
import Pets.Pet;
import Tutores.Endereco;
import Tutores.Tutor;
import components.*;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;
import servicos.Servico;

import java.util.Scanner;

public class Main implements Terminal {
    public static Scanner teclado = new Scanner(System.in);

    public static void LimparTela(){
        String linha = "-".repeat(100);
        System.out.println(CYAN + linha + RESETAR);
        System.out.println(CYAN + linha + RESETAR);
    }
    public static void menu_Principal() throws ListaVaziaException {
        while (true) {
            LimparTela();
            System.out.print("\t\t\t"+NEGRITO + PRETO + FUNDO_VERDE+ "SISTEMA DE GERENCIAMENTO AMIGOPET" + RESETAR + "\n");
            System.out.println(NEGRITO + AMARELO +"Escolha o módulo que deseja acessar:"+RESETAR);
            System.out.print(NEGRITO + CYAN + "1 - MODULO DE GERENCIAMENTO DE FUNCIONÁRIOS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "2 - MODULO DE GERENCIAMENTO DE TUTORES\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "3 - MODULO DE AGENDAMENTO DE SERVIÇOS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "4 - MODULO DE GERENCIAMENTO DE PETS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "5 - FINALIZAR PROGRAMA\n" + RESETAR);
            System.out.print(NEGRITO + AMARELO +"DIGITE A OPÇÃO ESCOLHIDA:" + RESETAR);
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user) {
                case 1:
                    LimparTela();
                    menu_Funcionario();
                    break;
                case 2:
                    LimparTela();
                    menu_Tutor();
                    break;
                case 3:
                    LimparTela();
                    Menu_AgendarServico();
                    break;
                case 4:
                    LimparTela();
                    menu_Pet();
                    break;
                default:
                    return;
            }
        }
    }
    public static void menu_Funcionario(){
        while (true) {
            System.out.println(AZUL + NEGRITO +"\t\t\tMÓDULO DE GERENCIAMENTO DE FUNCIONÁRIOS" + RESETAR);
            System.out.println(AMARELO + NEGRITO + "Escolha a ação que deseja realizar: " + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "1 - Cadastrar um novo funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "2 - Listar funcionários." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "3 - Atualizar salário de um funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "4 - Demitir um funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "5 - Voltar ao menu principal." + RESETAR);
            System.out.print(AMARELO + NEGRITO +"DIGITE A OPÇÃO ESCOLHIDA: " + RESETAR);
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            System.out.println("");
            switch (escolha_user) {
                case 6:
                    return;
                case 1:
                    LimparTela();
                    Funcionario.cadastrar();
                    break;
                case 2:
                    LimparTela();
                    System.out.print("""
                            Digite 1 para exibir todos os funcionários.
                            Digite 2 para exibir por matrícula.
                            DIGITE A OPÇÃO ESCOLHIDA: """);
                    int escolha_listar = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("\n");
                    if(escolha_listar == 1) {
                        try{
                            Funcionario.listar();
                        }
                        catch(ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if(escolha_listar == 2){
                        try{
                            System.out.print("Digite a matrícula do funcionário: ");
                            String matricula_consulta = teclado.nextLine();
                            Funcionario funcionario_consulta = Funcionario.consultarFuncionario(matricula_consulta);
                            if(funcionario_consulta instanceof Veterinario) {
                                Veterinario vet_consulta = (Veterinario) funcionario_consulta;
                                System.out.printf("Nome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\nCRMV: %s\nEspecialização: %s\n",vet_consulta.getNome(), vet_consulta.getMatricula(), vet_consulta.getSalario(), vet_consulta.getCPF(), vet_consulta.getSetor(), vet_consulta.getCRMV(), vet_consulta.getEscpecializacao());
                            }
                            else{
                                System.out.printf("Nome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\n",funcionario_consulta.getNome(), funcionario_consulta.getMatricula(), funcionario_consulta.getSalario(), funcionario_consulta.getCPF(), funcionario_consulta.getSetor());
                            }
                        }
                        catch(NullPointerException e ){
                            System.out.println("Nenhum funcionario encontrado!");
                        }
                    }
                    break;
                case 3:
                    try {
                        LimparTela();
                        Funcionario.atualizar();
                    } catch (ListaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try{
                        LimparTela();
                        Funcionario.deletar();
                    }
                    catch(ListaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void Menu_AgendarServico() throws ListaVaziaException {
        while(true){
            System.out.println(NEGRITO + VERDE + "\t\t\tMÓDULO DE AGENDAMENTO DE SERVIÇOS" + RESETAR);
            System.out.println(NEGRITO + CYAN + "Digite a opção desejada: " + RESETAR);
            System.out.println(NEGRITO + AZUL + "1 - Cadastrar um novo agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL + "2 - Remarcar/Alterar um agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL + "3 - Desmarcar um agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL +"4 - Listar todos os agendamentos."+ RESETAR);
            System.out.println(NEGRITO + AZUL+ "5 - Exibir Agenda de um Funcionário específico." + RESETAR);
            System.out.println(NEGRITO + AZUL + "6 - Voltar ao menu inicial." + RESETAR);
            System.out.print(NEGRITO + CYAN + "DIGITE A OPÇÃO ESCOLHIDA: " + RESETAR);
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user){
                case 1:
                    Servico.cadastrar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 2:
                    Servico.atualizar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 3:
                    Servico.deletar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 4:
                    Servico.listar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();

                    break;
                case 5:
                    System.out.println("Digite a matrícula do funcionário desejado: ");
                    String matricula_func = teclado.nextLine().strip();
                    Funcionario funcionario_agenda = Funcionario.consultarFuncionario(matricula_func);
                    funcionario_agenda.listarAgenda();
                    System.out.println("Pressione enter para voltar ao MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();
                    break;
                default:
                    System.out.println("Pressione enter para voltar ao menu inicial...");
                    teclado.nextLine();
                    return;
            }
    }}
    public static void menu_Tutor() {
        while (true) {
            System.out.println("\t\t\tMÓDULO DE GERENCIAMENTO DE TUTOR");
            System.out.println("Digite a opção desejada: ");
            System.out.print("""
                1 - Cadastrar um novo tutor.
                2 - Lista todos os tutores.
                3 - Atualizar o cadastro de um tutor existente.
                4 - Apagar o cadastro de um tutor.
                5 - Consultar tutor.
                6 - Voltar ao menu inicial.
                DIGITE A OPÇÃO ESCOLHIDA:\s""");
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user){
                case 1: {
                    Tutor.cadastrar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR...");
                    teclado.nextLine();
                    break;
                } case 2: {
                    try {
                        Tutor.listar();
                    }
                    catch (ListaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Pressione enter para voltar ao menu MODULO DE GERENCIAMENTO DE TUTOR...");
                    teclado.nextLine();
                    break;
                } case 3: {
                    Tutor.atualizar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR...");
                    teclado.nextLine();
                    break;
                } case 4: {
                    Tutor.deletar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR...");
                    teclado.nextLine();
                    break; 
                } case 5: {
                    System.out.print("Insira o CPF do Tutor que deseja consultar: ");
                    String cpfConsulta = teclado.nextLine();
                    Tutor tutorConsultado = Tutor.consultarTutor(cpfConsulta);
                    if (tutorConsultado instanceof Tutor) {
                        System.out.println("Nome: " + tutorConsultado.getNomeTutor());
                        Endereco enderecoTutor = tutorConsultado.getEnderecoTutor();
                        System.out.println("Endereço: \n" 
                            +"- Rua: " + enderecoTutor.getRua() +"\n"
                            +"- Bairro: " + enderecoTutor.getBairro() +"\n"
                            +"- Número: " + enderecoTutor.getNumero());
                        System.out.println("Telefone: " + tutorConsultado.getTelefoneTutor());
                        System.out.println("Email: " + tutorConsultado.getEmailTutor());
                    } else {
                        System.out.println("CPF inválido OU Não consta no sistema.");
                    }
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR...");
                    teclado.nextLine();
                    break;
                } default: {
                    System.out.println("Pressione enter para voltar ao menu inicial...");
                    teclado.nextLine();
                    return;
                }
            }   
        }
    }
    public static void menu_Pet() {
        while (true) {
            System.out.println("\t\t\tMÓDULO DE GERENCIAMENTO DE PET");
            System.out.println("Digite a opção desejada: ");
            System.out.print("""
                1 - Cadastrar um Pet.
                2 - Atualizar cadastro de Pet.
                3 - Apagar o cadastro de um Pet.
                4 - Listar todos os Pets cadastrados.
                5 - Adicionar Pet a um Tutor existente.
                6 - Remover Pet de um Tutor existente.
                7 - Voltar ao menu inicial.
                DIGITE A OPÇÃO ESCOLHIDA:\s""");
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user){
                case 1: {
                    Pet.cadastrar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET...");
                    teclado.nextLine();
                    break;
                } case 2: {
                    Pet.atualizar();
                    System.out.println("Pressione enter para voltar ao menu MODULO DE GERENCIAMENTO DE PET...");
                    teclado.nextLine();
                    break;
                } case 3: {
                    Pet.deletar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET...");
                    teclado.nextLine();
                    break;
                } case 4: {
                    try {
                        Pet.listar();
                    }
                    catch (ListaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET...");
                    teclado.nextLine();
                    break; 
                } case 5: {
                    Pet.atribuirPet_Tutor();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET...");
                    teclado.nextLine();
                    break;
                } case 6: {
                    Pet.removerPet_Tutor();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET...");
                        teclado.nextLine();
                    break;
                } default: {
                    System.out.println("Pressione enter para voltar ao menu inicial...");
                    teclado.nextLine();
                    return;
                }
            }   
        }
    }

    public static void data_seed() throws ListaVaziaException {
        Tutor.data_seed_tutor();
        Pet.data_seed_pet();
        Funcionario.data_seed_funcionario();

    }
    public static void main(String[] args) throws ListaVaziaException {
        data_seed();
        menu_Principal();
    }

}


//Modulo MENU PRINCIPAL(Rafael): Escolher entre os Módulo TUTOR, PET, FUNCIONARIO e AGENDAR SERVIÇO.
//Modulo TUTOR(Kerlen): Cadastrar Tutor, Listar Todos os Tutores,
//atualizar tutor existente, apagar tutor, Consultar Tutor por CPF, EXIBIR ENDEREÇO e TELEFONE DE TUTOR.

//Modulo Funcionario: (Emmanuel )Cadastrar - Normal ou Veterinario, Listar Todos, Listar por matrícula, Atualizar Salário, Apagar

//Modulo Agendar Serviço:(Rafael) CADASTRAR SERVIÇO, ATUALIZAR SERVIÇO, DESMARCAR SERVIÇO, Listar serviço por data, Listar todos, Exibir agenda de funcionário pela matrícula

//Modulo Pet (Kerlen): CADASTRAR PET, ATUALIZAR PET, EXCLUIR PET, LISTAR TODOS OS PETS, Listar PET por funcionario,  Adicionar Pet em Tutor existente, Remover Pet de Tutor Existente,