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
        Scanner input = new Scanner(System.in);

            System.out.println("\nCADASTRANDO TUTOR:");
            Tutor.cadastrar();
            System.out.println("\nCADASTRANDO PET:");
            Pet.cadastrar();
            System.out.println("\nCADASTRANDO FUNCIONARIO:");
            Funcionario.cadastrar();
            System.out.println("\nCADASTRANDO SERVIÇO:");
            Servico.cadastrar();
            System.out.println("\nCADASTRANDO SERVIÇO:");
            Servico.cadastrar();
            System.out.println("\nIMPRIMINDO AGENDA ATUALIZADA:");
            Funcionario funcionario = Funcionario.consultarFuncionario("123");
            funcionario.listarHorarios();

            Servico.cadastrar();


            Servico.listar();
            System.out.println("Pressione Enter para voltar ao menu inicial...");
            input.nextLine(); // Aguarda a tecla Enter
        }

    }
