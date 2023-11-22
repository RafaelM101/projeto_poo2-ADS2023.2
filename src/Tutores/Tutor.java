package Tutores;

import java.util.ArrayList;
import Pets.Pet;
import components.CRUD;
import exceptions.CpfInvalidoException;
import exceptions.ListaVaziaException;
import components.Validar;
public class Tutor implements CRUD {
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
        System.out.println("\n| CADASTRO DE TUTOR |\n");
        System.out.print("Digite o nome do Tutor: ");
        String nome = teclado.nextLine();
        String cpf;
        while(true){
            try{
                System.out.print("Digite o CPF do Tutor: ");
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
        System.out.print("Digite o telefone do Tutor: ");
        String telefone = teclado.nextLine();
        System.out.print("Digite o email do Tutor: ");
        String email = teclado.nextLine();
        System.out.println("Endereço:");
        System.out.print("Digite a rua: ");
        String rua = teclado.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = teclado.nextLine();
        System.out.print("Digite o número: ");
        int numero = teclado.nextInt();
        teclado.nextLine();
        Endereco endereco = new Endereco(rua,bairro,numero);
        Tutor tutor = new Tutor(nome, cpf, telefone, email, endereco);
        lista_tutores.add(tutor);
        System.out.println("Tutor cadastrado com sucesso!");
    }
    //Listar Tutores com Nome, CPF e seus Pets
    public static void listar() throws ListaVaziaException {
        if (lista_tutores.size() < 1) {
            throw new ListaVaziaException("Nenhum tutor cadastrado.");
        } else {
            System.out.println("\n | TUTORES CADASTRADOS | \n");
            for (Tutor tutor : lista_tutores ) {
                System.out.println("Nome: " + tutor.getNomeTutor() +
                "\nCPF: " + tutor.getCPF_Tutor() );
                System.out.println("Pets: ");
                for (Pet pet : tutor.pets){
                    System.out.println(" "+pet.getNomePet()+" "+pet.getMatriculaPet());
                }
                System.out.println();
            }
        }
    }
    //Atualizar cadastro de um Tutor existente
    public static void atualizar() {
        System.out.println("\n | ATUALIZAR CADASTRO DE TUTOR | \n");
        String cpfBuscar;
        while(true){
            try{
                System.out.print("Digite o CPF do Tutor que deseja atualizar: ");
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
            System.out.println("Vamos atualizar o cadastro do Tutor ");
            while (true) {
                System.out.print("Escolha opção do que deseja atualizar: \n"
                    +"1 - Nome\n"
                    +"2 - Telefone\n"
                    +"3 - Email\n"
                    +"4 - Endereço\n"
                    +"5 - SAIR\n: ");
                Integer escolhaAtualizar = teclado.nextInt();
                teclado.nextLine();
                switch (escolhaAtualizar) {
                    case 1: {
                        //Novo Nome do Tutor
                        System.out.print("Digite o novo Nome: ");
                        String novoNome = teclado.nextLine();
                        tutor.setNomeTutor(novoNome);
                        System.out.println("O nome foi atualizado.");
                        break;
                    }
                    case 2: {
                        //Novo Telefone
                        System.out.print("Digite o novo Telefone: ");
                        String novoTelefone = teclado.nextLine();
                        tutor.setTelefoneTutor(novoTelefone);
                        System.out.println("O telefone foi atualizado.");
                        break;
                    }
                    case 3: {
                        //Novo Email
                        System.out.print("Digite o novo Email: ");
                        String novoEmail = teclado.nextLine();
                        tutor.setEmailTutor(novoEmail);
                        System.out.println("O email foi atualizado.");
                        break;
                    }
                    case 4: {
                        //Novo Endereço
                        System.out.println("Vamos atualizar o endereço do Tutor");
                        Endereco endereco = tutor.getEnderecoTutor();
                        System.out.print("Digite a nova Rua: ");
                        String novaRua = teclado.nextLine();
                        endereco.setRua(novaRua);
                        System.out.print("Digite o novo Bairro: ");
                        String novoBairro = teclado.nextLine();
                        endereco.setBairro(novoBairro);
                        System.out.print("Digite o novo Número: ");
                        int novoNumero = teclado.nextInt();
                        teclado.nextLine();
                        endereco.setNumero(novoNumero);
                        System.out.println("O endereço foi atualizado.");
                        break;
                    }
                    default:{
                        return;
                    }
                }
            }
        } else {
            System.out.println("CPF não consta no sistema.");
        }
    }
    //Deletar tutor existente
    public static void deletar() {
        System.out.println("\n | DELETAR TUTOR | \n");
        String deletarCPF;
        while(true){
            try{
                System.out.print("Digite o CPF do Tutor que deseja deletar: ");
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
            System.out.println("Tutor deletado com sucesso!");
        } else {
            System.out.println("CPF não encontrado!");
        }

    }
    //Método de Consulta do Main
    public static void consulta() {
        System.out.println("\n | CONSULTAR TUTOR | \n");
        String cpfConsulta;
        while(true){
            try{
                System.out.print("Digite o CPF do Tutor que deseja consultar: ");
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
            System.out.println("Nome: " + tutorConsultado.getNomeTutor());
            Endereco enderecoTutor = tutorConsultado.getEnderecoTutor();
            System.out.println("Endereço: \n" 
                +"- Rua: " + enderecoTutor.getRua() +"\n"
                +"- Bairro: " + enderecoTutor.getBairro() +"\n"
                +"- Número: " + enderecoTutor.getNumero());
            System.out.println("Telefone: " + tutorConsultado.getTelefoneTutor());
            System.out.println("Email: " + tutorConsultado.getEmailTutor());
        } else {
            System.out.println("CPF não consta no sistema.");
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
        System.out.println("\n| CADASTRO DE TUTOR |\n");
        System.out.print("Digite o nome do Tutor: ");
        String nome = teclado.nextLine();
        System.out.print("Digite o telefone do Tutor: ");
        String telefone = teclado.nextLine();
        System.out.print("Digite o email do Tutor: ");
        String email = teclado.nextLine();
        System.out.println("Endereço:");
        System.out.print("Digite a rua: ");
        String rua = teclado.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = teclado.nextLine();
        System.out.print("Digite o número: ");
        int numero = teclado.nextInt();
        teclado.nextLine();
        Endereco endereco = new Endereco(rua,bairro,numero);
        Tutor tutor = new Tutor(nome, cpf, telefone, email, endereco);
        lista_tutores.add(tutor);
        System.out.println("Tutor cadastrado com sucesso!");
    }
}
