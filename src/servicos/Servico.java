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
            
        }
    }
    public static void listar() {};
    public static void atualizar() {};
    public static void deletar() {};
}

