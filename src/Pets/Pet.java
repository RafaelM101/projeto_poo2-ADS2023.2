package Pets;

import java.util.ArrayList;
import Tutores.Tutor;
import components.CRUD;
import components.Matricula;
import components.RacasCachorro;
import components.RacasGato;
import components.TipoEntidade;
import exceptions.ListaVaziaException;

public abstract class Pet implements CRUD {
    protected String nomePet;
    protected Matricula matriculaPet;
    protected Integer idadePet;
    protected Tutor donoPet;
    protected static ArrayList<Pet> lista_pets = new ArrayList<>();

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
    //Consultar se existe um Pet com essa Matricula


    public static void data_seed_pet() throws ListaVaziaException {
        Cachorro cachorro_1 = new Cachorro("Maylon", 2, Tutor.consultarTutor("111.111.111-11"), RacasCachorro.SRD);
        Pet.lista_pets.add(cachorro_1);
        Cachorro cachorro_2 = new Cachorro("Nick", 7, Tutor.consultarTutor("222.222.222-22"), RacasCachorro.POODLE);
        Pet.lista_pets.add(cachorro_2);

        Gato gato_1 = new Gato("Leon", 5, Tutor.consultarTutor("111.111.111-11"), RacasGato.SRD);
        Pet.lista_pets.add(gato_1);
        Gato gato_2 = new Gato("Luca", 3, Tutor.consultarTutor("222.222.222-22"), RacasGato.SRD);
        Pet.lista_pets.add(gato_2);

        Pet.listar();

    }
    public static Pet consultarPet(String matricula) {
        for (Pet pet : lista_pets) {
            if (pet.getMatriculaPet().equals(matricula)) {
                return pet;}
        }
        return null;
    }
    //Lista os Pets pelo nome
    public static void listar() throws ListaVaziaException{
        if (lista_pets.size() < 1) {
            throw new ListaVaziaException("Nenhum pet cadastrado");
        }
        else {
            for(Pet pet : lista_pets) {
                System.out.println("\nNome do Pet: " + pet.getNomePet());
                System.out.println("Matrícula: " + pet.getMatriculaPet());
            }
        }
    }
    //Atualiza o cadastro(Idade) de um Pet
    public static void atualizar() {
        System.out.print("Digite a matrícula do Pet que deseja atualizar: ");
        String matriculaBuscar = teclado.nextLine();
        Pet pet = consultarPet(matriculaBuscar);
        if (pet instanceof Pet) {
            System.out.println("Matrícula encontrada!");
            System.out.println("Vamos atualizar o cadastro do Pet");
            //Idade do Pet
            System.out.print("Digite a nova Idade: ");
            int novaIdade = teclado.nextInt();
            teclado.nextLine();
            pet.setIdadePet(novaIdade);
            System.out.println("O cadastro do seu Pet foi atualizado com sucesso!");

        } else {
            System.out.println("Matrícula não encontrada!");
        }

    }
    //Deleta um Pet da lista_pets
    public static void deletar() {
        System.out.print("Digite a matrícula do Pet que deseja deletar: ");
        String deletarMatricula = teclado.nextLine();
        Pet delPet = consultarPet(deletarMatricula);
        if(delPet instanceof Pet) {
            Tutor dono = delPet.getDonoPet();
            dono.deletarPet(delPet);
            lista_pets.remove(delPet);
            System.out.println("Pet deletado com sucesso!");
        } else {
            System.out.println("Matrícula não encontrada!");
        }
    }
    //Cadastrar um Pet
    public static void cadastrar(){
        System.out.println("\n| Cadastro de Pet |\n");
        System.out.print("Digite o CPF do tutor dono do pet: ");
        String cpf_tutor = teclado.nextLine();
        //Verifica se o Tutor já e cadastrado, se não cadastra um Tutor
        if(Tutor.consultarTutor(cpf_tutor)==null){
            System.out.println("Tutor não encontrado, deseja cadastrar? Digite Y para SIM e N para NÃO: ");
            String escolha = teclado.nextLine();
            escolha = String.valueOf(escolha.charAt(0));
            if(escolha.equalsIgnoreCase("Y")) {
                Tutor.cadastrar();
            } else {
                System.out.println("Tutor não pode ser nulo.");
                return;
            }
        }
        Tutor donoPet = Tutor.consultarTutor(cpf_tutor);
        //Cadastro do Pet
        System.out.print("Digite o nome do Pet: ");
        String nome_pet = teclado.nextLine();
        System.out.print("Digite a idade do Pet: ");
        Integer idade_pet = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Escolha a espécie do Pet: \n1- GATO \n2- CACHORRO\n: ");
        Integer escolhaTipoPet = teclado.nextInt();
        teclado.nextLine();
        switch (escolhaTipoPet) {
            //Se a espécie for um Gato
            case 1:{
                System.out.print("Escolha a Raça do seu Gato:\n 1 - SRD,\n" + 
                    " 2 - PERSA,\n" + 
                    " 3 - SIAMES,\n" + 
                    " 4 - BENGAL\n: ");
                Integer escolhaRacaGato = teclado.nextInt();
                teclado.nextLine();
                RacasGato racasGato = null;
                switch (escolhaRacaGato) {
                    case 1: {
                        racasGato = RacasGato.SRD;
                        break;
                    }
                    case 2: {
                        racasGato = RacasGato.PERSA;
                        break;
                    }
                    case 3: {
                        racasGato = RacasGato.SIAMES;
                        break;
                    }
                    case 4: {
                        racasGato = RacasGato.BENGAL;
                        break;
                    }
                    default: {
                        System.out.println("Escolha Inválida.");
                        break;
                    }
                }
                Gato novo_gato = new Gato(nome_pet,idade_pet,donoPet,racasGato);
                donoPet.adicionarPet(novo_gato);
                lista_pets.add(novo_gato);
                System.out.printf("Gato cadastrado com sucesso:\nNome: %s\nMatrícula:%s ", novo_gato.getNomePet(),novo_gato.getMatriculaPet());
                break;
            }
            //Se a espécie for um Cachorro
            case 2: {
                System.out.print("Escolha a raça do seu cachorro: \n 1 - SRD,\n" + 
                    " 2 - LABRADOR,\n" + 
                    " 3 - PINSCHER,\n" +
                    " 4 - POODLE\n: ");
                Integer escolhaRacaCachorro = teclado.nextInt();
                teclado.nextLine();
                RacasCachorro racasCachorro = null;
                switch (escolhaRacaCachorro) {
                    case 1: {
                        racasCachorro = RacasCachorro.SRD;
                        break;
                    }
                    case 2: {
                        racasCachorro = RacasCachorro.LABRADOR;
                        break;
                    }
                    case 3: {
                        racasCachorro = RacasCachorro.PINSCHER;
                        break;
                    }
                    case 4: {
                        racasCachorro = RacasCachorro.POODLE;
                        break;
                    }
                    default: {
                        System.out.println("Escolha Inválida.");
                        break;
                    }
                }
                Cachorro novo_cachorro = new Cachorro(nome_pet,idade_pet,donoPet,racasCachorro);
                donoPet.adicionarPet(novo_cachorro);
                lista_pets.add(novo_cachorro);
                System.out.printf("Cahorro cadastrado com sucesso:\nNome: %s\nMatrícula:%s ", novo_cachorro.getNomePet(),novo_cachorro.getMatriculaPet());
                break;
            }  
            //Se a espécie for Inválida
            default: {
                System.out.println("Espécie do Pet Inválida.");
                break;
            }
        }
    }
    
    @Override
    public String toString() {
        return
                "Pet: " + nomePet + "\n"+
                "Matrícula: " + matriculaPet;
    }

}
