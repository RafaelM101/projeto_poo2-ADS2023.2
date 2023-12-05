package controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;

import Pets.Cachorro;
import Pets.Gato;
import Pets.Pet;
import Tutores.Tutor;
import components.CRUD;
import components.RacasCachorro;
import components.RacasGato;
import components.Terminal;
import components.Validar;
import exceptions.CpfInvalidoException;
import exceptions.ListaVaziaException;
import exceptions.MatriculaInvalidaException;

public class PetController implements CRUD, Terminal {
    protected static ArrayList<Pet> lista_pets = new ArrayList<>();

    //Data Seed
    public static void data_seed_pet() throws ListaVaziaException {
        Cachorro cachorro_1 = new Cachorro("Maylon", 2, TutorController.consultarTutor("111.111.111-11"), RacasCachorro.SRD);
        PetController.lista_pets.add(cachorro_1);
        TutorController.consultarTutor("111.111.111-11").adicionarPet(cachorro_1);
        Cachorro cachorro_2 = new Cachorro("Nick", 7, TutorController.consultarTutor("222.222.222-22"), RacasCachorro.POODLE);
        PetController.lista_pets.add(cachorro_2);
        TutorController.consultarTutor("222.222.222-22").adicionarPet(cachorro_2);
        Gato gato_1 = new Gato("Leon", 5, TutorController.consultarTutor("111.111.111-11"), RacasGato.SRD);
        PetController.lista_pets.add(gato_1);
        TutorController.consultarTutor("111.111.111-11").adicionarPet(gato_1);
        Gato gato_2 = new Gato("Luca", 3, TutorController.consultarTutor("222.222.222-22"), RacasGato.SRD);
        PetController.lista_pets.add(gato_2);
        TutorController.consultarTutor("222.222.222-22").adicionarPet(gato_2);
        PetController.listar();

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
        System.out.println(NEGRITO+AMARELO+ "\nFormato da Matrícula: XXXX" +RESETAR);
        String matriculaBuscar;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite a matrícula do Pet que deseja atualizar: " +RESETAR);
                String matriculaBuscarValidando = teclado.nextLine();
                if (Validar.ValidarMatricula(matriculaBuscarValidando)) {
                    matriculaBuscar = matriculaBuscarValidando;
                    Pet pet = consultarPet(matriculaBuscar);
                    if (pet instanceof Pet) {
                        System.out.println(NEGRITO+PRETO+ "\n\t\t Vamos atualizar o cadastro do Pet" +RESETAR);
                        //Idade do Pet
                        while (true) {
                            try {
                                System.out.print(AMARELO+ "Digite a nova Idade: " +RESETAR);
                                int novaIdade = teclado.nextInt();
                                teclado.nextLine();
                                pet.setIdadePet(novaIdade);
                                System.out.println(NEGRITO+VERDE+ "\nO CADASTRO DO SEU PET FOI ATUALIZADO COM SUCESSO!" +RESETAR);
                                break;
                            }
                            catch (InputMismatchException e) {
                                System.out.println(NEGRITO+VERMELHO+ "Digite apenas o número da idade do Pet." +RESETAR);
                                teclado.nextLine();
                            }
                        }
                        break;
                    } else {
                        System.out.println(NEGRITO+VERMELHO+ "\nMATRÍCULA NÃO CADASTRADA!" +RESETAR);
                        break;
                    }
                }
            }
            catch (MatriculaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
   
    //Deleta um Pet da lista_pets
    public static void deletar() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t  | DELETAR PET | \n" +RESETAR);
        System.out.println(NEGRITO+AMARELO+ "\nFormato da Matrícula: XXXX" +RESETAR);
        String deletarMatricula;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite a matrícula do Pet que deseja deletar: " +RESETAR);
                String deletarMatriculaValidando = teclado.nextLine();
                if (Validar.ValidarMatricula(deletarMatriculaValidando)) {
                    deletarMatricula = deletarMatriculaValidando;
                    Pet delPet = consultarPet(deletarMatricula);
                    if(delPet instanceof Pet) {
                        Tutor dono = delPet.getDonoPet();
                        if (dono != null) {
                            dono.deletarPet(delPet);
                            lista_pets.remove(delPet);
                        } else {
                            lista_pets.remove(delPet);
                        }
                        System.out.println(NEGRITO+VERDE+ "\nPET DELETADO COM SUCESSO!" +RESETAR);
                        break;
                    } else {
                        System.out.println(NEGRITO+VERMELHO+ "\nMATRÍCULA NÃO CADASTRADA!" +RESETAR);
                        break;
                    }
                }
            }
            catch (MatriculaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    //Cadastrar um Pet
    public static void cadastrar(){
        System.out.println(NEGRITO+PRETO+ " \t\t\t  | CADASTRO DE PET | \n" +RESETAR);
        String cpf_tutor;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
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
        if(TutorController.consultarTutor(cpf_tutor)==null){
            while (true) {
                try {
                    System.out.print(NEGRITO+VERMELHO+ "\nTutor não encontrado," +RESETAR+CYAN+ " deseja cadastrar?\n" +AMARELO
                        +"1 - para SIM\n"
                        +"2 - para NÃO\n: " +RESETAR);
                    int escolha = teclado.nextInt();
                    teclado.nextLine();
                    if(escolha == 1) {
                        TutorController.cadastrarPorPet(cpf_tutor);
                        break;
                    } else if (escolha == 2) {
                        System.out.println(NEGRITO+VERMELHO+ "\nTUTOR NÃO PODE SER NULO."
                            +"\nCADASTRO NÃO PODERÁ SER REALIZADO!" +RESETAR);
                        return;
                    } else {
                        System.out.println(NEGRITO+VERMELHO+ "\nOPÇÃO INVÁLIDA." +RESETAR);
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                    teclado.nextLine();
                }
            }
        }
        Tutor donoPet = TutorController.consultarTutor(cpf_tutor);
        //Cadastro do Pet
        System.out.print(AMARELO+ "\nDigite o nome do Pet: " +RESETAR);
        String nome_pet = teclado.nextLine();
        Integer idade_pet;
        while (true) {
            try {
                System.out.print(AMARELO+ "Digite a idade do Pet: " +RESETAR);
                Integer idade = teclado.nextInt();
                teclado.nextLine();
                idade_pet = idade;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Insira apenas os números da idade." +RESETAR);
                teclado.nextLine();
            }
        }
        Integer escolhaTipoPet;
        while (true) {
            try {
                System.out.print(CYAN+ "\nEscolha a espécie do Pet:" +RESETAR+AMARELO+ " \n1- GATO \n2- CACHORRO\n: " +RESETAR);
                Integer escolha = teclado.nextInt();
                teclado.nextLine();
                escolhaTipoPet = escolha;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                teclado.nextLine();
            }
        }
        switch (escolhaTipoPet) {
            //Se a espécie for um Gato
            case 1:{
                Integer escolhaRacaGato;
                RacasGato racasGato = null;
                while (true) {
                    try {
                        System.out.print(CYAN+ "Escolha a Raça do seu Gato:" +RESETAR+AMARELO+ "\n 1 - SRD,\n" + 
                            " 2 - PERSA,\n" + 
                            " 3 - SIAMES,\n" + 
                            " 4 - BENGAL\n: " +RESETAR);
                        Integer escolhaGato = teclado.nextInt();
                        teclado.nextLine();
                        escolhaRacaGato = escolhaGato;
                        break;
                    }
                    catch (InputMismatchException e) {
                        System.out.println(NEGRITO+VERMELHO+ "Insira apenas o dígito da opção desejada." +RESETAR);
                        teclado.nextLine();
                    }
                }
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
                        System.out.println(NEGRITO+VERMELHO+ "\nESCOLHA INVÁLIDA." +RESETAR);
                        break;
                    }
                }
                Gato novo_gato = new Gato(nome_pet,idade_pet,donoPet,racasGato);
                donoPet.adicionarPet(novo_gato);
                lista_pets.add(novo_gato);
                System.out.printf(NEGRITO+VERDE+"\nGATO CADASTRADO COM SUCESSO:" +RESETAR
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
                        System.out.println(NEGRITO+VERMELHO+ "\nESCOLHA INVÁLIDA." +RESETAR);
                        break;
                    }
                }
                Cachorro novo_cachorro = new Cachorro(nome_pet,idade_pet,donoPet,racasCachorro);
                donoPet.adicionarPet(novo_cachorro);
                lista_pets.add(novo_cachorro);
                System.out.printf(NEGRITO+VERDE+ "\nCACHORRO CADASTRADO COM SUCESSO:"+RESETAR
                    +AZUL+ "\nNome: %s\nMatrícula: %s\n", novo_cachorro.getNomePet(),novo_cachorro.getMatriculaPet() +RESETAR);
                break;
            }  
            //Se a escolha for Inválida
            default: {
                System.out.println(NEGRITO+VERMELHO+ "\nESCOLHA DE ESPÉCIE DO PET INVÁLIDA." +RESETAR);
                break;
            }
        }
    }
    
    //Atribuir Pet a um Tutor
    public static void atribuirPet_Tutor () {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | ATRIBUIR PET A TUTOR | \n" +RESETAR);
        String cpfTutor;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
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
        Tutor tutorAdd = TutorController.consultarTutor(cpfTutor);
        if (tutorAdd != null) {
            System.out.println(NEGRITO+AMARELO+ "\nFormato da Matrícula: XXXX" +RESETAR);
            String matriculaPet;
            while (true) {
                try {
                    System.out.print(AMARELO+ "Insira a matrícula do Pet: " +RESETAR);
                    String matriculaPetValidar = teclado.nextLine();
                    if (Validar.ValidarMatricula(matriculaPetValidar)) {
                        matriculaPet = matriculaPetValidar;
                        Pet petAdd = PetController.consultarPet(matriculaPet);
                        if (petAdd != null) { 
                            if (petAdd.getDonoPet()==null) {
                                tutorAdd.adicionarPet(petAdd);
                                petAdd.setDonoPet(tutorAdd);
                                System.out.println(NEGRITO+VERDE+ "\nPET ATRIBUÍDO COM SUCESSO AO TUTOR!" +RESETAR);
                                break;
                            } else if (petAdd.getDonoPet()!=null) {
                                System.out.println(NEGRITO+VERMELHO+ "\nPET PERTENCE A UM TUTOR EXISTENTE." +RESETAR);
                                break;
                            }
                        } else {
                            System.out.println(NEGRITO+VERMELHO+ "\nMATRÍCULA NÃO CADASTRADA!" +RESETAR);
                            break;
                        }
                    }
                }
                catch (MatriculaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println(NEGRITO+VERMELHO+ "\nCPF NÃO CADASTRADO." +RESETAR);
        }
    }
    
    //Remover Pet de um Tutor
    public static void removerPet_Tutor() {
        System.out.println(NEGRITO+PRETO+ " \t\t\t | DESATRIBUIR PET DE TUTOR | \n" +RESETAR);
        String cpfTutor;
        System.out.println(NEGRITO+AMARELO+ "\nFormato do CPF: XXX.XXX.XXX-XX" +RESETAR);
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
        Tutor tutorDel = TutorController.consultarTutor(cpfTutor);
        if (tutorDel instanceof Tutor) {
            System.out.println(MAGENTA+ "Pets de " +RESETAR + tutorDel.getNomeTutor() + MAGENTA+ ":" +RESETAR);
            if (tutorDel.getPets().size() < 1) {
                System.out.println(NEGRITO+VERMELHO+ "\nNão possui nenhum Pet" +RESETAR);
                return;
            }
            for (Pet pet : tutorDel.getPets()) {
                System.out.println(AZUL+ "Nome do Pet: " +RESETAR + pet.getNomePet() + "\n" +
                    AZUL+ "Matrícula: " +RESETAR + pet.getMatriculaPet()+"\n");    
            }
            System.out.println(NEGRITO+AMARELO+ "\nFormato da Matrícula: XXXX" +RESETAR);
            String matriculaPetDel;
            while (true) {
                try {
                    System.out.print(AMARELO+ "Insira a matrícula do Pet que deseja remover do Tutor: " +RESETAR);
                    String matriculaPetValidando = teclado.nextLine();
                    if (Validar.ValidarMatricula(matriculaPetValidando)) {
                        matriculaPetDel = matriculaPetValidando;
                        Pet petDel = PetController.consultarPet(matriculaPetDel);
                        if (petDel instanceof Pet) {
                            tutorDel.deletarPet(petDel);
                            petDel.setDonoPet(null);
                            System.out.println(NEGRITO+VERDE+ "\nPET DELETADO COM SUCESSO DO TUTOR!" +RESETAR);
                            break;
                        } else {
                            System.out.println(NEGRITO+VERMELHO+ "\nMATRÍCULA NÃO CADASTRADA." +RESETAR);
                            break;
                        }
                    }
                }
                catch (MatriculaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println(NEGRITO+VERMELHO+ "\nCPF NÃO CADASTRADO." +RESETAR);
        }
    }
}
