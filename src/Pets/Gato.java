package Pets;

import Tutores.Tutor;
import components.RacasGato;

public class Gato extends Pet{
    private RacasGato racaGato;
    
    public Gato(String nomePet, Integer idadePet, Tutor donoPet, RacasGato racaGato) {
        super(nomePet, idadePet, donoPet);
        this.racaGato = racaGato;
    }

    public RacasGato getRacaGato() {
        return racaGato;
    }

    public void setRacaGato(RacasGato racaGato) {
        this.racaGato = racaGato;
    }
    
}
