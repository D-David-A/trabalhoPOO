package estudoDeCaso;

public class Terreno extends Imovel {
    private TipoTerreno tipo;

    public Terreno(TipoTerreno tipo){
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
        return "Tipo de terreno: "+getTipo();
    }
}