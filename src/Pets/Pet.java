package Pets;

import java.util.ArrayList;
import Tutores.Tutor;
import components.CRUD;
import components.Matricula;
import components.RacasCachorro;
import components.RacasGato;
import components.Terminal;
import components.TipoEntidade;
import components.Validar;
import exceptions.CpfInvalidoException;
import exceptions.ListaVaziaException;

public abstract class Pet implements CRUD, Terminal {
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
    
    public static void data_seed_pet() throws ListaVaziaException {
        Cachorro cachorro_1 = new Cachorro("Maylon", 2, Tutor.consultarTutor("111.111.111-11"), RacasCachorro.SRD);
        Pet.lista_pets.add(cachorro_1);
        Tutor.consultarTutor("111.111.111-11").adicionarPet(cachorro_1);
        Cachorro cachorro_2 = new Cachorro("Nick", 7, Tutor.consultarTutor("222.222.222-22"), RacasCachorro.POODLE);
        Pet.lista_pets.add(cachorro_2);
        Tutor.consultarTutor("222.222.222-22").adicionarPet(cachorro_2);
        Gato gato_1 = new Gato("Leon", 5, Tutor.consultarTutor("111.111.111-11"), RacasGato.SRD);
        Pet.lista_pets.add(gato_1);
        Tutor.consultarTutor("111.111.111-11").adicionarPet(gato_1);
        Gato gato_2 = new Gato("Luca", 3, Tutor.consultarTutor("222.222.222-22"), RacasGato.SRD);
        Pet.lista_pets.add(gato_2);
        Tutor.consultarTutor("222.222.222-22").adicionarPet(gato_2);
        Pet.listar();

    }
    //Consultar se existe um Pet com essa Matricula
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
            throw new ListaVaziaException(NEGRITO+VERMELHO+ "Nenhum pet cadastrado" +RESETAR);
        }
        else {
            System.out.println(NEGRITO+PRETO+ " \t\t\t | PETS CADASTRADOS | " +RESETAR+ "\n");
            for(Pet pet : lista_pets) {
                System.out.println(AZUL+ "Nome do Pet: " +RESETAR + pet.getNomePet());
                System.out.println(AZUL+ "Matrícula: " +RESETAR + pet.getMatriculaPet() +"\n");
            }
        }
    }
    //Atualiza o cadastro(Idade) de um Pet
    public static void atualizar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | ATUALIZAR CADASTRO DE PET | \n" +RESETAR);
        System.out.print(AMARELO+ "Digite a matrícula do Pet que deseja atualizar: " +RESETAR);
        String matriculaBuscar = teclado.nextLine();
        Pet pet = consultarPet(matriculaBuscar);
        if (pet instanceof Pet) {
            System.out.println(NEGRITO+VERDE+ "Matrícula encontrada!" +RESETAR);
            System.out.println(NEGRITO+PRETO+ "\nVamos atualizar o cadastro do Pet" +RESETAR);
            //Idade do Pet
            System.out.print(AMARELO+ "Digite a nova Idade: " +RESETAR);
            int novaIdade = teclado.nextInt();
            teclado.nextLine();
            pet.setIdadePet(novaIdade);
            System.out.println(NEGRITO+VERDE+ "O cadastro do seu Pet foi atualizado com sucesso!" +RESETAR);

        } else {
            System.out.println(NEGRITO+VERMELHO+ "Matrícula não encontrada!" +RESETAR);
        }

    }
    //Deleta um Pet da lista_pets
    public static void deletar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t  | DELETAR PET | \n" +RESETAR);
        System.out.print(AMARELO+ "Digite a matrícula do Pet que deseja deletar: " +RESETAR);
        String deletarMatricula = teclado.nextLine();
        Pet delPet = consultarPet(deletarMatricula);
        if(delPet instanceof Pet) {
            Tutor dono = delPet.getDonoPet();
            if (dono != null) {
                dono.deletarPet(delPet);
                lista_pets.remove(delPet);
            } else {
                lista_pets.remove(delPet);
            }
            System.out.println(NEGRITO+VERDE+ "Pet deletado com sucesso!" +RESETAR);
        } else {
            System.out.println(NEGRITO+VERMELHO+ "Matrícula não encontrada!" +RESETAR);
        }
    }
    //Cadastrar um Pet
    public static void cadastrar(){
        System.out.println(NEGRITO+PRETO+ " \t\t\t  | CADASTRO DE PET | \n" +RESETAR);
        String cpf_tutor;
        while(true){
            try{
                System.out.print(AMARELO+ "Digite o CPF do Tutor dono do pet: " +RESETAR);
                String cpfValidando = teclado.nextLine();
                if(Validar.validarCPF(cpfValidando)) {
                    cpf_tutor = cpfValidando;
                    break; 
                }
            }
            catch(CpfInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
        //Verifica se o Tutor já é cadastrado, SE NÃO, cadastra um Tutor
        if(Tutor.consultarTutor(cpf_tutor)==null){
            System.out.print(NEGRITO+VERMELHO+ "\nTutor não encontrado," +RESETAR+CYAN+ " deseja cadastrar?\n" +AMARELO
                +"1 - para SIM\n"
                +"2 - para NÃO\n: " +RESETAR);
            int escolha = teclado.nextInt();
            teclado.nextLine();
            if(escolha == 1) {
                Tutor.cadastrarPorPet(cpf_tutor);
            } else {
                System.out.println(NEGRITO+VERMELHO+ "Tutor não pode ser nulo." +RESETAR);
                return;
            }
        }
        Tutor donoPet = Tutor.consultarTutor(cpf_tutor);
        //Cadastro do Pet
        System.out.print(AMARELO+ "\nDigite o nome do Pet: " +RESETAR);
        String nome_pet = teclado.nextLine();
        System.out.print(AMARELO+ "Digite a idade do Pet: " +RESETAR);
        Integer idade_pet = teclado.nextInt();
        teclado.nextLine();
        System.out.print(CYAN+ "Escolha a espécie do Pet:" +RESETAR+AMARELO+ " \n1- GATO \n2- CACHORRO\n: " +RESETAR);
        Integer escolhaTipoPet = teclado.nextInt();
        teclado.nextLine();
        switch (escolhaTipoPet) {
            //Se a espécie for um Gato
            case 1:{
                System.out.print(CYAN+ "Escolha a Raça do seu Gato:" +RESETAR+AMARELO+ "\n 1 - SRD,\n" + 
                    " 2 - PERSA,\n" + 
                    " 3 - SIAMES,\n" + 
                    " 4 - BENGAL\n: " +RESETAR);
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
                        System.out.println(NEGRITO+VERMELHO+ "Escolha Inválida." +RESETAR);
                        break;
                    }
                }
                Gato novo_gato = new Gato(nome_pet,idade_pet,donoPet,racasGato);
                donoPet.adicionarPet(novo_gato);
                lista_pets.add(novo_gato);
                System.out.printf(NEGRITO+VERDE+"Gato cadastrado com sucesso:" +RESETAR
                    +AZUL+ "\nNome: %s\nMatrícula: %s\n", novo_gato.getNomePet(),novo_gato.getMatriculaPet() +RESETAR);
                break;
            }
            //Se a espécie for um Cachorro
            case 2: {
                System.out.print(CYAN+ "Escolha a raça do seu cachorro" +RESETAR+AMARELO+ "\n 1 - SRD,\n" + 
                    " 2 - LABRADOR,\n" + 
                    " 3 - PINSCHER,\n" +
                    " 4 - POODLE\n: " +RESETAR);
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
                        System.out.println(NEGRITO+VERMELHO+ "Escolha Inválida." +RESETAR);
                        break;
                    }
                }
                Cachorro novo_cachorro = new Cachorro(nome_pet,idade_pet,donoPet,racasCachorro);
                donoPet.adicionarPet(novo_cachorro);
                lista_pets.add(novo_cachorro);
                System.out.printf(NEGRITO+VERDE+ "\nCachorro cadastrado com sucesso:"+RESETAR
                    +AZUL+ "\nNome: %s\nMatrícula: %s\n", novo_cachorro.getNomePet(),novo_cachorro.getMatriculaPet() +RESETAR);
                break;
            }  
            //Se a espécie for Inválida
            default: {
                System.out.println(NEGRITO+VERMELHO+ "Espécie do Pet Inválida." +RESETAR);
                break;
            }
        }
    }
    //Atribuir Pet a um Tutor
    public static void atribuirPet_Tutor () {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | ATRIBUIR PET A TUTOR | \n" +RESETAR);
        String cpfTutor;
        while (true) {
            try {
                System.out.print(AMARELO+ "Insira o CPF do Tutor que deseja atribuir o Pet: " +RESETAR);
                String cpfValidar = teclado.nextLine();
                if (Validar.validarCPF(cpfValidar)) {
                    cpfTutor = cpfValidar;
                    break;
                }
            }
            catch (CpfInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        Tutor tutorAdd = Tutor.consultarTutor(cpfTutor);
        if (tutorAdd != null) {
            System.out.print(AMARELO+ "Insira a matrícula do Pet: " +RESETAR);
            String matriculaPet = teclado.nextLine();
            Pet petAdd = Pet.consultarPet(matriculaPet);
            if (petAdd != null && (petAdd.getDonoPet()==null)) {
            tutorAdd.adicionarPet(petAdd);
            petAdd.setDonoPet(tutorAdd);
                System.out.println(NEGRITO+VERDE+ "Pet atribuído com sucesso ao Tutor!" +RESETAR);
            } else if (petAdd.getDonoPet()!=null) {
                System.out.println(NEGRITO+VERMELHO+ "Pet pertence a um Tutor existente." +RESETAR);
            } else {
                System.out.println(NEGRITO+VERMELHO+ "Matrícula inválida OU Não cadastrada." +RESETAR);
            }
        } else {
            System.out.println(NEGRITO+VERMELHO+ "CPF Não cadastrado." +RESETAR);
        }
    }
    //Remover Pet de um Tutor
    public static void removerPet_Tutor() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | DESATRIBUIR PET DE TUTOR | \n" +RESETAR);
        String cpfTutor;
        while (true) {
            try {
                System.out.print(AMARELO+ "Insira o CPF do Tutor que deseja remover o Pet: " +RESETAR);
                String cpfValidar = teclado.nextLine();
                if (Validar.validarCPF(cpfValidar)) {
                    cpfTutor = cpfValidar;
                    break;
                }
            }
            catch (CpfInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
        Tutor tutorDel = Tutor.consultarTutor(cpfTutor);
        if (tutorDel instanceof Tutor) {
            System.out.println(MAGENTA+ "Pets de " +RESETAR + tutorDel.getNomeTutor() + MAGENTA+ ":" +RESETAR);
            for (Pet pet : tutorDel.getPets()) {
                System.out.println(AZUL+ "Nome do Pet: " +RESETAR + pet.getNomePet() + "\n" +
                    AZUL+ "Matrícula: " +RESETAR + pet.getMatriculaPet()+"\n");    
            }
            System.out.print(AMARELO+ "Insira a matrícula do Pet que deseja remover do Tutor: " +RESETAR);
            String matriculaPetDel = teclado.nextLine();
            Pet petDel = Pet.consultarPet(matriculaPetDel);
            if (petDel instanceof Pet) {
                tutorDel.deletarPet(petDel);
                petDel.setDonoPet(null);
                System.out.println(NEGRITO+VERDE+ "Pet deletado com sucesso do Tutor!" +RESETAR);
            } else {
                System.out.println(NEGRITO+VERMELHO+ "Matrícula inválida OU Não cadastrada." +RESETAR);
            }
        } else {
            System.out.println(NEGRITO+VERMELHO+ "CPF Não cadastrado." +RESETAR);
        }
    }
}
