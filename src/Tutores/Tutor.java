package Tutores;

import java.util.ArrayList;

import Pets.Pet;
import components.CRUD;
import components.Terminal;

public class Tutor implements CRUD, Terminal {
    private String nomeTutor;
    private String cpf_Tutor;
    private String telefoneTutor;
    private String emailTutor;
    private Endereco enderecoTutor;
    private ArrayList<Pet> pets = new ArrayList<>();

   //Construtor 
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

    //ToString
    @Override
    public String toString() {
        return "Tutor [nomeTutor=" + nomeTutor + ", cpf_Tutor=" + cpf_Tutor + ", telefoneTutor=" + telefoneTutor
                + ", emailTutor=" + emailTutor + ", enderecoTutor=" + enderecoTutor + "]";
    }
    
    //Get pets
    public ArrayList<Pet> getPets() {
        return pets;
    }
    
}
