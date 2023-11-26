package Tutores;

import java.util.ArrayList;
import Pets.Pet;
import components.CRUD;
import components.Terminal;
import exceptions.CpfInvalidoException;
import exceptions.EmailInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.SomenteLetrasException;
import exceptions.TelefoneInvalidoException;
import components.Validar;
public class Tutor implements CRUD, Terminal {
    private String nomeTutor;
    private String cpf_Tutor;
    private String telefoneTutor;
    private String emailTutor;
    private ArrayList<Pet> pets = new ArrayList<>();
    private Endereco enderecoTutor;
    protected static ArrayList<Tutor> lista_tutores = new ArrayList<>();
    
    public Tutor(String nomeTutor, String cPF_Tutor, String telefoneTutor, String emailTutor, Endereco enderecoTutor) {
        this.nomeTutor = nomeTutor;
        cpf_Tutor = cPF_Tutor;
        this.telefoneTutor = telefoneTutor;
        this.emailTutor = emailTutor;
        this.enderecoTutor = enderecoTutor;
    }
    //GETTERS AND SETTERS
    public String getNomeTutor() {
        return nomeTutor;
    }
    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }
    public String getCPF_Tutor() {
        return cpf_Tutor;
    }
    public String getTelefoneTutor() {
        return telefoneTutor;
    }
    public void setTelefoneTutor(String telefoneTutor) {
        this.telefoneTutor = telefoneTutor;
    }
    public String getEmailTutor() {
        return emailTutor;
    }
    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }
    public Endereco getEnderecoTutor() {
        return enderecoTutor;
    }
    public void setEnderecoTutor(Endereco enderecoTutor) {
        this.enderecoTutor = enderecoTutor;
    }
    public ArrayList<Pet> getPets() {
        return pets;
    }

    public static void data_seed_tutor() throws ListaVaziaException {
        Endereco endereco_tutor = new Endereco("Rua Fim do mundo", "Garanhuns", 666);
        Tutor tutor_1 = new Tutor("Rayane","111.111.111-11","81-99999-8888","tutor@gmail.com", endereco_tutor);
        Tutor.lista_tutores.add(tutor_1);
        Tutor tutor_2 = new Tutor("Daniel","222.222.222-22","81-99999-8888","tutor@gmail.com", endereco_tutor);
        Tutor.lista_tutores.add(tutor_2);
        Tutor.listar();
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
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: 000.000.000-00" +RESETAR);
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
        System.out.print(AMARELO+ "Digite o número: " +RESETAR);
        int numero = teclado.nextInt();
        teclado.nextLine();
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
                for (Pet pet : tutor.pets){
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
            System.out.println(PRETO+"Vamos atualizar o cadastro do Tutor " +RESETAR);
            while (true) {
                System.out.print(CYAN+ "Escolha opção do que deseja atualizar:" +RESETAR+AMARELO+ "\n"
                    +"1 - Nome\n"
                    +"2 - Telefone\n"
                    +"3 - Email\n"
                    +"4 - Endereço\n"
                    +"5 - SAIR\n: " +RESETAR);
                Integer escolhaAtualizar = teclado.nextInt();
                teclado.nextLine();
                switch (escolhaAtualizar) {
                    case 1: {
                        //Novo Nome do Tutor
                        System.out.print(AMARELO+ "Digite o novo Nome: " +RESETAR);
                        String novoNome = teclado.nextLine();
                        tutor.setNomeTutor(novoNome);
                        System.out.println(NEGRITO+VERDE+ "O nome foi atualizado." +RESETAR);
                        break;
                    }
                    case 2: {
                        //Novo Telefone
                        System.out.print(AMARELO+ "Digite o novo Telefone: " +RESETAR);
                        String novoTelefone = teclado.nextLine();
                        tutor.setTelefoneTutor(novoTelefone);
                        System.out.println(NEGRITO+VERDE+ "O telefone foi atualizado." +RESETAR);
                        break;
                    }
                    case 3: {
                        //Novo Email
                        String novoEmail;
                        while (true) {
                            try{
                                System.out.print(AMARELO+ "Digite o novo Email: " +RESETAR);
                                String novoEmailValidando= teclado.nextLine();
                                if (Validar.validarEmail(novoEmailValidando)) {
                                    novoEmail = novoEmailValidando;
                                    tutor.setEmailTutor(novoEmail);
                                    System.out.println(NEGRITO+VERDE+ "O email foi atualizado." +RESETAR);
                                    break;
                                }
                            }
                            catch(EmailInvalidoException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }
                    case 4: {
                        //Novo Endereço
                        System.out.println(PRETO+ "Vamos atualizar o endereço do Tutor" +RESETAR);
                        Endereco endereco = tutor.getEnderecoTutor();
                        System.out.print(AMARELO+ "Digite a nova Rua: " +RESETAR);
                        String novaRua = teclado.nextLine();
                        endereco.setRua(novaRua);
                        System.out.print(AMARELO+ "Digite o novo Bairro: " +RESETAR);
                        String novoBairro = teclado.nextLine();
                        endereco.setBairro(novoBairro);
                        System.out.print(AMARELO+ "Digite o novo Número: " +RESETAR);
                        int novoNumero = teclado.nextInt();
                        teclado.nextLine();
                        endereco.setNumero(novoNumero);
                        System.out.println(NEGRITO+VERDE+ "O endereço foi atualizado." +RESETAR);
                        break;
                    }
                    default:{
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
        Tutor tutorConsultado = Tutor.consultarTutor(cpfConsulta);
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
    //Adicionar Pet a lista de Pets
    public void adicionarPet(Pet pet){
        this.pets.add(pet);
    }
    //Deletar Pet da lista de Pets
    public void deletarPet(Pet pet) {
        this.pets.remove(pet);
    }
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
        System.out.print(AMARELO+ "Digite o número: " +RESETAR);
        int numero = teclado.nextInt();
        teclado.nextLine();
        Endereco endereco = new Endereco(rua,bairro,numero);
        Tutor tutor = new Tutor(nome, cpf, telefone, email, endereco);
        lista_tutores.add(tutor);
        System.out.println(NEGRITO+VERDE+ "\nTUTOR CADASTRADO COM SUCESSO!" +RESETAR);
    }
}
