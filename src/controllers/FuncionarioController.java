package controllers;

import static main.Main.LimparTela;

import java.util.InputMismatchException;

import components.CRUD;
import components.EspecializacoesVet;
import components.Setores;
import components.Terminal;
import components.Validar;
import exceptions.CRMVInvalidoException;
import exceptions.CpfInvalidoException;
import funcionarios.Funcionario;
import funcionarios.Veterinario;

public class FuncionarioController implements CRUD, Terminal{
    private Funcionario funcionario = new Funcionario(null, NEGRITO, NEGRITO, null);
    
    public FuncionarioController() {
    }

    public void cadastrar(){
            try{
                System.out.println(MAGENTA + NEGRITO + "\n| Cadastro de Funcionários |\n\n"+ RESETAR);
                System.out.print(AZUL + "Digite nome do funcionário: " + RESETAR);
                String nome = teclado.nextLine();
                System.out.print(AZUL + "Digite o sobrenome do funcionário: " +RESETAR);
                String sobrenome = teclado.next();
                nome += " "+sobrenome;
                teclado.nextLine();
                try {
                    Validar.validarLetras(nome);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    cadastrar();
                    return;
                }
                System.out.print(AZUL + "Digite o salário do funcionário: " + RESETAR);
                Double salario = teclado.nextDouble();
                teclado.nextLine();
                String CPF;
                while(true){
                    try{
                        System.out.print(AZUL + "Digite o CPF do funcionário: " + RESETAR) ;
                        String cpfValidando = teclado.nextLine();
                        if(Validar.validarCPF(cpfValidando)) {
                            CPF = cpfValidando;
                            break; 
                        }
                    }
                    catch(CpfInvalidoException e){
                        System.out.println(e.getMessage());
                    }
            }
                System.out.print(AZUL + "\t\t\tEscolha o setor do funcionário:\nDigite 1 PARA SERVICOS_GERAIS ou 2 PARA CLINICA_VET : "+ RESETAR);
                Integer escolha = teclado.nextInt();
                teclado.nextLine();
                Setores setor = null;
                if(escolha==1){
                    setor = Setores.SERVICOS_GERAIS;
                    Funcionario func = new Funcionario(salario, CPF, nome, setor);
                    System.out.println(MAGENTA + NEGRITO +"\nFuncionário cadastrado!\nDados:" + RESETAR);
                    System.out.printf(func.toString());
                    LimparTela();
                    funcionario.getListaF().add(func);
                } else if (escolha==2) {
                    setor = Setores.CLINICA_VET;
                    String crmv;
                    while(true){
                        try{
                            System.out.print(AZUL + "Insira o CRMV do Veterinário: " + RESETAR) ;
                            String CRMVValidando = teclado.nextLine();
                            if(Validar.validarCRMV(CRMVValidando)) {
                                crmv = CRMVValidando;
                                break; 
                            }
                        }
                        catch(CRMVInvalidoException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println(AZUL + "Escolha a Especialização do Veterinário:\n 1 - CLINICO,\n" +
                            " 2 - CIRURGIAO,\n" +
                            " 3 - ORTOPEDISTA,\n" +
                            " 4 - ONCOLOGISTA\n" + RESETAR);
                    EspecializacoesVet espec = null;
                    Integer escolha_espec = teclado.nextInt();
                    switch (escolha_espec){
                        case 1:
                            espec = EspecializacoesVet.CLINICO;
                            break;
                        case 2:
                            espec = EspecializacoesVet.CIRURGIAO;
                            break;
                        case 3:
                            espec = EspecializacoesVet.ORTOPEDISTA;
                            break;
                        case 4:
                            espec = EspecializacoesVet.ONCOLOGISTA;
                            break;
                        default:
                            System.out.println(NEGRITO+ VERMELHO+"Escolha inválida."+RESETAR);
                            break;
                    } Veterinario vet = new Veterinario(salario, CPF, nome, setor, crmv, espec);
                        funcionario.getListaF().add(vet);
                }else System.out.println(NEGRITO+ VERMELHO+"OPÇÃO INVÁLIDA."+RESETAR);
            }
            catch(InputMismatchException e){
                System.out.println(NEGRITO+ VERMELHO+"INSIRA UM VALOR VÁLIDO"+RESETAR);
                return;
            }
    }

}
