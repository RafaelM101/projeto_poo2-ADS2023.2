package Pets;

import java.util.ArrayList;
import java.util.Scanner;

import Tutores.Endereco;
import Tutores.Tutor;
import components.CRUD;

public abstract class Pet implements CRUD {
    protected String nomePet;
    protected String matriculaPet;
    protected Integer idadePet;
    protected Tutor donoPet;
    protected static ArrayList<Pet> lista_pets = new ArrayList<>();

    public Pet(String nomePet, String matriculaPet, Integer idadePet, Tutor donoPet) {
        this.nomePet = nomePet;
        this.matriculaPet = matriculaPet;
        this.idadePet = idadePet;
        this.donoPet = donoPet;
    }
    
    public String getNomePet() {
        return nomePet;
    }
    
    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }
    
    public String getMatriculaPet() {
        return matriculaPet;
    }
    
    public void setMatriculaPet(String matriculaPet) {
        this.matriculaPet = matriculaPet;
    }
    
    public Integer getIdadePet() {
        return idadePet;
    }
    
    public void setIdadePet(Integer idadePet) {
        this.idadePet = idadePet;
    }
    
    public Tutor getDonoPet() {
        return donoPet;
    }

    public void setDonoPet(Tutor donoPet) {
        this.donoPet = donoPet;
    }

    public static void listar() {

    }

    public static void atualizar() {

    }

    public static void deletar() {

    }

    public static Pet consultarPet(String matricula) {
        for (Pet pet : lista_pets) {
            if (pet.getMatriculaPet().equals(matricula)) {
                return pet;}
        }
        return null;
    }

    public static void cadastrar(){
        System.out.print("Digite o nome do Pet: ");
        String nome_pet = teclado.nextLine();
        System.out.print("Digite a matrícula do Pet: ");
        String matricula_pet = teclado.nextLine();
        System.out.print("Digite a idade do Pet: ");
        Integer idade_pet = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Digite o CPF do tutor:  ");
        String cpf_tutor = teclado.nextLine();
        if(Tutor.consultarTutor(cpf_tutor)==null){
            System.out.println("Tutor não encontrado, deseja cadastrar? Digite Y para SIM e N para NÃO");
            String escolha = teclado.next();
            escolha = String.valueOf(escolha.charAt(0));
            if(escolha.equals("Y")) Tutor.cadastrar();
            else return;
        }
        Tutor donoPet = Tutor.consultarTutor(cpf_tutor);
        System.out.println("Digite a espécie do Pet: GATO OU CACHORRO: ");
        String tipo_pet = teclado.next().toUpperCase();
        teclado.nextLine();
        if(tipo_pet.equals("GATO")){
            System.out.println("Digite a raça do seu gato: ");
            String racaGato = teclado.next();
            teclado.nextLine();
            Gato novo_gato = new Gato(nome_pet,matricula_pet,idade_pet,donoPet,racaGato);
            donoPet.adicionarPet(novo_gato);
            lista_pets.add(novo_gato);
        } else if(tipo_pet.equals("CACHORRO")){
            System.out.println("Digite a raça do seu cachorro: ");
            String racaCachorro = teclado.next();
            teclado.nextLine();
            Gato novo_cachorro = new Gato(nome_pet,matricula_pet,idade_pet,donoPet,racaCachorro);
            donoPet.adicionarPet(novo_cachorro);
            lista_pets.add(novo_cachorro);
            }


    }
}
