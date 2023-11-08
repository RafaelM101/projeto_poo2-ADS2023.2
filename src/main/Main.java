package main;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import Pets.Gato;
import Pets.Pet;
import Tutores.Tutor;
import funcionarios.Funcionario;
import servicos.AgendaDia;
import servicos.Servico;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            HashMap<LocalDate, AgendaDia> AgendaDiaria = new LinkedHashMap<>();
            LocalDate date_agenda = LocalDate.now();
            formatter.format(date_agenda);

        for (int i = 0; i < 8; i++) {
            LocalDate currentDate = date_agenda.plusDays(i);
            AgendaDia agendaDia = new AgendaDia();
            String dateFormatted = currentDate.format(formatter);
            System.out.println(dateFormatted + " = " + agendaDia);
            AgendaDiaria.put(currentDate, agendaDia);
        }
            System.out.println("\nCADASTRANDO TUTOR:");
            Tutor.cadastrar();
            System.out.println("\nCADASTRANDO PET:");
            Pet.cadastrar();
            System.out.println("\nCADASTRANDO FUNCIONARIO:");
            Funcionario.cadastrar();
            System.out.println("\nCADASTRANDO SERVIÇO:");
            Servico novo_servico = Servico.cadastrar();
           assert novo_servico != null;
            System.out.println("\nIMPRIMINDO AGENDA ATUALIZADA:");
            AgendaDia agendaDia = AgendaDiaria.get(novo_servico.getData_servico());
            agendaDia.agendarHorario(novo_servico.getHora_servico(), novo_servico);
            for (Map.Entry<LocalDate, AgendaDia> entry : AgendaDiaria.entrySet()) {
                date_agenda = entry.getKey();
                String dateFormatted = date_agenda.format(formatter);
                System.out.println(dateFormatted + " = " + entry.getValue() + "\n");
            }
            Funcionario.listar();
            System.out.println("Pressione Enter para voltar ao menu inicial...");
            teclado.nextLine(); // Aguarda a tecla Enter
        }

    }
