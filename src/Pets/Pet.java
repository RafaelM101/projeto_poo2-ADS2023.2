package Pets;

import java.util.ArrayList;
import components.CRUD;

public abstract class Pet implements CRUD {
    protected String nomePet;
    protected String matriculaPet;
    protected Integer idadePet;
    protected static ArrayList<Pet> lista_pets = new ArrayList<>();

    public Pet(String nomePet, String matriculaPet, Integer idadePet) {
        this.nomePet = nomePet;
        this.matriculaPet = matriculaPet;
        this.idadePet = idadePet;
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

    public static void cadastrar() {

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

}
