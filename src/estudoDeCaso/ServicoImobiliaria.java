package estudoDeCaso;

import java.util.List;
import java.util.ArrayList;

public class ServicoImobiliaria {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Imovel> imoveis = new ArrayList<>();
    private List<Contrato> contratos = new ArrayList<>();

    public void cadastrarCliente(Cliente c){
        for(Cliente x : clientes){
            if(x.equals(c)){
                throw new ClienteCadastradoException("Erro: Cliente já cadastrado no sistema!");
            }
        }
        clientes.add(c);
        System.out.println("Cliente cadastrado!");
    }

    public void cadastrarImovel(Imovel i){
        for(Imovel x : imoveis){
            if(x.equals(i)){
                throw new ClienteCadastradoException("Erro: Imóvel já cadastrado no sistema!");
            }
        }
        imoveis.add(i);
        System.out.println("Imóvel cadastrado!");
    }

    public void venderImovel(Imovel i, Cliente comprador){
        if(!imoveis.contains(i)){
            throw new IllegalArgumentException("Erro: Imóvel não cadastrado!");
        }
        if(i.getStatus() != StatusImovel.Disponivel){
            throw new ImovelIndisponivelException("Erro: Imóvel não disponível no momento");
        }
        i.setStatus(StatusImovel.Vendido);

        Contrato venda = new Contrato(comprador, i, "Venda", i.calcularValorFinal());

        contratos.add(venda);

        System.out.println("Venda concluída!");
        System.out.println(venda.emitirContrato());
    }

    public void alugarImovel(Imovel i, Cliente comprador, double valorMensal){
        if(!imoveis.contains(i)){
            throw new IllegalArgumentException("Erro: Imóvel não cadastrado!");
        }
        if(i.getStatus() != StatusImovel.Disponivel){
            throw new ImovelIndisponivelException("Erro: Imóvel não disponível no momento");
        }
        i.setStatus(StatusImovel.Alugado);

        Contrato aluguel = new Contrato(comprador, i, "Aluguel", valorMensal);

        contratos.add(aluguel);

        System.out.println("Aluguel concluído!");
        System.out.println(aluguel.emitirContrato());
    }

    public List<Imovel> buscarImovelPorTipo(String tipo){
        List<Imovel> r = new ArrayList<>();
        String formatoAceito = tipo.trim();

        for (Imovel i : imoveis) {
            if (formatoAceito.equalsIgnoreCase("Casa") && i instanceof Casa) {
                r.add(i);
            } else if (formatoAceito.equalsIgnoreCase("Apartamento") && i instanceof Apartamento) {
                r.add(i);
            } else if (formatoAceito.equalsIgnoreCase("Terreno") && i instanceof Terreno) {
                r.add(i);
            }
        }
        return r;
    }

    public List<Imovel> buscarImovelPorStatus(String status){
        List<Imovel> r = new ArrayList<>();
        String formatoAceito = status.trim();

        for (Imovel i : imoveis) {
            if(i.getStatus().name().equalsIgnoreCase(formatoAceito)){
                r.add(i);
            }
        }
        return r;
    }

    public void gerarRelatorios(){
        int imoveisDisponiveis = 0;
        int imoveisVendidos = 0;
        int imoveisAlugados = 0;
        double totalVendido = 0.0;
        double totalAlugado = 0.0;
        Imovel maisCaro = null;

        for (Imovel i : imoveis) {
            if (i.getStatus() == StatusImovel.Disponivel) {
                imoveisDisponiveis++;
            }
            else if (i.getStatus() == StatusImovel.Vendido) {
                imoveisVendidos++;
                totalVendido += i.calcularValorFinal();
            }
            else if (i.getStatus() == StatusImovel.Alugado) {
                imoveisAlugados++;
            }

            // Descobre qual é o imóvel mais caro com base no valor base (ou valor final)
            if (maisCaro == null || i.getValor() > maisCaro.getValor()) {
                maisCaro = i;
            }
        }

        for (Contrato c : contratos) {
            if (c.getTipoContrato().equalsIgnoreCase("Aluguel")) {
                totalAlugado += c.getValorAcordado();
            }
        }

        System.out.println("\n===== RELATÓRIO GERAL =====");
        System.out.println("Imóveis Disponíveis: " + imoveisDisponiveis);
        System.out.println("Imóveis Vendidos: " + imoveisVendidos);
        System.out.println("Total de vendas: R$ " + String.format("%.2f", totalVendido));
        System.out.println("Imóveis Alugados: " + imoveisAlugados);
        System.out.println("Total com aluguéis: R$ " + String.format("%.2f", totalAlugado));

        if (maisCaro != null) {
            System.out.println("\n===== Imóvel Mais Caro =====");
            System.out.println("Código: " + maisCaro.getCod());
            System.out.println("Valor: R$"+String.format("%.2f", maisCaro.getValor()));
        }
        else {
            System.out.println("\n===== Imóvel Mais Caro =====");
            System.out.println("Nenhum imóvel cadastrado.");
        }
        System.out.println("==============================");
    }
}