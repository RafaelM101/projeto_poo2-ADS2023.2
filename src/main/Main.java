package main;


import Pets.Gato;
import Pets.Pet;
import Tutores.Tutor;
import funcionarios.Funcionario;
import servicos.Servico;

import java.util.Scanner;

public class Main {

    public static void Menu_AgendarServico(){
        Scanner teclado = new Scanner(System.in);
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
                    teclado.next();
                    teclado.nextLine();
                    break;
                case 2:
                    Servico.atualizar();
                    System.out.println("Pressione enter para voltar ao menu MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.next();
                    teclado.nextLine();
                    break;
                case 3:
                    Servico.deletar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.next();
                    teclado.nextLine();
                    break;
                case 4:
                    Servico.listar();
                    System.out.println("Pressione enter para voltar ao menu do MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.next();
                    teclado.nextLine();
                    break;
                case 5:
                    System.out.println("Digite a matrícula do funcionário desejado:");
                    String matricula_func = teclado.nextLine().strip();
                    Funcionario funcionario_agenda = Funcionario.consultarFuncionario(matricula_func);
                    funcionario_agenda.listarHorarios();
                    System.out.println("Pressione enter para voltar ao MODULO DE AGENDAMENTO DE SERVIÇOS...");
                    teclado.next();
                    break;
                default:
                    System.out.println("Pressione enter para voltar ao menu inicial...");
                    teclado.nextLine();
                    return;
            }
    }}
    public static void main(String[] args) {
        //Funcionario.cadastrar();
        //Menu_AgendarServico();
        //Tutor.cadastrar();
        //Pet.cadastrar();
        //Pet.cadastrar();
    }

}

//Modulo MENU PRINCIPAL(Rafael): Escolher entre os Módulo TUTOR, PET, FUNCIONARIO e AGENDAR SERVIÇO.
//Modulo TUTOR(Kerlen): Cadastrar Tutor, Listar Todos os Tutores,
//atualizar tutor existente, apagar tutor, Consultar Tutor por CPF, EXIBIR ENDEREÇO e TELEFONE DE TUTOR.

//Modulo Funcionario: (Emmanuel )Cadastrar - Normal ou Veterinario, Listar Todos, Listar por matrícula, Atualizar Salário, Apagar

//Modulo Agendar Serviço:(Rafael) CADASTRAR SERVIÇO, ATUALIZAR SERVIÇO, DESMARCAR SERVIÇO, Listar serviço por data, Listar todos, Exibir agenda de funcionário pela matrícula

//Modulo Pet (Kerlen): CADASTRAR PET, ATUALIZAR PET, EXCLUIR PET, LISTAR TODOS OS PETS, Listar PET por funcionario,  Adicionar Pet em Tutor existente, Remover Pet de Tutor Existente,