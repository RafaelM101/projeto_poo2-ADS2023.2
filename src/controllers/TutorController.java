package controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Pets.Pet;
import Tutores.Endereco;
import Tutores.Tutor;
import components.CRUD;
import components.Terminal;
import components.Validar;
import exceptions.CpfInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.SomenteLetrasException;
import exceptions.TelefoneInvalidoException;

public class TutorController implements CRUD, Terminal {
    
    protected static ArrayList<Tutor> lista_tutores = new ArrayList<>();

    public static void data_seed_tutor() throws ListaVaziaException {
        Endereco endereco_tutor = new Endereco("Rua Fim do mundo", "Garanhuns", 666);
        Tutor tutor_1 = new Tutor("Rayane","111.111.111-11","81-99999-8888","tutor@gmail.com", endereco_tutor);
        TutorController.lista_tutores.add(tutor_1);
        Tutor tutor_2 = new Tutor("Daniel","222.222.222-22","81-99999-8888","tutor@gmail.com", endereco_tutor);
        TutorController.lista_tutores.add(tutor_2);
        TutorController.listar();
    }
    
    //Cadastrar Tutor
    public static void cadastrar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | CADASTRO DE TUTOR | \n" +RESETAR);
        //Cadastrar NOME
        System.out.println(NEGRITO+AMARELO+ "NOME: Somente letras [A-Z]" +RESETAR);
        String nome;
        while(true) {
            try {
                System.out.print(AMARELO+ "Digite o nome do Tutor: " +RESETAR);
                String nomeValidando = teclado.nextLine();
                if (Validar.validarLetras(nomeValidando)) {
                    nome = nomeValidando;
                    break;
                }
            }
            catch (SomenteLetrasException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar CPF
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
        String cpf;
        while(true){
            try{
                System.out.print(AMARELO+ "Digite o CPF do Tutor: " +RESETAR);
                String cpfValidando = teclado.nextLine();
                if(Validar.validarCPF(cpfValidando)) {
                    cpf = cpfValidando;
                    break; 
                }
            }
            catch(CpfInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar TELEFONE
        System.out.println(NEGRITO+AMARELO+ "\nFormato do Telefone: (XXX)9XXXX-XXXX" +RESETAR);
        String telefone;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o telefone do Tutor: " +RESETAR);
                String telefoneValidar = teclado.nextLine();
                if (Validar.validarTelefone(telefoneValidar)) {
                    telefone = telefoneValidar;
                    break;
                }
            }
            catch (TelefoneInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar EMAIL
        System.out.println(NEGRITO+AMARELO+ "\nFormato do Email: aaaa@aaaaa.com" +RESETAR);
        String email;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o email do Tutor: " +RESETAR);
                String emailValidando = teclado.nextLine();
                if (Validar.validarEmail(emailValidando)) {
                    email = emailValidando;
                    break;
                }
            }
            catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar ENDEREÇO
        System.out.println(NEGRITO+AMARELO+ "\nENDEREÇO:" +RESETAR);
        System.out.print(AMARELO+ "Digite a rua: " +RESETAR);
        String rua = teclado.nextLine();
        System.out.print(AMARELO+ "Digite o bairro: " +RESETAR);
        String bairro = teclado.nextLine();
        int numero;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o número: " +RESETAR);
                int numeroValidar = teclado.nextInt();
                teclado.nextLine();
                numero = numeroValidar;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Digite apenas o número da residência" +RESETAR);
                teclado.nextLine();
            }
        }
        Endereco endereco = new Endereco(rua,bairro,numero);
        Tutor tutor = new Tutor(nome, cpf, telefone, email, endereco);
        lista_tutores.add(tutor);
        System.out.println(NEGRITO+VERDE+ "\nTUTOR CADASTRADO COM SUCESSO!" +RESETAR);
    }
    
    //Listar Tutores com Nome, CPF e seus Pets
    public static void listar() throws ListaVaziaException {
        if (lista_tutores.size() < 1) {
            throw new ListaVaziaException(NEGRITO+VERMELHO+ "Nenhum tutor cadastrado." +RESETAR);
        } else {
            System.out.println(NEGRITO+PRETO+ " \t\t\t | TUTORES CADASTRADOS | \n" +RESETAR);
            for (Tutor tutor : lista_tutores ) {
                System.out.println(MAGENTA+ "Nome: " +RESETAR + tutor.getNomeTutor() +
                MAGENTA+ "\nCPF: " +RESETAR + tutor.getCPF_Tutor());
                System.out.println(MAGENTA+"Pets: " +RESETAR);
                for (Pet pet : tutor.getPets()){
                    System.out.println(AZUL+" " + "["+pet.getMatriculaPet()+"]" +RESETAR + " " +pet.getNomePet());
                }
                System.out.println();
            }
        }
    }
    
    //Atualizar cadastro de um Tutor existente
    public static void atualizar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | ATUALIZAR CADASTRO DE TUTOR | \n" +RESETAR);
        String cpfBuscar;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
        while(true){
            try{
                System.out.print(AMARELO+ "Digite o CPF do Tutor que deseja atualizar: " +RESETAR);
                String cpfValidando = teclado.nextLine();
                if(Validar.validarCPF(cpfValidando)) {
                    cpfBuscar = cpfValidando;
                    break; 
                }
            }
            catch(CpfInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
        Tutor tutor = consultarTutor(cpfBuscar);
        if (tutor instanceof Tutor) {
            System.out.println(PRETO+"\n\t\t\t Vamos atualizar o cadastro do Tutor " +RESETAR);
            while (true) {
                Integer escolhaAtualizar;
                while (true) {
                    try{
                        System.out.print(CYAN+ "\nEscolha opção do que deseja atualizar:" +RESETAR+AMARELO+ "\n"
                            +"1 - Telefone\n"
                            +"2 - Email\n"
                            +"3 - Endereço\n"
                            +"4 - CONCLUIR\n: " +RESETAR);
                        Integer escolha = teclado.nextInt();
                        teclado.nextLine();
                        escolhaAtualizar = escolha;
                        break;
                    }
                    catch (InputMismatchException e) {
                        System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                        teclado.nextLine();
                    }
                }
                switch (escolhaAtualizar) {
                    case 1: {
                        //Novo Telefone
                        System.out.println(NEGRITO+AMARELO+ "\nFormato do Telefone: (XXX)9XXXX-XXXX" +RESETAR);
                        String novoTelefone;
                        while (true) {
                            try {
                                System.out.print(AMARELO+ "Digite o novo telefone do Tutor: " +RESETAR);
                                String telefoneValidar = teclado.nextLine();
                                if(Validar.validarTelefone(telefoneValidar)) {
                                    novoTelefone = telefoneValidar;
                                    tutor.setTelefoneTutor(novoTelefone);
                                    System.out.println(NEGRITO+VERDE+ "\nO TELEFONE FOI ATUALIZADO." +RESETAR);
                                    break;
                                }
                            }
                            catch (TelefoneInvalidoException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }
                    case 2: {
                        //Novo Email
                        System.out.println(NEGRITO+AMARELO+ "\nFormato do Email: aaaa@aaaaa.com" +RESETAR);
                        String novoEmail;
                        while (true) {
                            try{
                                System.out.print(AMARELO+ "Digite o novo Email: " +RESETAR);
                                String novoEmailValidando= teclado.nextLine();
                                if (Validar.validarEmail(novoEmailValidando)) {
                                    novoEmail = novoEmailValidando;
                                    tutor.setEmailTutor(novoEmail);
                                    System.out.println(NEGRITO+VERDE+ "\nO EMAIL FOI ATUALIZADO." +RESETAR);
                                    break;
                                }
                            }
                            catch(EmailInvalidoException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }
                    case 3: {
                        //Novo Endereço
                        System.out.println(PRETO+ "\n\t\t Vamos atualizar o endereço do Tutor" +RESETAR);
                        Endereco endereco = tutor.getEnderecoTutor();
                        System.out.print(AMARELO+ "Digite a nova Rua: " +RESETAR);
                        String novaRua = teclado.nextLine();
                        endereco.setRua(novaRua);
                        System.out.print(AMARELO+ "Digite o novo Bairro: " +RESETAR);
                        String novoBairro = teclado.nextLine();
                        endereco.setBairro(novoBairro);
                        while (true) {
                            try {
                                System.out.print(AMARELO+ "Digite o novo Número: " +RESETAR);
                                int novoNumero = teclado.nextInt();
                                teclado.nextLine();
                                endereco.setNumero(novoNumero);
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println(NEGRITO+VERMELHO+ "Digite apenas o número da residência" +RESETAR);
                                teclado.nextLine();
                            }
                        }
                        System.out.println(NEGRITO+VERDE+ "\nO ENDEREÇO FOI ATUALIZADO." +RESETAR);
                        break;
                    }
                    default:{
                        System.out.println(NEGRITO+VERDE+ "\nCADASTRO ATUALIZADO!" +RESETAR);
                        return;
                    }
                }
            }
        } else {
            System.out.println(NEGRITO+VERMELHO+ "CPF não consta no sistema." +RESETAR);
        }
    }
    
    //Deletar tutor existente
    public static void deletar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | DELETAR TUTOR | \n" +RESETAR);
        String deletarCPF;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
        while(true){
            try{
                System.out.print(AMARELO+ "Digite o CPF do Tutor que deseja deletar: " +RESETAR);
                String cpfValidando = teclado.nextLine();
                if(Validar.validarCPF(cpfValidando)) {
                    deletarCPF = cpfValidando;
                    break; 
                }
            }
            catch(CpfInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
        Tutor deletarTutor = consultarTutor(deletarCPF);
        if(deletarTutor instanceof Tutor) {
            lista_tutores.remove(deletarTutor);
            System.out.println(NEGRITO+VERDE+ "Tutor deletado com sucesso!" +RESETAR);
        } else {
            System.out.println(NEGRITO+VERMELHO+ "CPF não encontrado!" +RESETAR);
        }

    }
    
    //Método de Consulta do Main
    public static void consulta() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | CONSULTAR TUTOR | \n" +RESETAR);
        String cpfConsulta;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
        while(true){
            try{
                System.out.print(AMARELO+ "Digite o CPF do Tutor que deseja consultar: " +RESETAR);
                String cpfValidando = teclado.nextLine();
                if(Validar.validarCPF(cpfValidando)) {
                    cpfConsulta = cpfValidando;
                    break; 
                }
            }
            catch(CpfInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
        Tutor tutorConsultado = TutorController.consultarTutor(cpfConsulta);
        if (tutorConsultado instanceof Tutor) {
            System.out.println(CYAN+ "\nNome: " +RESETAR + tutorConsultado.getNomeTutor());
            Endereco enderecoTutor = tutorConsultado.getEnderecoTutor();
            System.out.println(CYAN+ "Endereço: \n" +RESETAR+CYAN
                +PRETO+ "- Rua: " +RESETAR + enderecoTutor.getRua() +"\n"
                +PRETO+ "- Bairro: " +RESETAR + enderecoTutor.getBairro() +"\n"
                +PRETO+ "- Número: " +RESETAR + enderecoTutor.getNumero());
            System.out.println(CYAN+ "Telefone: " +RESETAR + tutorConsultado.getTelefoneTutor());
            System.out.println(CYAN+ "Email: " +RESETAR + tutorConsultado.getEmailTutor());
        } else {
            System.out.println(NEGRITO+VERMELHO+ "CPF não consta no sistema." +RESETAR);
        }
    }
    
    //Retorna um Tutor
    public static Tutor consultarTutor(String cpf) {
		for (Tutor tutor : lista_tutores) {
			if (tutor.getCPF_Tutor().equals(cpf)) {
				return tutor;
            }
		}
		return null;
	}
    
    //Cadastro de Tutor pela classe Pet
    public static void cadastrarPorPet(String cpf) {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | CADASTRO DE TUTOR | \n" +RESETAR);
        //Cadastrar NOME
        System.out.println(NEGRITO+AMARELO+ "Formato do Nome: Somente letras [A-Z]" +RESETAR);
        String nome;
        while(true) {
            try{
                System.out.print(AMARELO+ "Digite o nome do Tutor: " +RESETAR);
                String nomeValidando = teclado.nextLine();
                if(Validar.validarLetras(nomeValidando)) {
                    nome = nomeValidando;
                    break;
                }
            }
            catch (SomenteLetrasException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar TELEFONE
        System.out.println(NEGRITO+AMARELO+ "\nFormato do Telefone: (XXX)9XXXX-XXXX" +RESETAR);
        String telefone;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o telefone do Tutor: " +RESETAR);
                String telefoneValidar = teclado.nextLine();
                if (Validar.validarTelefone(telefoneValidar)) {
                    telefone = telefoneValidar;
                    break;
                }
            }
            catch (TelefoneInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar EMAIL
        System.out.println(NEGRITO+AMARELO+ "\nFormato do Email: aaaa@aaaaa.com" +RESETAR);
        String email;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o email do Tutor: " +RESETAR);
                String emailValidando = teclado.nextLine();
                if (Validar.validarEmail(emailValidando)) {
                    email = emailValidando;
                    break;
                }
            }
            catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        //Cadastrar ENDEREÇO
        System.out.println(NEGRITO+AMARELO+ "\nENDEREÇO:" +RESETAR);
        System.out.print(AMARELO+ "Digite a rua: " +RESETAR);
        String rua = teclado.nextLine();
        System.out.print(AMARELO+ "Digite o bairro: " +RESETAR);
        String bairro = teclado.nextLine();
        int numero;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite o número: " +RESETAR);
                int numeroValidar = teclado.nextInt();
                teclado.nextLine();
                numero = numeroValidar;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Digite apenas o número da residência" +RESETAR);
                teclado.nextLine();
            }
        }
        Endereco endereco = new Endereco(rua,bairro,numero);
        Tutor tutor = new Tutor(nome, cpf, telefone, email, endereco);
        lista_tutores.add(tutor);
        System.out.println(NEGRITO+VERDE+ "\nTUTOR CADASTRADO COM SUCESSO!" +RESETAR);
    }

}
