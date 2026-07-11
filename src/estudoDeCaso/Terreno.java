package estudoDeCaso;

public class Terreno extends Imovel {
    private TipoTerreno tipo;

    public Terreno(Endereco endereco, double valor, StatusImovel status,TipoTerreno tipo){
        super(endereco, valor, status);
        this.setTipo(tipo);
    }

    public TipoTerreno getTipo() {
        return tipo;
    }

    public void setTipo(TipoTerreno tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do terreno não pode ser nulo.");
        }
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nTipo de terreno: "+getTipo();
    }

    @Override
    public double calcularValorFinal() {
        return (getValor() + (getValor() * 0.08));
    }
}