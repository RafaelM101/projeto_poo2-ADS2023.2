package servicos;

import Pets.Pet;
import components.CRUD;
import components.Terminal;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;


public class Servico implements CRUD, Terminal {

    private Funcionario nome_funcionario;
    private Float preco;
    private ListaServicos tipo_agendamento;
    private Pet pet_agendamento;

    private LocalDate data_servico;

    private LocalTime hora_servico;

    public static ArrayList<Servico> lista_servicos = new ArrayList<>();


    public Servico(Funcionario nome_funcionario, Float preco, ListaServicos tipo_agendamento, Pet pet_agendamento) {
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
        this.tipo_agendamento = tipo_agendamento;
        this.pet_agendamento = pet_agendamento;
    }

    //GETTERS
    public Funcionario getNome_funcionario() {return nome_funcionario;}
    public ListaServicos getTipo_agendamento() {return tipo_agendamento;}
    public LocalDate getData_servico() {
        return data_servico;
    }
    public LocalTime getHora_servico() {
        return hora_servico;
    }

    //SETTERS
    public void setNome_funcionario(Funcionario nome_funcionario) {this.nome_funcionario = nome_funcionario;}
    public void setTipo_agendamento(ListaServicos tipo_agendamento) {this.tipo_agendamento = tipo_agendamento;}
    public void setData_servico(LocalDate data_servico) {
        this.data_servico = data_servico;
    }
    public void setHora_servico(LocalTime hora_servico) {
        this.hora_servico = hora_servico;
    }
    public void setPreco(Float preco) {this.preco = preco;}

    @Override
    public String toString() {
        return  VERMELHO + "Data: "+ RESETAR  + formatter.format(data_servico) + "\n" + RESETAR +
                VERMELHO + "Horário: " + RESETAR + hora_servico + "\n" + RESETAR +
                CYAN + "Funcionário:" + RESETAR + nome_funcionario.getNome() + "\n" + RESETAR +
                VERDE + "Tipo do serviço: " + RESETAR + tipo_agendamento + "\n" + RESETAR +
                VERDE + "Preço: " + RESETAR + "R$" + preco + "\n" + RESETAR +
                VERDE + "Nome do pet: "+ RESETAR + pet_agendamento.getNomePet() + "\n" + RESETAR;

    }

    public static void cadastrar() {
        //try {
            System.out.print("Escolha o tipo do serviço que será realizado:\n 1 - BANHO\n2 - TOSA\n3 - CONSULTA:");
            int servico = teclado.nextInt();
            teclado.nextLine();
            ListaServicos tipo_servico;
            float preco_servico;
            if (servico == 1) {
                tipo_servico = ListaServicos.BANHO;
                preco_servico = 45.00f;
            } else if (servico == 2) {
                tipo_servico = ListaServicos.CONSULTA;
                preco_servico = 90.00f;
            } else if (servico == 3) {
                tipo_servico = ListaServicos.TOSA;
                preco_servico = 60.00f;
            } else {
                System.out.println("Serviço não encontrado.");
                return;
            }

            System.out.println("Insira a matrícula do Funcionário que irá realizar esse serviço: ");
            String matricula = teclado.nextLine();
            if (Funcionario.consultarFuncionario(matricula) == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }
            Funcionario funcionario_servico = Funcionario.consultarFuncionario(matricula);
            if (tipo_servico != ListaServicos.CONSULTA && funcionario_servico instanceof Veterinario) {
                System.out.println("Veterinários não podem realizar outros serviços além de consultas.");
                return;
            }
            if (tipo_servico.equals(ListaServicos.CONSULTA)) {
                if (!(funcionario_servico instanceof Veterinario)) {
                    System.out.println("Apenas veterinários podem realizar consultas.");
                    return;
                }
            }
            System.out.println("Insira a matrícula do Pet que irá receber esse serviço: ");
            String matriculaPet = teclado.next();
            teclado.nextLine();
            if (Pet.consultarPet(matriculaPet) == null) {
                System.out.println("Pet não encontrado.");
                return;

            }
            Pet pet_servico = Pet.consultarPet(matriculaPet);

            System.out.println("\nDatas disponíveis para agendamentos: \n");
            Servico.imprimirDatas();
            System.out.print("\n\nInsira a data que será realizada o serviço: \n");
            String data = teclado.nextLine();
            LocalDate data_escolhida = LocalDate.parse(data, formatter);
            funcionario_servico.imprimirAgendaDia(data_escolhida);
            System.out.println("Insira o horário que será realizado o serviço: ");
            String horario = teclado.next();
            teclado.nextLine();
            LocalTime horario_escolhido = LocalTime.parse(horario);

            Servico novo_servico = new Servico(funcionario_servico, preco_servico, tipo_servico, pet_servico);
            novo_servico.setData_servico(data_escolhida);
            novo_servico.setHora_servico(horario_escolhido);
            lista_servicos.add(novo_servico);
            funcionario_servico.agendarHorario(novo_servico);
            System.out.println(novo_servico);
        }
        //catch (){}


    public static void listar() throws ListaVaziaException {
        if(lista_servicos.size()<1){
            throw new ListaVaziaException("Não há nenhum serviço agendado.");
        }
        System.out.println("Deseja listar por data específica?:  DIGITE S ou N");
        String option = teclado.next().toUpperCase().strip();
        teclado.nextLine();
        if(option.equals("S")){
            System.out.println("Digite a data dos serviços que deseja listar: ");
            String data = teclado.nextLine();
            LocalDate data_escolhida = LocalDate.parse(data, formatter);
            for( Servico servico : lista_servicos){
                if(servico.getData_servico().equals(data_escolhida)) {
                    Collections.sort(lista_servicos, new ServicoComparator());
                    System.out.println(servico.toString());
                    }
            }
        } else if(option.equals("N")) {
            Collections.sort(lista_servicos, new ServicoComparator());
            System.out.println("Lista de serviços cadastrados");
            for( Servico servico : lista_servicos){
                System.out.println(servico.toString());
            }
        } else {
            System.out.println("Opção inválida.");
        }

    }
    public static void atualizar() {
        System.out.println("Insira a DATA do serviço que será alterado: ");
        String data = teclado.nextLine();

        System.out.println("Insira o HORÁRIO do serviço que será alterado: ");
        String horario = teclado.next();
        teclado.nextLine();
        Servico servico_atualizar = Servico.ConsultarServico(data, horario);
        Funcionario funcionario = servico_atualizar.getNome_funcionario();
        while (true) {
            System.out.print("Digite o campo que deseja mudar:\n1- DATA\n2 - HORARIO\n 3 - TIPO\n4 - FUNCIONARIO:");
            int option = teclado.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Digite a nova DATA do serviço: ");
                    String data_nova = teclado.nextLine().strip();
                    servico_atualizar.setData_servico(LocalDate.parse(data_nova, formatter));
                    funcionario.desmarcarHorario(servico_atualizar);
                    if(funcionario.verificarHorario(servico_atualizar.getData_servico(),servico_atualizar.getHora_servico())){
                        funcionario.agendarHorario(servico_atualizar);}
                    break;
                case 2:
                    funcionario.desmarcarHorario(servico_atualizar);
                    System.out.println("Digite o novo HORARIO do serviço: ");
                    String horario_novo = teclado.nextLine().strip();
                    servico_atualizar.setHora_servico(LocalTime.parse(horario_novo));
                    if(funcionario.verificarHorario(servico_atualizar.getData_servico(),servico_atualizar.getHora_servico())){
                        funcionario.agendarHorario(servico_atualizar);
                    }
                    break;
                case 3:
                    System.out.println("Digite o novo TIPO do serviço: \nBANHO, TOSA, CONSULTA:");
                    String novo_tipo = teclado.next().strip().toUpperCase();
                    novo_tipo= novo_tipo.toUpperCase();
                    teclado.nextLine();
                    switch (novo_tipo){
                        case "BANHO":
                            servico_atualizar.setTipo_agendamento(ListaServicos.BANHO);
                            servico_atualizar.setPreco(45.00f);
                            break;
                        case "TOSA":
                            servico_atualizar.setTipo_agendamento(ListaServicos.TOSA);
                            servico_atualizar.setPreco(60.00f);
                        case "CONSULTA":
                            System.out.println("Será necessário alterar o funcionário, apenas veterinários podem realizar consultas.");
                            funcionario.desmarcarHorario(servico_atualizar);
                            System.out.println("Insira a matrícula do Veterinário que irá realizar a consulta: ");
                            String matricula_vet = teclado.next();
                            teclado.nextLine();
                           if (Funcionario.consultarFuncionario(matricula_vet) instanceof Veterinario);{
                                servico_atualizar.setNome_funcionario(Funcionario.consultarFuncionario(matricula_vet));
                                servico_atualizar.setTipo_agendamento(ListaServicos.CONSULTA);
                                servico_atualizar.setPreco(90.00f);
                                funcionario.agendarHorario(servico_atualizar);
                                break;}
                    }
                case 4:
                    funcionario.desmarcarHorario(servico_atualizar);
                    System.out.println("Insira a matrícula do novo funcionário: ");
                    String matricula_funcionario = teclado.next().strip();
                    teclado.nextLine();
                    Funcionario novo_funcionario = Funcionario.consultarFuncionario(matricula_funcionario);
                    if(servico_atualizar.getTipo_agendamento()==ListaServicos.CONSULTA && !(novo_funcionario instanceof Veterinario)
                       || servico_atualizar.getTipo_agendamento()!=ListaServicos.CONSULTA && novo_funcionario instanceof Veterinario ){
                        System.out.println("Não é possível cadastrar um Veterinário em um Serviço que não seja uma consulta");
                        break;
                    }
                    else if(novo_funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())){
                        servico_atualizar.setNome_funcionario(novo_funcionario);
                        novo_funcionario.agendarHorario(servico_atualizar);
                        break;
                    }
                default:
                    System.out.println("Opção não existe!");
                    return;}


            System.out.println("Caso não precise fazer mais nenhuma alteração digite sair...");
        }
    }

    public static void deletar(){
        System.out.println("Insira a data que seria realizada o serviço que será excluído: ");
        String data = teclado.nextLine();

        System.out.println("Insira o horário que seria realizado o serviço que será excluído: ");
        String horario = teclado.next();
        teclado.nextLine();

        if(Servico.ConsultarServico(data, horario)==null){
            System.out.println("Serviço não encontrado.");}
        else {
            Servico servico_delete = ConsultarServico(data,horario);
            Funcionario funcionario_servico = Funcionario.consultarFuncionario(servico_delete.getNome_funcionario().getMatricula());
            funcionario_servico.desmarcarHorario(servico_delete);
            lista_servicos.remove(servico_delete);

        }
    }

    public static Servico ConsultarServico(String data_servico, String hora_servico){
        LocalDate data_escolhida = LocalDate.parse(data_servico, formatter);
        LocalTime horario_escolhido = LocalTime.parse(hora_servico);
        for (Servico servico : lista_servicos){
            if(servico.getData_servico().isEqual(data_escolhida) && servico.getHora_servico().equals(horario_escolhido)){
                return servico;
            }
        }
        return null;
    }
    public static void imprimirDatas() {
        LocalDate datas = LocalDate.now();
        for (int i = 0; i <= 7; i++) {
            System.out.print("| " + formatter.format(datas.plusDays(i)) + "| ");
        }
    }
    }

