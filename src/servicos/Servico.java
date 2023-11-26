package servicos;

import Pets.Pet;
import components.CRUD;
import components.Terminal;
import components.Validar;
import exceptions.DataInvalidaException;
import exceptions.EscolhaInvalidaException;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;
import main.Main;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;


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
    public Funcionario getNome_funcionario() {
        return nome_funcionario;
    }

    public ListaServicos getTipo_agendamento() {
        return tipo_agendamento;
    }

    public LocalDate getData_servico() {
        return data_servico;
    }

    public LocalTime getHora_servico() {
        return hora_servico;
    }

    //SETTERS
    public void setNome_funcionario(Funcionario nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public void setTipo_agendamento(ListaServicos tipo_agendamento) {
        this.tipo_agendamento = tipo_agendamento;
    }

    public void setData_servico(LocalDate data_servico) {
        this.data_servico = data_servico;
    }

    public void setHora_servico(LocalTime hora_servico) {
        this.hora_servico = hora_servico;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return CYAN + NEGRITO + "Data: " + RESETAR + VERDE +formatter.format(data_servico) + "\n" + RESETAR +
                CYAN + NEGRITO + "Horário: " + RESETAR + VERDE +hora_servico + "\n" + RESETAR +
                CYAN + NEGRITO+ "Funcionário:" + RESETAR + VERDE +nome_funcionario.getNome() + "\n" + RESETAR +
                CYAN + NEGRITO + "Tipo do serviço: " + RESETAR + VERDE +tipo_agendamento + "\n" + RESETAR +
                CYAN + NEGRITO + "Preço: " + RESETAR + VERDE +"R$" + preco + "\n" + RESETAR +
                CYAN + NEGRITO + "Nome do pet: " + RESETAR + VERDE + pet_agendamento.getNomePet() + "\n" + RESETAR;

    }

    public static void cadastrar() {


        int servico;
        while (true) {
            try {
                System.out.println(CYAN + "Escolha o tipo do serviço que será realizado:" + RESETAR);
                System.out.print(AZUL + "1 - BANHO\n2 - CONSULTA\n3 - TOSA\n" + RESETAR + AMARELO + "INSIRA A OPÇÃO DESEJADA: " + RESETAR);
                servico = teclado.nextInt();
                teclado.nextLine();
                if (servico != 1 && servico != 2 && servico != 3) {
                    throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tDigite um valor válido: 1 - BANHO, 2 - CONSULTA, 3 - TOSA.\n " + RESETAR);
                } else break;
            } catch (InputMismatchException e) {
                System.out.println(VERMELHO + "\n\t\t\tValor digitado não é um número. Tente novamente.\n" + RESETAR);
                teclado.nextLine();
            } catch (EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
            }

        }

        ListaServicos tipo_servico = ListaServicos.NULO;
        float preco_servico = 0.00f;
        if (servico == 1) {
            tipo_servico = ListaServicos.BANHO;
            preco_servico = 45.00f;
        } else if (servico == 2) {
            tipo_servico = ListaServicos.CONSULTA;
            preco_servico = 90.00f;
        } else{
            tipo_servico = ListaServicos.TOSA;
            preco_servico = 60.00f;
        }

        String matricula;
        Funcionario funcionario_servico;
        while (true) {
            System.out.print(AMARELO + NEGRITO +"\nInsira a matrícula do Funcionário que irá realizar esse serviço: " + RESETAR);
            matricula = teclado.nextLine();
            if (Funcionario.consultarFuncionario(matricula) == null) {
                System.out.println(VERMELHO + "\n\t\t\tFuncionário não encontrado. Verifique a matrícula digitada." + RESETAR);
                continue;
            }

            funcionario_servico = Funcionario.consultarFuncionario(matricula);

            if (tipo_servico != ListaServicos.CONSULTA && funcionario_servico instanceof Veterinario) {
                System.out.println(VERMELHO + "\n\t\t\tVeterinários não podem realizar outros serviços além de consultas." + RESETAR);
                continue;
            }

            if (tipo_servico.equals(ListaServicos.CONSULTA)) {
                if (!(funcionario_servico instanceof Veterinario)) {
                    System.out.println(VERMELHO + "\n\t\t\tApenas veterinários podem realizar consultas." + RESETAR);
                    continue;
                }
            }
            break;
        }

        String matriculaPet;
        while (true) {
            System.out.print(AMARELO + NEGRITO +"\nInsira a matrícula do Pet que irá receber esse serviço: " + RESETAR);
            matriculaPet = teclado.next();
            teclado.nextLine();
            if (Pet.consultarPet(matriculaPet) == null) {
                System.out.println(VERMELHO + "\n\t\t\tPet não encontrado. Verifique a matrícula digitada." + RESETAR);
                continue;
            }
            break;
        }

        Pet pet_servico = Pet.consultarPet(matriculaPet);

        LocalDate data_escolhida;
        LocalTime horario_escolhido;
        while (true) {

            ArrayList<String> lista_datas = new ArrayList<>();

            for (int i = 0; i <= 7; i++) {
                if(i==0 && LocalTime.now().isAfter(LocalTime.of(18,30))){}
                else {lista_datas.add(formatter.format(LocalDate.now().plusDays(i)));}
            }

            System.out.println(CYAN + NEGRITO +"\nDatas disponíveis para agendamentos: \n" + RESETAR);
            Servico.imprimirDatas();
            System.out.print(AMARELO + NEGRITO +"\n\nInsira a data que será realizada o serviço: " + RESETAR);
            String data = teclado.nextLine();

            if (!(lista_datas.contains(data))) {
                System.out.print(VERMELHO+"\t\t\t\nA Data escolhida não foi localizada. Verifique a data digitada e tente novamente.\n" + RESETAR);
                continue;
            }

            data_escolhida = LocalDate.parse(data, formatter);
            break;
        }
        System.out.println(" ");
        funcionario_servico.imprimirAgendaDia(data_escolhida);
        while(true){
            System.out.print(AMARELO + NEGRITO +"\nInsira o horário que será realizado o serviço: " + RESETAR);
            String horario = teclado.next();
            teclado.nextLine();
            horario_escolhido = LocalTime.parse(horario);
            if(!(funcionario_servico.verificarHorario(data_escolhida,horario_escolhido))){
                System.out.println(VERMELHO + "\t\t\t\nO Horário digitado não está disponível para esse funcionário. Insira um horário disponível.\n" + RESETAR);
                continue;
            }
            break;
        }

        Servico novo_servico = new Servico(funcionario_servico, preco_servico, tipo_servico, pet_servico);
        novo_servico.setData_servico(data_escolhida);
        novo_servico.setHora_servico(horario_escolhido);
        lista_servicos.add(novo_servico);
        funcionario_servico.agendarHorario(novo_servico);
        System.out.println(CYAN + NEGRITO + "\t\t\t\nSERVIÇO CADASTRADO COM SUCESSO!\n" + RESETAR);
        System.out.println(novo_servico);

    }


    public static void listar() throws ListaVaziaException {
        if (lista_servicos.isEmpty()) {
            throw new ListaVaziaException("Não há nenhum serviço agendado.");
        }
        System.out.print("Deseja listar por data específica?\nDIGITE S ou N: ");
        String option = teclado.next().toUpperCase().strip();
        teclado.nextLine();
        if (option.equals("S")) {
            System.out.print("\nDigite a data dos serviços que deseja listar: ");
            String data = teclado.nextLine();
            LocalDate data_escolhida = LocalDate.parse(data, formatter);
            for (Servico servico : lista_servicos) {
                if (servico.getData_servico().equals(data_escolhida)) {
                    Collections.sort(lista_servicos, new ServicoComparator());
                    System.out.println(servico.toString());
                }
            }
        } else if (option.equals("N")) {
            Collections.sort(lista_servicos, new ServicoComparator());
            System.out.println("Lista de serviços cadastrados");
            for (Servico servico : lista_servicos) {
                System.out.println(servico.toString());
            }
        } else {
            System.out.println("Opção inválida.");
        }

    }

    public static void atualizar() {
        String data, horario, matricula;
        Servico servico_atualizar;
        while (true) {
            try {
                while (true){
                System.out.println("Insira a matrícula do funcionário que está com esse serviço agendado: ");
                matricula = teclado.nextLine().strip();
                if(Funcionario.consultarFuncionario(matricula)==null){
                    throw new ListaVaziaException(VERMELHO +"\n\t\t\tA Matrícula informada não está associada à nenhum funcionário, tente novamente.\n" + RESETAR);
                }
                break;
                }

                System.out.println("Insira a data cadastrada do serviço que será desmarcado: ");
                data = teclado.nextLine().trim();
                Validar.ValidarDate(data);
                System.out.println("Insira o horário cadastrado do serviço que será desmarcado: ");
                horario = teclado.next().trim();
                teclado.nextLine();
                Servico.ConsultarServico(matricula,data, horario);
                servico_atualizar = Servico.ConsultarServico(matricula, data, horario);
            } catch (DataInvalidaException | ListaVaziaException e) {
                System.out.println("\t\t\t" + e.getMessage());
                System.out.println(VERMELHO + "\t\t\tPressione" + RESETAR + " " + FUNDO_AMARELO + VERMELHO + "ENTER" + RESETAR + VERMELHO + " para tentar novamente ou " + RESETAR + FUNDO_AMARELO + VERMELHO + "digite 1" + RESETAR + VERMELHO + " " + "para voltar ao menu do Módulo de Agendamento de Serviços." + RESETAR + VERMELHO + RESETAR);
                String escolha = teclado.nextLine();
                if (escolha.equals("1")) return;
                continue;
            }

            break;
        }
        Funcionario funcionario = servico_atualizar.getNome_funcionario();
        while (true) {
            System.out.print(AMARELO + NEGRITO +"Digite o campo que deseja mudar:\n1- DATA\n2 - HORARIO\n3 - TIPO\n4 - FUNCIONARIO:" + RESETAR);
            int option = teclado.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Digite a nova DATA do serviço: ");
                    String data_nova = teclado.nextLine().strip();
                    servico_atualizar.setData_servico(LocalDate.parse(data_nova, formatter));
                    funcionario.desmarcarHorario(servico_atualizar);
                    if (funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                        funcionario.agendarHorario(servico_atualizar);
                    }
                    break;
                case 2:
                    funcionario.desmarcarHorario(servico_atualizar);
                    System.out.println("Digite o novo HORARIO do serviço: ");
                    String horario_novo = teclado.nextLine().strip();
                    servico_atualizar.setHora_servico(LocalTime.parse(horario_novo));
                    if (funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                        funcionario.agendarHorario(servico_atualizar);
                    }
                    break;
                case 3:
                    System.out.println("Digite o novo TIPO do serviço: \nBANHO, TOSA, CONSULTA:");
                    String novo_tipo = teclado.next().strip().toUpperCase();
                    novo_tipo = novo_tipo.toUpperCase();
                    teclado.nextLine();
                    switch (novo_tipo) {
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
                            if (Funcionario.consultarFuncionario(matricula_vet) instanceof Veterinario) ;
                        {
                            servico_atualizar.setNome_funcionario(Funcionario.consultarFuncionario(matricula_vet));
                            servico_atualizar.setTipo_agendamento(ListaServicos.CONSULTA);
                            servico_atualizar.setPreco(90.00f);
                            funcionario.agendarHorario(servico_atualizar);
                            break;
                        }
                    }
                case 4:
                    funcionario.desmarcarHorario(servico_atualizar);
                    System.out.println("Insira a matrícula do novo funcionário: ");
                    String matricula_funcionario = teclado.next().strip();
                    teclado.nextLine();
                    Funcionario novo_funcionario = Funcionario.consultarFuncionario(matricula_funcionario);
                    if (servico_atualizar.getTipo_agendamento() == ListaServicos.CONSULTA && !(novo_funcionario instanceof Veterinario)
                            || servico_atualizar.getTipo_agendamento() != ListaServicos.CONSULTA && novo_funcionario instanceof Veterinario) {
                        System.out.println("Não é possível cadastrar um Veterinário em um Serviço que não seja uma consulta");
                        break;
                    } else if (novo_funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                        servico_atualizar.setNome_funcionario(novo_funcionario);
                        novo_funcionario.agendarHorario(servico_atualizar);
                        break;
                    }
                default:
                    System.out.println("RETORNANDO AO MENU PRINCIPAL DO MODULO DE SERVICO.");
                    return;
            }


            System.out.println("Caso não precise fazer mais nenhuma alteração digite sair...");
        }
    }

    public static void deletar() throws ListaVaziaException {
        String data, horario, matricula;
        while (true) {
            try {
                System.out.println("Insira a matrícula do funcionário que está com esse serviço agendado: ");
                matricula = teclado.nextLine().strip();
                if(Funcionario.consultarFuncionario(matricula)==null){
                    throw new ListaVaziaException(VERMELHO +"\n\t\t\tA Matrícula informada não está associada à nenhum funcionário, tente novamente.\n" + RESETAR);
                }
                System.out.println("Insira a data cadastrada do serviço que será desmarcado: ");
                data = teclado.nextLine().trim();
                Validar.ValidarDate(data);
                System.out.println("Insira o horário cadastrado do serviço que será desmarcado: ");
                horario = teclado.next().trim();
                teclado.nextLine();
                Servico.ConsultarServico(matricula,data,horario);
        } catch (DataInvalidaException | ListaVaziaException e){
            System.out.println("\t\t\t"+e.getMessage());
            System.out.println(VERMELHO + "\t\t\tPressione"+ RESETAR + " " +FUNDO_AMARELO + VERMELHO+ "ENTER" +RESETAR + VERMELHO+ " para tentar novamente ou " + RESETAR + FUNDO_AMARELO + VERMELHO+ "digite 1" +RESETAR + VERMELHO + " " + "para voltar ao menu do Módulo de Agendamento de Serviços." + RESETAR + VERMELHO + RESETAR);
            String escolha = teclado.nextLine();
            if(escolha.equals("1")) return;
            continue;
            }

            break;
        }
            Servico servico_delete = ConsultarServico(matricula, data, horario);
            System.out.println("\nSERVIÇO A DESMARCAR:\n ");
            System.out.println("\n"+servico_delete);
            while(true) {
                int escolha = 0;
                try {
                    System.out.print("Tem certeza que deseja desmarcar o serviço?\nDigite 1 para SIM ou 2 para NÃO: ");
                    escolha = teclado.nextInt();
                    teclado.nextLine();
                    if (escolha != 1 && escolha != 2) {
                        throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tEscolha inválida. Escolha entre 1 - DELETAR SERVIÇO OU 2 - CANCELAR OPERAÇÃO" + RESETAR);
                    }
                } catch (InputMismatchException | EscolhaInvalidaException e) {
                    System.out.println(VERMELHO + "\n\t\t\tInsira apenas números." + NEGRITO);
                    continue;
                }
                if (escolha == 2) {
                    System.out.println("Operação cancelada, pressione qualquer tecla para retornar ao menu do Módulo de Agendamento de Serviços.");
                    teclado.nextLine();
                    return;
                }
                Funcionario funcionario_servico = Funcionario.consultarFuncionario(servico_delete.getNome_funcionario().getMatricula());
                funcionario_servico.desmarcarHorario(servico_delete);
                lista_servicos.remove(servico_delete);
                System.out.println(CYAN + NEGRITO + "AGENDAMENTO DESMARCADO COM SUCESSO!" + RESETAR);
                return;
            }


    }


    public static Servico ConsultarServico(String matricula, String data_servico, String hora_servico) throws ListaVaziaException {
        LocalDate data_escolhida = LocalDate.parse(data_servico, formatter);
        LocalTime horario_escolhido = LocalTime.parse(hora_servico);
        for (Servico servico : lista_servicos) {
            if (servico.getNome_funcionario().getMatricula().equals(matricula)&&servico.getData_servico().isEqual(data_escolhida) && servico.getHora_servico().equals(horario_escolhido)) {
                return servico;
            }
        }
        throw new ListaVaziaException(VERMELHO + "O serviço inserido não foi encontrado. Verifique os dados e tente novamente." + RESETAR);
    }

    public static void imprimirDatas() {
        LocalDate datas = LocalDate.now();
        for (int i = 0; i <= 7; i++) {
            if(LocalTime.now().isAfter(LocalTime.of(18,30))){
                continue;
            }
            String dataFormatada = formatter.format(datas.plusDays(i));
            System.out.print(AMARELO + "| " + RESETAR + VERDE + dataFormatada + RESETAR + AMARELO + "| " + RESETAR);
        }
    }
}



