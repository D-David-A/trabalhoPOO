package estudoDeCaso;

import java.time.Year;
import java.util.Objects;

public abstract class Imovel implements Calculavel {
    private int cod;
    private Endereco endereco;
    private double valor;
    private StatusImovel status;

    private static int contador = 0;
    private static int anoAtual = Year.now().getValue();

    public static int gerarCodigo(){
        contador++;
        return((anoAtual * 10000) + contador);
    }

    public Imovel(Endereco endereco, double valor, StatusImovel status){
        this.cod = gerarCodigo();
        setEndereco(endereco);
        setValor(valor);
        setStatus(status);
    }

    public int getCod() {
        return cod;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getValor() {
        return valor;
    }

    public StatusImovel getStatus() {
        return status;
    }

    public void setEndereco(Endereco endereco) {
        if(endereco == null){
            throw new IllegalArgumentException("Erro: O endereço não pode ser nulo!");
        }
        this.endereco = endereco;
    }

    public void setValor(double valor) {
        if(!Validacoes.eValidoDouble(valor)){
            throw new IllegalArgumentException("Erro: Valor deve ser maior que 0");
        }
        this.valor = valor;
    }

    public void setStatus(StatusImovel status) {
        if(status == null){
            throw new IllegalArgumentException("Erro: O status não pode ser nulo!");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return  "\nCódigo: " + getCod() +
                "\nValor do Imóvel: " + String.format("%.2f", getValor()) +
                "\nStatus: " + getStatus() +
                "\nEndereço: " + getEndereco();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return cod == imovel.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cod);
    }

    @Override
    public abstract double calcularValorFinal();
}