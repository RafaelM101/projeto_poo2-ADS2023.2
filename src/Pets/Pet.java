package Pets;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pet{
    protected String nomePet;
    protected String matriculaPet;
    protected LocalDate dataNascimentoPet;
    protected Integer idadePet;

    public Pet(String nomePet, String matriculaPet, LocalDate dataNascimentoPet) {
        this.nomePet = nomePet;
        this.matriculaPet = matriculaPet;
        this.dataNascimentoPet = dataNascimentoPet;
        this.idadePet = this.getIdade();
    }
    
    public int getIdade() {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimentoPet, dataAtual);
        return periodo.getYears();
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
    
    public LocalDate getDataNascimentoPet() {
        return dataNascimentoPet;
    }
    
    public void setDataNascimentoPet(LocalDate dataNascimentoPet) {
        this.dataNascimentoPet = dataNascimentoPet;
    }
    
    public Integer getIdadePet() {
        return idadePet;
    }
    
    public void setIdadePet() {
        this.idadePet = this.getIdade();
    }

}
