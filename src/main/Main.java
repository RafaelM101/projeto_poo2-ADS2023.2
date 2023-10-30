package main;

import java.time.LocalDate;

import Pets.Gato;
import funcionarios.funcionarios.Funcionario;
import funcionarios.funcionarios.Veterinario;

public class Main {

    public static void main(String[] args) {
    	Veterinario n = new Veterinario("l", 2, "l","l", "l", "l", "l");
    	System.out.println(n.getCRMV());

        Funcionario cu = new Funcionario( "123", 1.500, "123", "Tabica", "IF");
        System.out.println(cu.getNome());
        System.out.println(cu.getSalario());
        System.out.println(cu.getCPF());
        System.out.println(cu.getSetor());

        Gato g = new Gato("Pablo Vittar", "123" , LocalDate.now(), "whiskas");
        System.out.println(g.getNomePet());
        System.out.println(g.getMatriculaPet());
        System.out.println(g.getIdade());
        System.out.println(g.getRacaGato());

    }
}



//	protected String matricula;
// protected double salario;
// protected String CPF;
// protected String nome;
// protected String setor;