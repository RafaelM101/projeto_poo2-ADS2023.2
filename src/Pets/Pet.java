package Pets;

import Tutores.Tutor;

import components.Matricula;
import components.TipoEntidade;


public abstract class Pet {
    protected String nomePet;
    protected Matricula matriculaPet;
    protected Integer idadePet;
    protected Tutor donoPet;

    //Construtor
    public Pet(String nomePet, Integer idadePet, Tutor donoPet) {
        this.nomePet = nomePet;
        this.matriculaPet = Matricula.gerarMatricula(TipoEntidade.PET);
        this.idadePet = idadePet;
        this.donoPet = donoPet;
    }

    //GETTERS AND SETTERS
    public String getNomePet() {
        return nomePet;
    }
    public String getMatriculaPet() {
        return matriculaPet.numero_matricula;
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
    
}
