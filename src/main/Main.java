package main;


import Pets.Gato;
import Pets.Pet;
import Tutores.Tutor;
import funcionarios.Funcionario;
import funcionarios.Veterinario;
import servicos.Servico;

import java.util.Scanner;

public class Main {
    public static Scanner teclado = new Scanner(System.in);
    public static void menu_Principal() {
        while (true) {
            System.out.println("\t\t\tSISTEMA DE GERENCIAMENTO AMIGOPET");
            System.out.println("Escolha o módulo que deseja acessar: ");
            System.out.print("""
                    1 - MODULO DE GERENCIAMENTO DE FUNCIONÁRIOS
                    2 - MODULO DE GERENCIAMENTO DE TUTORES
                    3 - MODULO DE AGENDAMENTO DE SERVIÇOS
                    4 - MODULO DE GERENCIAMENTO DE PETS
                    5 - FINALIZAR PROGRAMA
                    DIGITE A OPÇÃO ESCOLHIDA:  """);
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user) {
                case 1:
                    menu_Funcionario();
                    break;
                case 2:
                    System.out.println("Menu ainda não foi implementado, pressione enter para voltar...");
                    teclado.nextLine();
                    break;
                case 3:
                    Menu_AgendarServico();
                    break;
                case 4:
                    System.out.println("Menu inda não foi implementado pressione enter para voltar...");
                    teclado.nextLine();
                    break;
                default:
                    return;
            }
        }
    }
    public static void menu_Funcionario(){
        while (true) {
            System.out.println("");
            System.out.println("\t\t\tMÓDULO DE GERENCIAMENTO DE FUNCIONÁRIOS");
            System.out.println("Escolha a ação que deseja realizar: ");
            System.out.println("");
            System.out.print("""
                    1 - Cadastrar um novo funcionário.
                    2 - Listar funcionarios.
                    3 - Atualizar salário de um funcionário.
                    4 - Demitir um funcionário.
                    5 - Voltar ao menu principal.
                    DIGITE A OPÇÃO ESCOLHIDA:""");
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            System.out.println("");
            switch (escolha_user) {
                case 6:
                    return;
                case 1:
                    Funcionario.cadastrar();
                    break;
                case 2:
                    System.out.print("""
                            Digite 1 para exibir todos os funcionários.
                            Digite 2 para exibir por matrícula.
                            DIGITE A OPÇÃO ESCOLHIDA: """);
                    int escolha_listar = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("\n");
                    if(escolha_listar == 1) {
                        Funcionario.listar();
                    }
                    else if(escolha_listar == 2){
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
                    break;
                case 3:
                    Funcionario.atualizar();
                    break;
                case 4:
                    Funcionario.deletar();
                    break;
                case 5:
                    return;
                default:
                    //possível exception
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void Menu_AgendarServico(){
        while(true){
            System.out.println("MÓDULO DE AGENDAMENTO DE SERVIÇOS");
            System.out.println("Digite a opção desejada: ");
            System.out.print("""
                    1 - Cadastrar um novo agendamento.
                    2 - Remarcar/Alterar um agendamento.
                    3 - Desmarcar um agendamento.
                    4 - Listar todos os agendamentos.
                    5 - Exibir Agenda de um Funcionário específico.
                    6 - Voltar ao menu inicial.
                    DIGITE A OPÇÃO ESCOLHIDA:\s""");
            int escolha_user = teclado.nextInt();
            teclado.nextLine();
            switch (escolha_user){
                case 1:
                    Servico.cadastrar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();
                    break;
                case 2:
                    Servico.atualizar();
                    System.out.println("Pressione enter para voltar ao menu MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();
                    break;
                case 3:
                    Servico.deletar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();
                    break;
                case 4:
                    Servico.listar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();

                    break;
                case 5:
                    System.out.println("Digite a matrícula do funcionário desejado:");
                    String matricula_func = teclado.nextLine().strip();
                    Funcionario funcionario_agenda = Funcionario.consultarFuncionario(matricula_func);
                    funcionario_agenda.listarHorarios();
                    System.out.println("Pressione enter para voltar ao MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.nextLine();
                    break;
                default:
                    System.out.println("Pressione enter para voltar ao menu inicial...");
                    teclado.nextLine();
                    return;
            }
    }}
    public static void main(String[] args) {
        menu_Principal();
        }

    }

}

//Modulo MENU PRINCIPAL(Rafael): Escolher entre os Módulo TUTOR, PET, FUNCIONARIO e AGENDAR SERVIÇO.
//Modulo TUTOR(Kerlen): Cadastrar Tutor, Listar Todos os Tutores,
//atualizar tutor existente, apagar tutor, Consultar Tutor por CPF, EXIBIR ENDEREÇO e TELEFONE DE TUTOR.

//Modulo Funcionario: (Emmanuel )Cadastrar - Normal ou Veterinario, Listar Todos, Listar por matrícula, Atualizar Salário, Apagar

//Modulo Agendar Serviço:(Rafael) CADASTRAR SERVIÇO, ATUALIZAR SERVIÇO, DESMARCAR SERVIÇO, Listar serviço por data, Listar todos, Exibir agenda de funcionário pela matrícula

//Modulo Pet (Kerlen): CADASTRAR PET, ATUALIZAR PET, EXCLUIR PET, LISTAR TODOS OS PETS, Listar PET por funcionario,  Adicionar Pet em Tutor existente, Remover Pet de Tutor Existente,