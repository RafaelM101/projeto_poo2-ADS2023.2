package controllers;

import Pets.Pet;
import components.CRUD;
import components.Terminal;
import components.Validar;
import exceptions.DataInvalidaException;
import exceptions.EscolhaInvalidaException;
import exceptions.HoraInvalidaException;
import exceptions.ListaVaziaException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;
import servicos.ListaServicos;
import servicos.Servico;
import servicos.ServicoComparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

import static main.Main.LimparTela;

public class ServicoController implements CRUD, Terminal{

    protected static ArrayList<Servico> lista_servicos = new ArrayList<>();

    public static void cadastrar() {
        int servico;
        LimparTela();
        System.out.println(CYAN + "\n| CADASTRO DE AGENDAMENTOS |" + RESETAR);
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

        ListaServicos tipo_servico;
        float preco_servico;
        if (servico == 1) {
            System.out.println(VERDE + "\n\t\t\tServiço escolhido: BANHO." + RESETAR);
            tipo_servico = ListaServicos.BANHO;
            preco_servico = 45.00f;
        } else if (servico == 2) {
            System.out.println(VERDE + "\n\t\t\tServiço escolhido: CONSULTA VETERINÁRIA" + RESETAR);
            tipo_servico = ListaServicos.CONSULTA;
            preco_servico = 90.00f;
        } else{
            System.out.println(VERDE + "\n\t\t\tServiço escolhido: TOSA." + RESETAR);
            tipo_servico = ListaServicos.TOSA;
            preco_servico = 60.00f;
        }

        String matricula;
        Funcionario funcionario_servico;
        while (true) {
            System.out.print(AMARELO + NEGRITO +"\nInsira a matrícula do Funcionário que irá realizar esse serviço: " + RESETAR);
            matricula = teclado.nextLine();
            if (FuncionarioController.consultarFuncionario(matricula) == null) {
                System.out.println(VERMELHO + "\n\t\t\tFuncionário não encontrado. Verifique a matrícula digitada." + RESETAR);
                continue;
            }

            funcionario_servico = FuncionarioController.consultarFuncionario(matricula);
            System.out.printf(VERDE + "\n\t\t\tFuncionário escolhido: %s.\n", funcionario_servico.getNome() + RESETAR);
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
            if (PetController.consultarPet(matriculaPet) == null) {
                System.out.println(VERMELHO + "\n\t\t\tPet não encontrado. Verifique a matrícula digitada." + RESETAR);
                continue;
            }
            break;
        }

        Pet pet_servico = PetController.consultarPet(matriculaPet);
        System.out.printf(VERDE + "\n\t\t\tPet escolhido: %s.\n", pet_servico.getNomePet() + RESETAR);
        LocalDate data_escolhida;
        LocalTime horario_escolhido;
        while (true) {

            ArrayList<String> lista_datas = new ArrayList<>();

            for (int i = 0; i <= 7; i++) {
                if (i == 0 && LocalTime.now().isAfter(LocalTime.of(18, 30))) {
                } else {
                    lista_datas.add(formatter.format(LocalDate.now().plusDays(i)));
                }
            }

            System.out.println(CYAN + NEGRITO + "\nDatas disponíveis para agendamentos: \n" + RESETAR);
            ServicoController.imprimirDatas();
            String data = null;
            try {
                System.out.print(AMARELO + NEGRITO + "\n\nInsira a data que será realizada o serviço: " + RESETAR);
                data = teclado.nextLine();

                if (!(lista_datas.contains(data))) {
                    System.out.print(VERMELHO + "\t\t\t\nA Data escolhida não foi localizada. Verifique a data digitada e tente novamente.\n" + RESETAR);
                    continue;
                }
                Validar.ValidarDate(data);
            } catch (DataInvalidaException e) {
                System.out.println(e.getMessage());
            }
            data_escolhida = LocalDate.parse(data, formatter);
            break;
        }
        System.out.println(" ");
        funcionario_servico.imprimirAgendaDia(data_escolhida);
        while(true){
            try{
                System.out.print(AMARELO + NEGRITO +"\nInsira o horário que será realizado o serviço: " + RESETAR);
                String horario = teclado.next();
                Validar.validarHora(horario);
                teclado.nextLine();
                horario_escolhido = LocalTime.parse(horario);
                if(!(funcionario_servico.verificarHorario(data_escolhida,horario_escolhido))){
                    System.out.println(VERMELHO + "\t\t\t\nO Horário digitado não está disponível para esse funcionário. Insira um horário disponível.\n" + RESETAR);
                    continue;
                }}
            catch (HoraInvalidaException e){
                System.out.println(e.getMessage());
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
        LimparTela();

    }


    public static void listar(){
        try{
            LimparTela();
            if (lista_servicos.isEmpty()) {
                throw new ListaVaziaException("Não há nenhum serviço agendado, retornando ao menu do modulo de agendamento de serviços.");
            }
            System.out.print(CYAN + "Deseja listar por data específica?\n1 - SIM\n2 - NÃO\nOPÇÃO: " + RESETAR);
            int option = teclado.nextInt();
            teclado.nextLine();
            if(option < 1 || option > 2){
                throw new EscolhaInvalidaException(VERMELHO +"ERRO: Escolha apenas entre 1 - Para listar por data específica\n2 - listar todos os agendamentos cadastrados.\n"+ RESETAR);
            }
            if (option==1) {
                System.out.print(AMARELO + "\nDigite a data dos serviços que deseja listar: " + RESETAR);
                String data = teclado.nextLine();
                Validar.ValidarDate(data);
                LocalDate data_escolhida = LocalDate.parse(data, formatter);
                for (Servico servico : lista_servicos) {
                    if (servico.getData_servico().equals(data_escolhida)) {
                        Collections.sort(lista_servicos, new ServicoComparator());
                        System.out.println(servico);
                        LimparTela();
                    }
                }
            } else {
                Collections.sort(lista_servicos, new ServicoComparator());
                System.out.println("Lista de serviços cadastrados");
                for (Servico servico : lista_servicos) {
                    System.out.println("\n" + servico.toString());
                    LimparTela();
                }
            }
        }
        catch (ListaVaziaException | DataInvalidaException | EscolhaInvalidaException e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException e){
            System.out.println(VERMELHO + "\n\t\t\tEscolha entre os números: 1 - PARA LISTAR POR DATA ESPECIFICA\n 2 - PARA LISTAR TODOS OS AGENDAMENTOS CADASTRADOS." + RESETAR);
        }

    }

    public static void atualizar() {
        String data, horario, matricula;
        Servico servico_atualizar;
        String atributos_originais;
        while (true) {
            try {
                while (true){
                    LimparTela();
                    System.out.println(CYAN + NEGRITO + "\t\t\t| ALTERAÇÃO DE AGENDAMENTO |" + RESETAR);
                    System.out.println(CYAN + "OBSERVAÇÃO: Para alterar um agendamento, é necessário ter em mão a matrícula do funcionário, a data e o horário." + RESETAR);
                    System.out.print(NEGRITO + AMARELO +"\nInsira a matrícula do funcionário que está com esse serviço agendado: " + RESETAR) ;
                    matricula = teclado.nextLine().strip();
                    if(FuncionarioController.consultarFuncionario(matricula)==null){
                        throw new ListaVaziaException(VERMELHO +"\n\t\t\tA Matrícula informada não está associada à nenhum funcionário, tente novamente.\n" + RESETAR);
                    }
                    break;
                }
                System.out.print(NEGRITO + AMARELO +"\nInsira a data cadastrada do agendamento que será alterado: " + RESETAR);
                data = teclado.nextLine().trim();
                Validar.ValidarDate(data);
                System.out.print(NEGRITO + AMARELO +"\nInsira o horário cadastrado do agendamento que será alterado: " + RESETAR);
                horario = teclado.next().trim();
                Validar.validarHora(horario);
                teclado.nextLine();
                servico_atualizar = ServicoController.ConsultarServico(matricula, data, horario);
                atributos_originais = ServicoController.clonarAtributos(servico_atualizar);
            } catch (DataInvalidaException | ListaVaziaException | HoraInvalidaException e) {
                System.out.println("\t\t\t" + e.getMessage());
                System.out.println(VERMELHO + "\n\t\t\tPressione" + RESETAR + " " + FUNDO_AMARELO + VERMELHO + "ENTER" + RESETAR + VERMELHO + " para tentar novamente ou " + RESETAR + FUNDO_AMARELO + VERMELHO + "digite 1" + RESETAR + VERMELHO + " " + "para voltar ao menu do Módulo de Agendamento de Serviços." + RESETAR + VERMELHO + RESETAR);
                String escolha = teclado.nextLine();
                if (escolha.equals("1")) return;
                continue;
            }

            break;
        }
        LimparTela();
        System.out.println(CYAN + NEGRITO + "\n\t\t\tSERVIÇO LOCALIZADO!" + RESETAR);
        System.out.println(CYAN + "DADOS DO SERVIÇO:" + RESETAR);
        System.out.printf(CYAN + "\t\tFuncionário: %s", servico_atualizar.getNome_funcionario().getNome() + RESETAR);
        System.out.printf(CYAN +"\n\t\tPet: %s",servico_atualizar.getPet_agendamento().getNomePet() + RESETAR);
        System.out.printf(CYAN +"\n\t\tTipo: %s",servico_atualizar.getTipo_agendamento() + RESETAR);
        System.out.printf( CYAN + "\n\t\tData: %s",servico_atualizar.getData_servico() + RESETAR);
        System.out.printf(CYAN +"\n\t\tHora: %s",servico_atualizar.getHora_servico() + RESETAR);
        Funcionario funcionario = servico_atualizar.getNome_funcionario();
        while (true) {
            int option;
            try {

                System.out.print(AMARELO + NEGRITO + "\nDigite o campo que deseja mudar:\n1 - DATA\n2 - HORARIO\n3 - TIPO\n4 - FUNCIONARIO"+ RESETAR + CYAN + "\nPRESSIONE QUALQUER OUTRO NÚMERO PARA FINALIZAR A ALTERAÇÃO NO AGENDAMENTO.\nOPÇÃO: " + RESETAR);
                option = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(VERMELHO + "\n\t\t\tERRO: Insira apenas números!" + RESETAR);
                teclado.nextLine();
                continue;
            }
            switch (option) {
                case 1:
                    try {
                        LimparTela();
                        System.out.println(CYAN + "\n| Alteração de Data de Agendamento |" + RESETAR);
                        System.out.println(NEGRITO + AMARELO + "\nDigite a nova DATA do serviço: " + RESETAR);
                        System.out.println(NEGRITO + AMARELO + "\tDatas disponíveis:\n" + RESETAR);
                        ServicoController.imprimirDatas();
                        System.out.print(AMARELO + "\nDigite a nova data: " + RESETAR);
                        String data_nova = teclado.nextLine().strip();
                        Validar.ValidarDate(data_nova);
                        funcionario.desmarcarHorario(servico_atualizar);
                        servico_atualizar.setData_servico(LocalDate.parse(data_nova, formatter));
                        if (funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                            funcionario.agendarHorario(servico_atualizar);
                            System.out.println(CYAN + NEGRITO + "\n\t\t\tDATA ALTERADA COM SUCESSO!\n" + RESETAR);
                            LimparTela();
                            break;

                        } else {
                            throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tO funcionário cadastrado neste serviço não possui disponibilidade de horário nesta nova data, tente outra data ou atualize o horário.\n" + RESETAR);
                        }
                    } catch (DataInvalidaException | EscolhaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        LimparTela();
                        System.out.println(CYAN + "|\n Alteração de Horário de Agendamento |\n" + RESETAR);
                        funcionario.desmarcarHorario(servico_atualizar);
                        System.out.print(AMARELO + "Digite o novo HORARIO do serviço: " + RESETAR);
                        String horario_novo = teclado.nextLine().strip();
                        Validar.validarHora(horario_novo);
                        servico_atualizar.setHora_servico(LocalTime.parse(horario_novo));
                        if (funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                            funcionario.agendarHorario(servico_atualizar);
                            System.out.println(CYAN + NEGRITO + "\n\t\t\tHorário alterado com sucesso!\n" + RESETAR);
                            LimparTela();
                        }
                    } catch (HoraInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    int novo_tipo;
                    while (true) {
                        try {
                            LimparTela();
                            System.out.println(CYAN + "|\n\t\t\t Alteração de Tipo de Agendamento |\n");
                            System.out.println(AMARELO +"Digite o novo TIPO do serviço: \n1 - BANHO\n2 - TOSA\n3 - CONSULTA:" + RESETAR);
                            novo_tipo = teclado.nextInt();
                            teclado.nextLine();
                            if (novo_tipo < 1 || novo_tipo > 3) {
                                throw new EscolhaInvalidaException(VERMELHO + "\n\t\t\tERRO: Opção não existe, insira um número válido!" + RESETAR);
                            }
                        } catch (InputMismatchException e) {
                            System.out.print(VERMELHO + "\n\t\t\tERRO:Digite apenas um número:\n1 para BANHO\n2 para TOSA\n3 para CONSULTA\n" + RESETAR);
                            continue;
                        } catch (EscolhaInvalidaException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    }
                    switch (novo_tipo) {
                        case 1:
                            System.out.println(CYAN +"\n\t\t\t| Alteração de Tipo de Serviço para Banho |\n" + RESETAR);
                            servico_atualizar.setTipo_agendamento(ListaServicos.BANHO);
                            servico_atualizar.setPreco(45.00f);
                            System.out.println(CYAN + "TIPO DO SERVIÇO ALTERADO PARA BANHO COM SUCESSO!" + RESETAR);
                            System.out.println(CYAN + "Pressione enter para voltar ao menu de Atualizar Agendamento." + RESETAR);
                            teclado.nextLine();
                            LimparTela();
                            break;
                        case 2:
                            System.out.println(CYAN +"\n\t\t\t| Alteração de Tipo de Serviço Tosa |\n" + RESETAR);
                            servico_atualizar.setTipo_agendamento(ListaServicos.TOSA);
                            servico_atualizar.setPreco(60.00f);
                            System.out.println(CYAN + "TIPO DO SERVIÇO ALTERADO PARA TOSA COM SUCESSO!" + RESETAR);
                            System.out.println(CYAN + "Pressione enter para voltar ao menu de Atualizar Agendamento." + RESETAR);
                            teclado.nextLine();
                            LimparTela();
                            break;
                        case 3:
                            try {
                                System.out.println(CYAN +"\n\t\t\t| Alteração de Tipo de Serviço Consulta Veterinária |\n" + RESETAR);
                                System.out.println(NEGRITO + AMARELO + "Será necessário alterar o funcionário, apenas veterinários podem realizar consultas." + RESETAR);
                                funcionario.desmarcarHorario(servico_atualizar);
                                System.out.println(NEGRITO + AMARELO + "Insira a matrícula do Veterinário que irá realizar a consulta ou pressione 1 para cancelar a operação: " + RESETAR);
                                String matricula_vet = teclado.next();
                                if (FuncionarioController.consultarFuncionario(matricula_vet) == null) {
                                    throw new EscolhaInvalidaException(VERMELHO + "ERRO: A matrícula digitada não está associada a um Veterinário ou não existe." + RESETAR);
                                }
                                teclado.nextLine();
                                if (matricula_vet.equals("1")) {
                                    break;
                                } else if (FuncionarioController.consultarFuncionario(matricula_vet) instanceof Veterinario) {
                                    servico_atualizar.setNome_funcionario(FuncionarioController.consultarFuncionario(matricula_vet));
                                    servico_atualizar.setTipo_agendamento(ListaServicos.CONSULTA);
                                    servico_atualizar.setPreco(90.00f);
                                    funcionario.agendarHorario(servico_atualizar);
                                    System.out.println(CYAN + NEGRITO + "\n\t\t\tTIPO DE SERVIÇO ATUALIZADO PARA CONSULTA COM SUCESSO!\n**Também foi alterado o funcionário." + RESETAR);
                                    LimparTela();
                                    break;
                                }
                            } catch (EscolhaInvalidaException e) {
                                System.out.println(e.getMessage());
                            }
                    }
                    break;
                case 4:
                    LimparTela();
                    System.out.println(CYAN +"\n\t\t\t| Alteração de Funcionário |\n" + RESETAR);
                    System.out.println(CYAN + "Obs: É necessário verificar se o novo funcionário possui disponibilidade na Data e Horário atuais do serviço." + RESETAR);
                    funcionario.desmarcarHorario(servico_atualizar);
                    System.out.println("Insira a matrícula do novo funcionário: ");
                    String matricula_funcionario = teclado.next().strip();
                    teclado.nextLine();
                    Funcionario novo_funcionario = FuncionarioController.consultarFuncionario(matricula_funcionario);
                    if (servico_atualizar.getTipo_agendamento() == ListaServicos.CONSULTA && !(novo_funcionario instanceof Veterinario)
                            || servico_atualizar.getTipo_agendamento() != ListaServicos.CONSULTA && novo_funcionario instanceof Veterinario) {
                        System.out.println(VERMELHO + "\n\t\t\tERRO: Não é possível cadastrar um Veterinário em um Serviço que não seja uma consulta nem Cadastrar um Funcionário que não é Veterinário em uma Consulta." + RESETAR);
                        System.out.println(VERMELHO + "\t\t\tverifique os dados e tente novamente." + RESETAR);
                        break;
                    } else if (novo_funcionario.verificarHorario(servico_atualizar.getData_servico(), servico_atualizar.getHora_servico())) {
                        servico_atualizar.setNome_funcionario(novo_funcionario);
                        novo_funcionario.agendarHorario(servico_atualizar);
                        System.out.println(CYAN + "\n\t\t\tALTERAÇÃO DE FUNCIONÁRIO FEITA COM SUCESSO!" + RESETAR);
                        LimparTela();
                        break;
                    }
                default:
                    LimparTela();
                    System.out.println(NEGRITO + CYAN + "\t\t\tVERIFIQUE AS ALTERAÇÕES REALIZADAS NO SERVIÇO:" + RESETAR);
                    System.out.println(CYAN + NEGRITO + "\tAGENDAMENTO ORIGINAL:" + RESETAR);
                    System.out.println(VERMELHO + atributos_originais + RESETAR);
                    System.out.println(CYAN + "AGENDAMENTO APÓS ALTERAÇÕES:" + RESETAR);
                    System.out.println(servico_atualizar);
                    return;
            }

        }
    }

    public static void deletar(){
        String data, horario, matricula;
        Servico servico_delete;
        while (true) {
            try {
                LimparTela();
                System.out.println(CYAN + NEGRITO + "\n| DESMARCAR AGENDAMENTO |\n" + RESETAR);
                System.out.println("Insira a matrícula do funcionário que está com esse serviço agendado: ");
                matricula = teclado.nextLine().strip();
                if(FuncionarioController.consultarFuncionario(matricula)==null){
                    throw new ListaVaziaException(VERMELHO +"\n\t\t\tA Matrícula informada não está associada à nenhum funcionário, tente novamente.\n" + RESETAR);
                }
                System.out.println("Insira a data cadastrada do serviço que será desmarcado: ");
                data = teclado.nextLine().trim();
                Validar.ValidarDate(data);
                System.out.println("Insira o horário cadastrado do serviço que será desmarcado: ");
                horario = teclado.next().trim();
                Validar.validarHora(horario);
                teclado.nextLine();
                servico_delete = ServicoController.ConsultarServico(matricula,data,horario);
            } catch (DataInvalidaException | ListaVaziaException | HoraInvalidaException e){
                System.out.println("\t\t\t"+e.getMessage());
                System.out.println(VERMELHO + "\t\t\tPressione"+ RESETAR + " " +FUNDO_AMARELO + VERMELHO+ "ENTER" +RESETAR + VERMELHO+ " para tentar novamente ou " + RESETAR + FUNDO_AMARELO + VERMELHO+ "digite 1" +RESETAR + VERMELHO + " " + "para voltar ao menu do Módulo de Agendamento de Serviços." + RESETAR + VERMELHO + RESETAR);
                String escolha = teclado.nextLine();
                if(escolha.equals("1")) return;
                continue;
            }
            break;
        }
        System.out.println("\nSERVIÇO A DESMARCAR:\n ");
        System.out.println("\n"+servico_delete);
        while(true) {
            int escolha;
            try {
                System.out.print(NEGRITO + CYAN +"\nTem certeza que deseja desmarcar o serviço?\nDigite 1 para SIM ou 2 para NÃO: " + RESETAR);
                teclado.nextLine();
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
                System.out.println(NEGRITO + AMARELO +"Operação cancelada, pressione qualquer tecla para retornar ao menu do Módulo de Agendamento de Serviços." + RESETAR);
                teclado.nextLine();
                return;
            }
            Funcionario funcionario_servico = FuncionarioController.consultarFuncionario(servico_delete.getNome_funcionario().getMatricula());
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
            if(i==0&&LocalTime.now().isAfter(LocalTime.of(18,30))){
                continue;
            }
            String dataFormatada = formatter.format(datas.plusDays(i));
            System.out.print(AMARELO + "| " + RESETAR + VERDE + dataFormatada + RESETAR + AMARELO + "| " + RESETAR);
        }

    }

    private static String clonarAtributos(Servico servico){
        return AZUL + NEGRITO + "Data: " + RESETAR + MAGENTA +formatter.format(servico.getData_servico()) + "\n" + RESETAR +
                AZUL + NEGRITO + "Horário: " + RESETAR + MAGENTA +servico.getHora_servico() + "\n" + RESETAR +
                AZUL + NEGRITO+ "Funcionário:" + RESETAR + MAGENTA +servico.getNome_funcionario().getNome() + "\n" + RESETAR +
                AZUL + NEGRITO + "Tipo do serviço: " + RESETAR + MAGENTA +servico.getTipo_agendamento() + "\n" + RESETAR +
                AZUL + NEGRITO + "Preço: " + RESETAR + MAGENTA +"R$" + servico.getPreco() + "\n" + RESETAR +
                AZUL + NEGRITO + "Nome do pet: " + RESETAR + MAGENTA + servico.getPet_agendamento().getNomePet() + "\n" + RESETAR;
    }

    public static void ListarAgendaFuncionario(){
        System.out.println(NEGRITO + CYAN + "\n\t\t\t| Listar Agenda de Funcionário |\n" + RESETAR);
        while (true) {
            String matricula_func;
            try {
                System.out.print(NEGRITO + AMARELO + "Digite a matrícula do funcionário desejado: " + RESETAR);
                matricula_func = teclado.nextLine().strip();
                if (FuncionarioController.consultarFuncionario(matricula_func) == null) {
                    throw new ListaVaziaException(VERMELHO + "\n\t\t\tERRO: A matrícula digitada não está associada à nenhum funcionário cadastrado.\n\t\t\tPRESSIONE ENTER para TENTAR NOVAMENTE ou 1 PARA CANCELAR E VOLTAR AO MENU PRINCIPAL.\n" + RESETAR);
                }
                Funcionario funcionario_agenda = FuncionarioController.consultarFuncionario(matricula_func);
                if (funcionario_agenda != null) {
                    funcionario_agenda.listarAgenda();
                    break;
                }
            } catch (ListaVaziaException e) {
                System.out.print(e.getMessage());
                System.out.print(VERMELHO + "OPÇÃO: " + RESETAR);
                String option = teclado.nextLine();
                if (option.equals("1")) {
                    System.out.println("Retornando ao menu principal...");
                    return;
                }
            }
        }
    }

    }

