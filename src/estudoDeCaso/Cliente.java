package estudoDeCaso;

import java.util.Objects;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email){
        this.setNome(nome);
        this.setCpf(cpf);
        this.setTelefone(telefone);
        this.setEmail(email);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        if(!Validacoes.eValidoString(nome)){
            throw new IllegalArgumentException("Erro: Nome não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if(!Validacoes.eValidoCPF(cpf)){
            throw new CpfInvalidoException(Validacoes.erroCPF(cpf));
        }
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        if(!Validacoes.eValidoTelefone(telefone)){
            throw new IllegalArgumentException("Erro: Telefone deve conter 8 dígitos!");
        }
        this.telefone = "9" + telefone;
    }

    public void setEmail(String email) {
        if(!Validacoes.eValidoEmail(email)){
            throw new IllegalArgumentException("Erro: Email deve conter o @, não pode ser nulo e nem vazio!");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return  "\nNome: "+getNome()+
                "\nCPF: "+getCpf()+
                "\nTelefone: "+getTelefone()+
                "\nEmail: "+getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}