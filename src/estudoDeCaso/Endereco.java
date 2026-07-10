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
        if(Validador.eValidoString(logradouro)){
            this.logradouro = logradouro;
        }
        else{
            throw new IllegalArgumentException("Erro: O logradouro não pode ser nulo ou vazio!");
        }
    }

    public void setNumero(int numero) {
        if(Validador.eValidoInt(numero)){
            this.numero = numero;
        }
        else{
            throw new IllegalArgumentException("Erro: O número deve ser maior que zero!");
        }
    }

    public void setBairro(String bairro) {
        if(Validador.eValidoString(bairro)){
            this.bairro = bairro;
        }
        else{
            throw new IllegalArgumentException("Erro: O bairro não pode ser nulo ou vazio!");
        }
    }

    public void setCidade(String cidade) {
        if(Validador.eValidoString(cidade)){
            this.cidade = cidade;
        }
        else{
            throw new IllegalArgumentException("Erro: A cidade não pode ser nulo ou vazio!");
        }
    }

    public void exibirDetalhes(){
        System.out.println("Logradouro: "+getLogradouro());
        System.out.println("Número: "+getNumero());
        System.out.println("Bairro: "+getBairro());
        System.out.println("Cidade: "+getCidade());
    }
}