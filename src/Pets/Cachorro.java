package Pets;

import Tutores.Tutor;

public class Cachorro extends Pet {
    private String racaCachorro;

    public Cachorro(String nomePet, String matriculaPet, Integer idadePet, Tutor donoPet, String racaCachorro) {
        super(nomePet, matriculaPet, idadePet, donoPet);
        this.racaCachorro = racaCachorro;
    }

    public String getRacaCachorro() {
        return racaCachorro;
    }

    public void setRacaCachorro(String racaCachorro) {
        this.racaCachorro = racaCachorro;
    }
    
}
