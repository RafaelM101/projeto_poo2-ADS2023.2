package servicos.servicos;

import Pets.Cachorro;
import Pets.Gato;
import Pets.Pet;
import funcionarios.funcionarios.Funcionario;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgendarServico implements Serviço{

    private ListaServicos tipo_servico;
    private Funcionario nome_funcionario;
    private Float preco;
    private static ArrayList<String> agenda;
    private Pet pet;


    public AgendarServico(ListaServicos tipo_servico, Funcionario nome_funcionario, Float preco) {
        this.tipo_servico = tipo_servico;
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
    }

    public void Agendar(String tipoServiço, Funcionario nome_funcionario, String dia_hora  ) {
        


        
    }

    public void Cancelar() { 
        
    }

    public void Efetuar() {  
        
    }
  
    public void Receber() {
            
    }
    
}

