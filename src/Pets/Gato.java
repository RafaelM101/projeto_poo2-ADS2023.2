package Pets;

public class Gato extends Pet{
    private String racaGato;
    
    public Gato(String nomePet, String matriculaPet, Integer idadePet, String racaGato) {
        super(nomePet, matriculaPet, idadePet);
        this.racaGato = racaGato;
    }

    public String getRacaGato() {
        return racaGato;
    }

    public void setRacaGato(String racaGato) {
        this.racaGato = racaGato;
    }
    
}
