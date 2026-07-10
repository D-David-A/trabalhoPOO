package estudoDeCaso;

public class Casa extends Imovel {
    private int nrQuartos;
    private boolean garagem;
    private double iptu;

    public Casa (Endereco endereco, double valor, StatusImovel status, int nrQuartos, boolean garagem, double iptu){
        super(endereco, valor, status);
        this.setNrQuartos(nrQuartos);
        this.setGaragem(garagem);
        this.setIptu(iptu);
    }

    public int getNrQuartos() {
        return nrQuartos;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public double getIptu() {
        return iptu;
    }

    public void setIptu(double iptu) {
        if(!Validacoes.eValidoDouble(iptu)){
            throw new IllegalArgumentException("Erro: Iptu deve ser maior que 0!");
        }
        this.iptu = iptu;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    public void setNrQuartos(int nrQuartos) {
        if(!Validacoes.eValidoInt(nrQuartos)){
            throw new IllegalArgumentException("Erro: Número de quartos deve ser maior que 0!");
        }
        this.nrQuartos = nrQuartos;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nNº de quartos: "+getNrQuartos()+
                "\nGaragem: "+(isGaragem() ? "Sim" : "Não")+
                "\nIPTU: "+String.format("%.2f",getIptu());
    }

    @Override
    public double calcularValorFinal() {
        return getIptu();
    }
}