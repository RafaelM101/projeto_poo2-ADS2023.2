package main;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import funcionarios.Funcionario;
import servicos.AgendaDia;

public class Main {

    public static void main(String[] args) {

        Funcionario.cadastrar();
        Funcionario.listar();

        HashMap<Date, AgendaDia> AgendaDiaria = new HashMap<Date, AgendaDia>();
        
    }
}



