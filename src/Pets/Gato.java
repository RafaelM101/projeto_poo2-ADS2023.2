package Pets;

import java.time.LocalDate;

public class Gato extends Pet{
    private String racaGato;
    
    public Gato(String nomePet, String matriculaPet, LocalDate dataNascimentoPet, String racaGato) {
        super(nomePet, matriculaPet, dataNascimentoPet);
        this.racaGato = racaGato;
    }

    public String getRacaGato() {
        return racaGato;
    }

    public void setRacaGato(String racaGato) {
        this.racaGato = racaGato;
    }
    
}
