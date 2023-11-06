package main;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Scanner;

import funcionarios.Funcionario;
import servicos.AgendaDia;

public class Main {

    public static void main(String[] args) {

        Funcionario.cadastrar();
        Funcionario.listar();

        HashMap<LocalDate, AgendaDia> AgendaDiaria = new HashMap<LocalDate, AgendaDia>();
        
    }
}



