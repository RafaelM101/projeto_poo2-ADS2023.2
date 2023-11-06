package servicos;

import Pets.Pet;
import components.CRUD;
import funcionarios.Funcionario;
import funcionarios.Veterinario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Servico {

    private Funcionario nome_funcionario;
    private Float preco;
    private ListaServicos tipo_agendamento;
    private Pet pet_agendamento;

    private LocalDate data_servico;

    private LocalTime hora_servico;

    private static ArrayList<Servico> lista_servicos = new ArrayList<>();


    public Servico(Funcionario nome_funcionario, Float preco, ListaServicos tipo_agendamento, Pet pet_agendamento) {
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
        this.tipo_agendamento = tipo_agendamento;
        this.pet_agendamento = pet_agendamento;
    }

    public static Servico cadastrar(Scanner teclado) {

        System.out.println("Insira o tipo do serviço que será realizado: BANHO, TOSA ou CONSULTA:");
        String servico = teclado.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ListaServicos tipo_servico = ListaServicos.NULO;
        Float preco_servico = 0.00f;
        if (servico.equals(ListaServicos.BANHO.toString())) {
            tipo_servico = ListaServicos.BANHO;
            preco_servico = 45.00f;
        } else if (servico.equals(ListaServicos.CONSULTA.toString())) {
            tipo_servico = ListaServicos.CONSULTA;
            preco_servico = 90.00f;
        } else if (servico.equals(ListaServicos.TOSA.toString())) {
            tipo_servico = ListaServicos.TOSA;
            preco_servico = 60.00f;
        } else {
            System.out.println("Serviço não encontrado.");
            return null;
        }

        System.out.println("Insira a matrícula do Funcionário que irá realizar esse serviço: ");
        String matricula = teclado.next();
        if (Funcionario.consultarFuncionario(matricula)==null) {
            System.out.println("Funcionário não encontrado.");
            return null;
        }
        Funcionario funcionario_servico = Funcionario.consultarFuncionario(matricula);
        if (tipo_servico.equals(ListaServicos.CONSULTA)) {
            if (!(funcionario_servico instanceof Veterinario)) {
                System.out.println("Apenas veterinários podem realizar consultas.");
                return null;
            }
        }
        System.out.println("Insira a matrícula do Pet que irá receber esse serviço: ");
        String matriculaPet = teclado.next();
        if (Pet.consultarPet(matriculaPet) == null) {
            System.out.println("Pet não encontrado.");
            return null;

        }
        Pet pet_servico = Pet.consultarPet(matriculaPet);

        System.out.println("Insira a data que será realizada o serviço: ");
        String data = teclado.next();
        LocalDate data_escolhida = LocalDate.parse(data);
        formatter.format(data_escolhida);

        System.out.println("Insira o horário que será realizado o serviço: ");
        String horario = teclado.next();
        LocalTime horario_escolhido = LocalTime.parse(horario);

        Servico novo_servico = new Servico(funcionario_servico, preco_servico, tipo_servico, pet_servico);
        novo_servico.setData_servico(data_escolhida);
        novo_servico.setHora_servico(horario_escolhido);
        lista_servicos.add(novo_servico);
        return novo_servico;

    }

    public LocalDate getData_servico() {
        return data_servico;
    }
    public LocalTime getHora_servico() {
        return hora_servico;
    }

    public void setData_servico(LocalDate data_servico) {
        this.data_servico = data_servico;
    }
    public void setHora_servico(LocalTime hora_servico) {
        this.hora_servico = hora_servico;
    }




    public static Servico ConsultarServico(String data_servico, String hora_servico){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data_escolhida = LocalDate.parse(data_servico);
        formatter.format(data_escolhida);
        LocalTime horario_escolhido = LocalTime.parse(hora_servico);
        for (Servico servico : lista_servicos){
            if(servico.getData_servico().isEqual(data_escolhida) && servico.getHora_servico().equals(horario_escolhido)){
                return servico;
            }
        }
        return null;
    }
    public static void listar() {}
    public static void atualizar() {

    }
    public static void deletar() {}
}

