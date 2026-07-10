package estudoDeCaso;

public class Endereco {
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;

    public Endereco (String logadouro, int numero, String bairro, String cidade) {
        this.setLogradouro(logadouro);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setCidade(cidade);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setLogradouro(String logradouro) {
        if(!Validacoes.eValidoString(logradouro)){
            throw new IllegalArgumentException("Erro: O logradouro não pode ser nulo ou vazio!");
        }
        this.logradouro = logradouro;
    }

    public void setNumero(int numero) {
        if(!Validacoes.eValidoInt(numero)){
            throw new IllegalArgumentException("Erro: O número deve ser maior que zero!");
        }
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        if(!Validacoes.eValidoString(bairro)){
            throw new IllegalArgumentException("Erro: O bairro não pode ser nulo ou vazio!");
        }
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        if(!Validacoes.eValidoString(cidade)){
            throw new IllegalArgumentException("Erro: A cidade não pode ser nulo ou vazio!");
        }
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return  "\nLogradouro: "+getLogradouro()+
                "\nNúmero: "+getNumero()+
                "\nBairro: "+getBairro()+
                "\nCidade: "+getCidade();
    }
}