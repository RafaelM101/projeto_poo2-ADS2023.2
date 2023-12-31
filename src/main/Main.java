package main;

import components.*;
import controllers.FuncionarioController;
import controllers.PetController;
import controllers.ServicoController;
import controllers.TutorController;
import exceptions.DataInvalidaException;
import exceptions.EscolhaInvalidaException;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements Terminal {
    public static Scanner teclado = new Scanner(System.in);

    public static void LimparTela(){
        String linha = "-".repeat(100);
        System.out.println(CYAN + linha + RESETAR);
        System.out.println(CYAN + linha + RESETAR);
    }
    public static void menu_Principal(){
        while (true) {
            LimparTela();
            System.out.print("\t\t\t" + NEGRITO + PRETO + FUNDO_VERDE + "SISTEMA DE GERENCIAMENTO AMIGOPET" + RESETAR + "\n");
            System.out.println("\n\t\t\t"+NEGRITO + FUNDO_AMARELO + PRETO +"Alunos: Rafael Marques, Emmanoel Barros e Kerlen Melo."+ RESETAR +"\n" +"\t\t\t" +FUNDO_AMARELO + NEGRITO + PRETO + "2º ADS - 2023.2" + RESETAR);
            System.out.println(NEGRITO + AMARELO + "Escolha o módulo que deseja acessar:" + RESETAR);
            System.out.print(NEGRITO + CYAN + "1 - MODULO DE GERENCIAMENTO DE FUNCIONÁRIOS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "2 - MODULO DE GERENCIAMENTO DE TUTORES\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "3 - MODULO DE AGENDAMENTO DE SERVIÇOS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "4 - MODULO DE GERENCIAMENTO DE PETS\n" + RESETAR);
            System.out.print(NEGRITO + CYAN + "5 - FINALIZAR PROGRAMA\n" + RESETAR);
            System.out.print(NEGRITO + AMARELO + "DIGITE A OPÇÃO ESCOLHIDA: " + RESETAR);
            int escolha_user = 0;
            try {
                escolha_user = teclado.nextInt();
                if (escolha_user < 1 || escolha_user > 5) {
                    throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tO número escolhido não corresponde a nenhuma opção disponível, verifique e tente novamente.\n" + RESETAR);
                }
                teclado.nextLine();
            } catch (EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
                teclado.nextLine();
                continue;

            }catch (InputMismatchException f){
                System.out.println(VERMELHO + "\n\t\t\tInsira apenas números!\n" + RESETAR);
                teclado.nextLine();
                continue;
            }
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

        FuncionarioController f = new FuncionarioController();

        while (true) {
            System.out.println(AZUL + NEGRITO + "\t\t\tMÓDULO DE GERENCIAMENTO DE FUNCIONÁRIOS" + RESETAR);
            System.out.println(AMARELO + NEGRITO + "Escolha a ação que deseja realizar: " + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "1 - Cadastrar um novo funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "2 - Listar funcionários." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "3 - Atualizar salário de um funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "4 - Demitir um funcionário." + RESETAR);
            System.out.println(MAGENTA + NEGRITO + "5 - Voltar ao menu principal." + RESETAR);
            System.out.print(AMARELO + NEGRITO + "DIGITE A OPÇÃO ESCOLHIDA: " + RESETAR);
            int escolha_user;
            try {
                escolha_user = teclado.nextInt();
                teclado.nextLine();
                if(escolha_user < 1 || escolha_user > 5){
                    throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tO número escolhido não corresponde à nenhuma opção disponível. Pressione ENTER e tente novamente.\n" + RESETAR);
                }
                System.out.println("");
            } catch (EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
                teclado.nextLine();
                continue;

            } catch (InputMismatchException ee) {
                System.out.println(VERMELHO + "\n\t\t\tInsira apenas números!\n" + RESETAR);
                teclado.nextLine();
                continue;
            }
            switch (escolha_user) {
                case 6:
                    return;
                case 1:
                    LimparTela();
                    //E USA ASSIM:
                    f.cadastrar();
                    break;
                case 2:
                    LimparTela();
                    System.out.print(NEGRITO + MAGENTA + """
                            Digite 1 para exibir todos os funcionários.
                            Digite 2 para exibir por matrícula.
                            DIGITE A OPÇÃO ESCOLHIDA: """ + RESETAR);
                    int escolha_listar = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("\n");
                    if (escolha_listar == 1) {
                        try {
                            f.listar();
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (escolha_listar == 2) {
                        try {
                            System.out.print(MAGENTA + "Digite a matrícula do funcionário: " + RESETAR);
                            String matricula_consulta = teclado.nextLine();
                            Funcionario funcionario_consulta = FuncionarioController.consultarFuncionario(matricula_consulta);
                            if (funcionario_consulta instanceof Veterinario) {
                                Veterinario vet_consulta = (Veterinario) funcionario_consulta;
                                System.out.printf(AMARELO + "Nome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\nCRMV: %s\nEspecialização: %s\n", vet_consulta.getNome(), vet_consulta.getMatricula(), vet_consulta.getSalario(), vet_consulta.getCPF(), vet_consulta.getSetor(), vet_consulta.getCRMV(), vet_consulta.getEscpecializacao() + RESETAR);
                            } else {
                                System.out.printf(AMARELO + "Nome: %s\nMatricula: %s%nSalário: %.2f\nCPF: %s\nSetor: %s\n", funcionario_consulta.getNome(), funcionario_consulta.getMatricula(), funcionario_consulta.getSalario(), funcionario_consulta.getCPF(), funcionario_consulta.getSetor() + RESETAR);
                            }
                        } catch (NullPointerException e) {
                            System.out.println(NEGRITO + VERMELHO + "Nenhum funcionario encontrado!" + RESETAR);
                        }
                    }
                    break;
                case 3:
                    try {
                        LimparTela();
                        //MAIS UM EXEMPLO:
                        f.atualizar();
                    } catch (ListaVaziaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        LimparTela();
                        f.deletar();
                    } catch (ListaVaziaException e) {
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
    public static void Menu_AgendarServico(){
        while(true) {
            System.out.println(NEGRITO + VERDE + "\t\t\tMÓDULO DE AGENDAMENTO DE SERVIÇOS" + RESETAR);
            System.out.println(NEGRITO + CYAN + "Digite a opção desejada: " + RESETAR);
            System.out.println(NEGRITO + AZUL + "1 - Cadastrar um novo agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL + "2 - Remarcar/Alterar um agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL + "3 - Desmarcar um agendamento." + RESETAR);
            System.out.println(NEGRITO + AZUL + "4 - Listar todos os agendamentos." + RESETAR);
            System.out.println(NEGRITO + AZUL + "5 - Exibir Agenda de um Funcionário específico." + RESETAR);
            System.out.println(NEGRITO + AZUL + "6 - Voltar ao menu inicial." + RESETAR);
            int escolha_user = 0;
            try {
                System.out.print(NEGRITO + CYAN + "DIGITE A OPÇÃO ESCOLHIDA: " + RESETAR);
                escolha_user = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(VERMELHO + "\n\t\t\tInsira apenas números. Pressione enter para voltar.\n" + RESETAR);
                teclado.nextLine();
                continue;
            }
            switch (escolha_user) {
                case 1:
                    LimparTela();
                    ServicoController.cadastrar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 2:
                    LimparTela();
                    ServicoController.atualizar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 3:
                    LimparTela();
                    ServicoController.deletar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();
                    break;
                case 4:
                    LimparTela();
                    ServicoController.listar();
                    System.out.println(FUNDO_CYAN + PRETO + "Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS..." + RESETAR);
                    teclado.nextLine();

                    break;
                case 5:
                    LimparTela();
                    ServicoController.ListarAgendaFuncionario();
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
            try {
                LimparTela();
                System.out.println(NEGRITO+CYAN+ "\t\t\tMÓDULO DE GERENCIAMENTO DE TUTOR" +RESETAR);
                System.out.println(NEGRITO+AMARELO+ "Digite a opção desejada: " +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "1 - Cadastrar um novo tutor." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "2 - Lista todos os tutores." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "3 - Atualizar o cadastro de um tutor existente." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "4 - Apagar o cadastro de um tutor." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "5 - Consultar tutor." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "6 - Voltar ao menu inicial." +RESETAR);
                System.out.print(NEGRITO+AMARELO+ "DIGITE A OPÇÃO ESCOLHIDA: " +RESETAR);
                int escolha_user = teclado.nextInt();
                teclado.nextLine();
                switch (escolha_user){
                    case 1: {
                        LimparTela();
                        TutorController.cadastrar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 2: {
                        try {
                            LimparTela();
                            TutorController.listar();
                        }
                        catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu MODULO DE GERENCIAMENTO DE TUTOR..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 3: {
                        LimparTela();
                        TutorController.atualizar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 4: {
                        LimparTela();
                        TutorController.deletar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR..." +RESETAR);
                        teclado.nextLine();
                        break; 
                    } case 5: {
                        LimparTela();
                        TutorController.consulta();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE TUTOR..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } default: {
                        LimparTela();
                        return;
                    }
                }  
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                teclado.nextLine();
            }
        }
    }
    public static void menu_Pet() {
        while (true) {
            try {
                LimparTela();
                System.out.println(NEGRITO+CYAN+ "\t\t\tMÓDULO DE GERENCIAMENTO DE PET" +RESETAR);
                System.out.println(NEGRITO+AMARELO+ "Digite a opção desejada: " +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "1 - Cadastrar um Pet." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "2 - Atualizar cadastro de Pet." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "3 - Apagar o cadastro de um Pet." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "4 - Listar todos os Pets cadastrados." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "5 - Adicionar Pet a um Tutor existente" +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "6 - Remover Pet de um Tutor existente." +RESETAR);
                System.out.println(NEGRITO+BRANCO+ "7 - Voltar ao menu inicial." +RESETAR);
                System.out.print(NEGRITO+AMARELO+ "DIGITE A OPÇÃO ESCOLHIDA: " +RESETAR);
                int escolha_user = teclado.nextInt();
                teclado.nextLine();
                switch (escolha_user){
                    case 1: {
                        LimparTela();
                        PetController.cadastrar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 2: {
                        LimparTela();
                        PetController.atualizar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 3: {
                        LimparTela();
                        PetController.deletar();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 4: {
                        try {
                            LimparTela();
                            PetController.listar();
                        }
                        catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                        teclado.nextLine();
                        break; 
                    } case 5: {
                        LimparTela();
                        PetController.atribuirPet_Tutor();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                        teclado.nextLine();
                        break;
                    } case 6: {
                        LimparTela();
                        PetController.removerPet_Tutor();
                        System.out.print(NEGRITO+CYAN+ "\nPressione enter para voltar ao menu do MODULO DE GERENCIAMENTO DE PET..." +RESETAR);
                            teclado.nextLine();
                        break;
                    } default: {
                        LimparTela();
                        return;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                teclado.nextLine();
            }
        }
    }

    public static void data_seed() throws ListaVaziaException {
        TutorController.data_seed_tutor();
        PetController.data_seed_pet();
        FuncionarioController.data_seed_funcionario();

    }
    public static void main(String[] args) throws ListaVaziaException, DataInvalidaException {
        data_seed();
        menu_Principal();
    }

}


