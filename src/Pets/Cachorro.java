package Pets;

public class Cachorro extends Pet {
    private String racaCachorro;

    public Cachorro(String nomePet, String matriculaPet, Integer idadePet, String racaCachorro) {
        super(nomePet, matriculaPet, idadePet);
        this.racaCachorro = racaCachorro;
    }

    public String getRacaCachorro() {
        return racaCachorro;
    }

    public void setRacaCachorro(String racaCachorro) {
        this.racaCachorro = racaCachorro;
    }
    
}
