package Pets;

import Tutores.Tutor;
import components.RacasCachorro;

public class Cachorro extends Pet {
    private RacasCachorro racaCachorro;

    public Cachorro(String nomePet, Integer idadePet, Tutor donoPet, RacasCachorro racaCachorro) {
        super(nomePet, idadePet, donoPet);
        this.racaCachorro = racaCachorro;
    }

    public RacasCachorro getRacaCachorro() {
        return racaCachorro;
    }

    public void setRacaCachorro(RacasCachorro racaCachorro) {
        this.racaCachorro = racaCachorro;
    }
    
}
