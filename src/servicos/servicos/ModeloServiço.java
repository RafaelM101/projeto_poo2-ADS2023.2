package servicos.servicos;


abstract class ModeloServiço implements Serviço {

    private Float preço;
    private Integer codigo, dia, mes, ano, hora, minutos;


    public ModeloServiço(Float preço, Integer codigo, Integer dia, Integer mes, Integer ano, Integer hora, Integer minutos) {
        this.preço = preço;
        this.codigo = codigo;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minutos = minutos;
    }

    public Float getPreço() {
        return preço;
    }

    public void setPreço(Float preço) {
        this.preço = preço;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public void Agendar(){

    }

    public void Cancelar(){

    }

    public void Efetuar(){

    }
    
    public void Receber(){

    }
}