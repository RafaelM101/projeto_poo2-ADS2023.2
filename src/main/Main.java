package main;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import Pets.Gato;
import Pets.Pet;
import funcionarios.Funcionario;
import servicos.AgendaDia;
import servicos.Servico;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            HashMap<LocalDate, AgendaDia> AgendaDiaria = new LinkedHashMap<>();
            LocalDate date = LocalDate.now();
            formatter.format(date);

            for (int i = 0; i < 8; i++) {
                AgendaDiaria.put(date.plusDays(i), new AgendaDia());
            }

            // Pet.cadastrar();
            // Funcionario.cadastrar(teclado);

            // Servico novo_servico = Servico.cadastrar(teclado);
            // assert novo_servico != null;

            for (Map.Entry<LocalDate, AgendaDia> entry : AgendaDiaria.entrySet()) {
                date = entry.getKey();
                String dateFormatted = date.format(formatter);
                System.out.println(dateFormatted + " = " + entry.getValue() + "\n");
            }

            System.out.println("Pressione Enter para voltar ao menu inicial...");

            teclado.nextLine(); // Aguarda a tecla Enter
        }

    }
    }
