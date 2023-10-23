package main;

import funcionarios.funcionarios.Funcionario;
import funcionarios.funcionarios.Veterinario;

public class Main {

    public static void main(String[] args) {
    	Veterinario n = new Veterinario("l", 2, "l","l", "l", "l", "l");
    	System.out.println(n.getCRMV());
    }
}