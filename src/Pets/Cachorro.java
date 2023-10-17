package Pets;

import java.time.LocalDate;

public class Cachorro extends Pet {
    private String racaCachorro;

    public Cachorro(String nomePet, String matriculaPet, LocalDate dataNascimentoPet, String racaCachorro) {
        super(nomePet, matriculaPet, dataNascimentoPet);
        this.racaCachorro = racaCachorro;
    }

    public String getRacaCachorro() {
        return racaCachorro;
    }

    public void setRacaCachorro(String racaCachorro) {
        this.racaCachorro = racaCachorro;
    }
    
}
