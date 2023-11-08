package Pets;

import Tutores.Tutor;

public class Gato extends Pet{
    private String racaGato;
    
    public Gato(String nomePet, String matriculaPet, Integer idadePet, Tutor donoPet, String racaGato) {
        super(nomePet, matriculaPet, idadePet, donoPet);
        this.racaGato = racaGato;
    }

    public String getRacaGato() {
        return racaGato;
    }

    public void setRacaGato(String racaGato) {
        this.racaGato = racaGato;
    }
    
}
