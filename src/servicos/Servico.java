package servicos;

import Pets.Pet;
import components.CRUD;
import funcionarios.Funcionario;

import java.util.Scanner;


public class Servico implements CRUD {

    private Funcionario nome_funcionario;
    private Float preco;
    private ListaServicos tipo_agendamento;
    private Pet pet_agendamento;

    public Servico(Funcionario nome_funcionario, Float preco, ListaServicos tipo_agendamento, Pet pet_agendamento) {
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
        this.tipo_agendamento = tipo_agendamento;
        this.pet_agendamento = pet_agendamento;
    }

    public static void cadastrar() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira a matrícula do Funcionário que irá realizar esse serviço: ");
        String matricula = input.next();
        if(Funcionario.consultarFuncionario(matricula)!=null){
            Funcionario funcionario_servico = Funcionario.consultarFuncionario(matricula);
            System.out.println("Insira o tipo do serviço que será realizado: BANHO, TOSA ou CONSULTA");
            String servico = input.next().toUpperCase();
            ListaServicos tipo_servico = ListaServicos.NULO;
            Float preco_servico = 0.00f;
            if (servico.equals(ListaServicos.BANHO.toString())) {
                tipo_servico = ListaServicos.BANHO;
                preco_servico = 45.00f;
            } else if (servico.equals(ListaServicos.CONSULTA.toString())) {
                tipo_servico = ListaServicos.CONSULTA;
                preco_servico = 90.00f;
            } else if (servico.equals(ListaServicos.TOSA.toString())) {
                tipo_servico = ListaServicos.TOSA;
                preco_servico = 60.00f;
            }else System.out.println("Serviço não encontrado.");
            System.out.println("Insira a matrícula do Pet que irá receber esse serviço: ");
             String matriculaPet = input.next();
            if(Pet.consultarPet(matriculaPet)!=null){
                Pet pet_servico = Pet.consultarPet(matriculaPet);
                if(tipo_servico!=ListaServicos.NULO) {
                    Servico novo_servico = new Servico(funcionario_servico,preco_servico,tipo_servico,pet_servico);}
            }
        }else System.out.println("Funcionário não localizado. Verifique a matrícula digitada.");
    }
    public static void listar() {};
    public static void atualizar() {};
    public static void deletar() {};
}

