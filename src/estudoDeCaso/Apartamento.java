package estudoDeCaso;

public class Apartamento extends Imovel {
    private int andar;
    private int numeroApt;
    private double vlrCondominio;
    private double iptu;

    public Apartamento(int andar, int numeroApt, double vlrCondominio, double iptu){
        this.setAndar(andar);
        this.setNumeroApt(numeroApt);
        this.setVlrCondominio(vlrCondominio);
        this.setIptu(iptu);
    }

    public int getAndar() {
        return andar;
    }

    public int getNumeroApt() {
        return numeroApt;
    }

    public double getVlrCondominio() {
        return vlrCondominio;
    }

    public double getIptu() {
        return iptu;
    }

    public void setAndar(int andar) {
        if(!Validacoes.eValidoInt(andar)){
            throw new IllegalArgumentException("Erro: Andar deve ser maior que 0!");
        }
        this.andar = andar;
    }

    public void setNumeroApt(int numeroApt) {
        if(!Validacoes.eValidoInt(numeroApt)){
            throw new IllegalArgumentException("Erro: Numero do apartamento deve ser maior que 0!");
        }
        this.numeroApt = numeroApt;
    }

    public void setVlrCondominio(double vlrCondominio) {
        if(!Validacoes.eValidoDouble(vlrCondominio)){
            throw new IllegalArgumentException("Erro: Valor do condomínio deve ser maior que 0!");
        }
        this.vlrCondominio = vlrCondominio;
    }

    public void setIptu(double iptu) {
        if(!Validacoes.eValidoDouble(iptu)){
            throw new IllegalArgumentException("Erro: Iptu deve ser maior que 0!");
        }
        this.iptu = iptu;
    }

    @Override
    public String toString() {
        return  "\nAndar: "+getAndar()+
                "\nNº Apartamento: "+getNumeroApt()+
                "\nValor do Condomínio: "+getVlrCondominio()+
                "\nIPTU: "+getIptu();
    }
}