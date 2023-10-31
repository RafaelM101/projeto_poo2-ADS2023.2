package servicos;

import java.util.ArrayList;

import Pets.Pet;
import funcionarios.Funcionario;

public class Agendamento{

    private Funcionario nome_funcionario;
    private Float preco;
    private static ArrayList<String> agenda;
    private Pet pet;


    public AgendarServico(Funcionario nome_funcionario, Float preco) {
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
    }

    public void Agendar(String tipoServiço, Funcionario nome_funcionario, String dia_hora  ) {
        


        
    }

    
}

