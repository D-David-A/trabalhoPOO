package estudoDeCaso;

public class Contrato {
    private Cliente cliente;
    private Imovel imovel;
    private String tipoContrato;
    private double valorAcordado;

    public Contrato(Cliente cliente, Imovel imovel, String tipoContrato, double valorAcordado) {
        this.setCliente(cliente);
        this.setImovel(imovel);
        this.setTipoContrato(tipoContrato);
        this.setValorAcordado(valorAcordado);
    }

    public String emitirContrato(){
        return  "\n===== CONTRATO DE "+tipoContrato.toUpperCase()+"====="+
                "\nCliente: "+cliente.getNome()+
                "\nCPF: "+cliente.getCpf()+
                "\nCódigo do Imóvel: "+imovel.getCod()+
                "\nValor Acordado: R$"+String.format("%.2f",valorAcordado);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public double getValorAcordado() {
        return valorAcordado;
    }

    public void setCliente(Cliente cliente) {
        if(cliente == null){
            throw new IllegalArgumentException("Erro: Cliente não pode ser nulo!");
        }
        this.cliente = cliente;
    }

    public void setImovel(Imovel imovel) {
        if(imovel == null){
            throw new IllegalArgumentException("Erro: Imovel não pode ser nulo!");
        }
        this.imovel = imovel;
    }

    public void setTipoContrato(String tipoContrato) {
        if(!Validacoes.eValidoString(tipoContrato)){
            throw new IllegalArgumentException("Erro: Tipo de contrato não pode ser nulo ou vazio. Contrato deve ser 'Aluguel' ou 'Venda'!");
        }
        String formatoAceito = tipoContrato.trim();
        if(!formatoAceito.equalsIgnoreCase("Aluguel") && !formatoAceito.equalsIgnoreCase("Venda")){
            throw new IllegalArgumentException("Erro: Contrato deve ser 'Aluguel' ou 'Venda'!");
        }
        this.tipoContrato = formatoAceito.substring(0, 1).toUpperCase() + formatoAceito.substring(1).toLowerCase();
    }

    public void setValorAcordado(double valorAcordado) {
        if(!Validacoes.eValidoDouble(valorAcordado)){
            throw new IllegalArgumentException("Erro: Valor acordado deve ser maior que 0!");
        }
        this.valorAcordado = valorAcordado;
    }

    @Override
    public String toString() {
        return emitirContrato();
    }
}
