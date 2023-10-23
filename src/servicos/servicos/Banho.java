package servicos.servicos;

public class Banho extends ModeloServiço {

    private String animal;
    private String funcionario;


    public Banho(Float preço, Integer codigo, Integer dia, Integer mes, Integer ano, Integer hora, Integer minutos, String animal, String funcionario) {
        super(preço, codigo, dia, mes, ano, hora, minutos);
        this.animal = animal;
        this.funcionario = funcionario;
    }
    
}
