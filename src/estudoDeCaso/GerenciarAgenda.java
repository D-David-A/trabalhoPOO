package estudoDeCaso;

import java.util.Scanner;

public class GerenciarAgenda {

    public static void main(String[] args) {
        ServicoImobiliaria servico = new ServicoImobiliaria();
        Scanner lt = new Scanner(System.in);
        int opcao = 0;

        do {
            menu();

            try {
                if (lt.hasNextInt()) {
                    opcao = lt.nextInt();
                    lt.nextLine();
                } else {
                    System.out.println("Erro: Digite um número válido!");
                    lt.next();
                    continue;
                }

                switch (opcao) {

                    case 1:
                        cadastrarCliente(servico, lt);
                        break;

                    case 2:
                        cadastrarImovel(servico, lt);
                        break;

                    case 3:
                        listarImoveis(servico);
                        break;

                    case 4:
                        venderImovel(servico, lt);
                        break;

                    case 5:
                        alugarImovel(servico, lt);
                        break;

                    case 6:
                        buscarImovel(servico, lt);
                        break;

                    case 7:
                        servico.gerarRelatorios();
                        break;

                    case 8:
                        System.out.println("Programa encerrado");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 6);
    }

    public static void menu() {
        String textBlock = """
                \n=================================
                                MENU
                
                1. Cadastrar Cliente
                2. Cadastrar Imóvel
                3. Listar Imóveis
                4. Vender Imóveis
                5. Alugar Imóvel
                6. Buscar Imóvel
                7. Relatórios
                8. Sair do Sistema
                =================================
                """;
        System.out.println(textBlock);
    }

    private static void cadastrarCliente(ServicoImobiliaria servico, Scanner lt) {
        System.out.print("Nome: ");
        String nome = lt.nextLine();
        System.out.print("CPF: ");
        String cpf = lt.nextLine();
        System.out.print("Telefone: ");
        String telefone = lt.nextLine();
        System.out.print("Email: ");
        String email = lt.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone, email);
        servico.cadastrarCliente(cliente);
    }

    private static void cadastrarImovel(ServicoImobiliaria servico, Scanner lt) {
        System.out.println("Tipo de Imóvel: 1 - Casa | 2 - Apartamento | 3 - Terreno");
        int tipo = lt.nextInt();
        lt.nextLine();

        if (tipo < 1 || tipo > 3) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }

        System.out.print("Logradouro do Endereço: ");
        String log = lt.nextLine();
        System.out.print("Número do Endereço: ");
        int num = lt.nextInt();
        lt.nextLine();
        System.out.print("Bairro: ");
        String bairro = lt.nextLine();
        System.out.print("Cidade: ");
        String cidade = lt.nextLine();

        Endereco endereco = new Endereco(log, num, bairro, cidade);

        System.out.print("Valor do imóvel: ");
        double valor = lt.nextDouble();
        lt.nextLine();

        if (tipo == 1) {
            System.out.print("Número de quartos: ");
            int quartos = lt.nextInt();
            lt.nextLine();
            boolean garagem = false;
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.print("Possui garagem (Sim / Não): ");
                String possui = lt.nextLine().trim();

                if (possui.equalsIgnoreCase("Sim") || possui.equalsIgnoreCase("S")) {
                    garagem = true;
                    entradaValida = true;
                } else if (possui.equalsIgnoreCase("Não") || possui.equalsIgnoreCase("Nao") || possui.equalsIgnoreCase("N")) {
                    garagem = false;
                    entradaValida = true;
                } else {
                    System.out.println("Erro: Digite apenas 'Sim' ou 'Não'.");
                }
            }
            System.out.print("Valor do IPTU: ");
            double iptu = lt.nextDouble();
            lt.nextLine();
            Casa casa = new Casa(endereco, valor, StatusImovel.Disponivel, quartos, garagem, iptu);
            servico.cadastrarImovel(casa);

        } else if (tipo == 2) {
            System.out.print("Andar: ");
            int andar = lt.nextInt();
            System.out.print("Número do Apartamento: ");
            int numApt = lt.nextInt();
            System.out.print("Valor do condomínio: ");
            double condo = lt.nextDouble();
            System.out.print("Valor do IPTU: ");
            double iptu = lt.nextDouble();
            lt.nextLine();
            Apartamento apt = new Apartamento(endereco, valor, StatusImovel.Disponivel, andar, numApt, condo, iptu);
            servico.cadastrarImovel(apt);

        } else if (tipo == 3) {
            TipoTerreno tipoTerreno = null;
            boolean tipoTerrenoValido = false;

            while (!tipoTerrenoValido) {
                System.out.println("Tipo de Terreno: 1 - Residencial | 2 - Comercial");
                int tTerreno = lt.nextInt();
                lt.nextLine();

                if (tTerreno == 1) {
                    tipoTerreno = TipoTerreno.Residencial;
                    tipoTerrenoValido = true;
                } else if (tTerreno == 2) {
                    tipoTerreno = TipoTerreno.Comercial;
                    tipoTerrenoValido = true;
                } else {
                    System.out.println("Erro: Opção inválida! Escolha 1 para Residencial ou 2 para Comercial.");
                }
            }

            Terreno terreno = new Terreno(endereco, valor, StatusImovel.Disponivel, tipoTerreno);
            servico.cadastrarImovel(terreno);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void listarImoveis(ServicoImobiliaria servico) {
        System.out.println("\n--- Lista de Imóveis Disponíveis ---");
        for (Imovel i : servico.buscarImovelPorStatus("Disponivel")) {
            System.out.println(i.toString() + "\n--------------------");
        }
    }

    private static void venderImovel(ServicoImobiliaria servico, Scanner scanner) {
        System.out.print("Digite o código do imóvel: ");
        int cod = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CPF do comprador: ");
        String cpf = scanner.nextLine().trim();

        Cliente cliente = null;
        for (Cliente c : servico.getClientes()) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Erro: Cliente não cadastrado no sistema!");
            return;
        }

        Imovel imovel = null;
        for (Imovel i : servico.buscarImovelPorStatus("Disponivel")) {
            if (i.getCod() == cod) {
                imovel = i;
                break;
            }
        }

        if (imovel != null) {
            servico.venderImovel(imovel, cliente);
        } else {
            System.out.println("Erro: Imóvel não encontrado ou não disponível.");
        }
    }

    private static void alugarImovel(ServicoImobiliaria servico, Scanner scanner) {
        System.out.print("Digite o código do imóvel: ");
        int cod = scanner.nextInt();
        scanner.nextLine();

        System.out.print("CPF do locatário: ");
        String cpf = scanner.nextLine().trim();

        Cliente cliente = null;
        for (Cliente c : servico.getClientes()) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Erro: Cliente não cadastrado no sistema!");
            return;
        }

        System.out.print("Valor mensal do aluguel: ");
        double vlrMensal = scanner.nextDouble();
        scanner.nextLine();

        Imovel imovel = null;
        for (Imovel i : servico.buscarImovelPorStatus("Disponivel")) {
            if (i.getCod() == cod) {
                imovel = i;
                break;
            }
        }

        if (imovel != null) {
            servico.alugarImovel(imovel, cliente, vlrMensal);
        } else {
            System.out.println("Erro: Imóvel não encontrado ou não disponível.");
        }
    }
    private static void buscarImovel(ServicoImobiliaria servico, Scanner scanner) {
        System.out.println("\n--- BUSCAR IMÓVEL ---");
        System.out.println("Deseja buscar por: [1] Tipo ou [2] Status?");
        int x = scanner.nextInt();
        scanner.nextLine();

        if (x == 1) {
            System.out.print("Digite o tipo (Casa, Apartamento, Terreno): ");
            String tipo = scanner.nextLine();
            for (Imovel i : servico.buscarImovelPorTipo(tipo)) {
                System.out.println(i.toString() + "\n--------------------");
            }
        } else if (x == 2) {
            System.out.print("Digite o status (Disponivel, Alugado, Vendido): ");
            String status = scanner.nextLine();
            for (Imovel i : servico.buscarImovelPorStatus(status)) {
                System.out.println(i.toString() + "\n--------------------");
            }
        } else {
            System.out.println("Erro: Opção inválida!");
        }
    }
}