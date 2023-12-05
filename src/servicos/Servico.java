package servicos;

import Pets.Pet;
import components.CRUD;
import components.Terminal;
import funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.LocalTime;



public class Servico implements CRUD, Terminal {

    private Funcionario nome_funcionario;
    private Float preco;
    private ListaServicos tipo_agendamento;
    private Pet pet_agendamento;

    private LocalDate data_servico;

    private LocalTime hora_servico;


    public Servico(Funcionario nome_funcionario, Float preco, ListaServicos tipo_agendamento, Pet pet_agendamento) {
        this.nome_funcionario = nome_funcionario;
        this.preco = preco;
        this.tipo_agendamento = tipo_agendamento;
        this.pet_agendamento = pet_agendamento;
    }

    //GETTERS
    public Funcionario getNome_funcionario() {
        return nome_funcionario;
    }

    public ListaServicos getTipo_agendamento() {
        return tipo_agendamento;
    }

    public Float getPreco() {return preco;}

    public LocalDate getData_servico() {
        return data_servico;
    }

    public LocalTime getHora_servico() {
        return hora_servico;
    }

    //SETTERS
    public void setNome_funcionario(Funcionario nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public void setTipo_agendamento(ListaServicos tipo_agendamento) {
        this.tipo_agendamento = tipo_agendamento;
    }

    public void setData_servico(LocalDate data_servico) {
        this.data_servico = data_servico;
    }

    public void setHora_servico(LocalTime hora_servico) {
        this.hora_servico = hora_servico;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Pet getPet_agendamento() {
        return pet_agendamento;
    }

    @Override
    public String toString() {
        return CYAN + NEGRITO + "Data: " + RESETAR + VERDE + formatter.format(data_servico) + "\n" + RESETAR +
                CYAN + NEGRITO + "Horário: " + RESETAR + VERDE + hora_servico + "\n" + RESETAR +
                CYAN + NEGRITO + "Funcionário:" + RESETAR + VERDE + nome_funcionario.getNome() + "\n" + RESETAR +
                CYAN + NEGRITO + "Tipo do serviço: " + RESETAR + VERDE + tipo_agendamento + "\n" + RESETAR +
                CYAN + NEGRITO + "Preço: " + RESETAR + VERDE + "R$" + preco + "\n" + RESETAR +
                CYAN + NEGRITO + "Nome do pet: " + RESETAR + VERDE + pet_agendamento.getNomePet() + "\n" + RESETAR;

    }
}



