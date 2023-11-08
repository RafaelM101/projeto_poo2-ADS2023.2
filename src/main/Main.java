package main;


import Pets.Pet;
import Tutores.Tutor;
import funcionarios.Funcionario;
import servicos.Servico;

public class Main {

    public static void main(String[] args) {
        Tutor.cadastrar();
        Pet.cadastrar();
        Funcionario.cadastrar();
        Servico.cadastrar();
        Servico.listar();
        Servico.atualizar();
        Servico.listar();
        Servico.deletar();
        Servico.listar();
        }

    }
