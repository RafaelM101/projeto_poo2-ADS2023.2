package Tutores;

import java.util.ArrayList;
import Pets.Pet;
import components.CRUD;

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

    public String getNomeTutor() {
        return nomeTutor;
    }
    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }
    public String getCPF_Tutor() {
        return cpf_Tutor;
    }
    public void setCPF_Tutor(String cPF_Tutor) {
        cpf_Tutor = cPF_Tutor;
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

    public static void cadastrar() {
        System.out.print("Digite o nome do Tutor: ");
        String nome = teclado.nextLine();
        System.out.print("Digite o CPF do Tutor: ");
        String cpf = teclado.nextLine();
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
    }
    
    public static void listar() {
        for (Tutor tutor : lista_tutores ) {
            System.out.println("Nome: " + tutor.getNomeTutor());
        }
    }
    
    public static void atualizar() {
        System.out.print("Digite o CPF do tutor que deseja atualizar: ");
        String cpfBuscar = teclado.nextLine();
        Tutor tutor = consultarTutor(cpfBuscar);
        if (tutor instanceof Tutor) {
            System.out.println("CPF encontrado!");
            System.out.print("Digite a opção do que deseja atualizar: " +
            "(Nome, CPF, Telefone, Email, Endereco) ");
            String escolhaAtt = teclado.nextLine();
            // Vai alterar o atributo especifico que o usuario escolher
            if(escolhaAtt.equalsIgnoreCase("Nome")) {
                System.out.print("Digite o novo Nome: ");
                String novoNome = teclado.nextLine();
                tutor.setNomeTutor(novoNome);
            } else if(escolhaAtt.equalsIgnoreCase("CPF")) {
                System.out.print("Digite o novo CPF: ");
                String novoCPF = teclado.nextLine();
                tutor.setCPF_Tutor(novoCPF);
            } else if(escolhaAtt.equalsIgnoreCase("Telefone")) {
                System.out.print("Digite o novo Telefone: ");
                String novoTelefone = teclado.nextLine();
                tutor.setTelefoneTutor(novoTelefone);
            } else if(escolhaAtt.equalsIgnoreCase("Email")) {
                System.out.print("Digite o novo Email: ");
                String novoEmail = teclado.nextLine();
                tutor.setEmailTutor(novoEmail);
            } else if(escolhaAtt.equalsIgnoreCase("Endereco")) {
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
            } else {
                System.out.println("Opção Inválida!");
            }   
        } else {
            System.out.println("CPF não encontrado!");
        }
    }
    
    public static void deletar() {
        System.out.print("Digite o CPF do tutor que deseja deletar: ");
        String deletarCPF = teclado.nextLine();
        Tutor deletarTutor = consultarTutor(deletarCPF);
        if(deletarTutor instanceof Tutor) {
            lista_tutores.remove(deletarTutor);
            System.out.println("Tutor deletado com sucesso!");
        } else {
            System.out.println("CPF não encontrado!");
        }

    }
    public void adicionarPet(Pet pet){
        pets.add(pet);
    }
    public static Tutor consultarTutor(String cpf) {
		for (Tutor tutor : lista_tutores) {
			if (tutor.getCPF_Tutor().equals(cpf)) {
				return tutor;}
		}
		return null;
	}
}
