package vendas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vendas.controller.CategoriaController;
import vendas.controller.ClienteController;
import vendas.controller.FornecedorController;
import vendas.controller.FornecedorProdutoController;
import vendas.controller.ProdutoController;
import vendas.controller.ProdutoVendaController;
import vendas.controller.VendaController;
import vendas.model.Categoria;
import vendas.model.Cliente;
import vendas.model.Fornecedor;
import vendas.model.FornecedorProduto;
import vendas.model.Produto;
import vendas.model.ProdutoVenda;
import vendas.model.Venda;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static CategoriaController categoriaController = new CategoriaController();
    static ClienteController clienteController = new ClienteController();
    static FornecedorController fornecedorController = new FornecedorController();
    static ProdutoController produtoController = new ProdutoController();
    static FornecedorProdutoController fornecedorProdutoController = new FornecedorProdutoController();
    static VendaController vendaController = new VendaController();
    static ProdutoVendaController produtoVendaController = new ProdutoVendaController();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE VENDAS ===");
            System.out.println("1 - Categorias");
            System.out.println("2 - Clientes");
            System.out.println("3 - Fornecedores");
            System.out.println("4 - Produtos");
            System.out.println("5 - Fornecedor Produto");
            System.out.println("6 - Vendas");
            System.out.println("7 - Produto Venda");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuCategoria();
                case 2 -> menuCliente();
                case 3 -> menuFornecedor();
                case 4 -> menuProduto();
                case 5 -> menuFornecedorProduto();
                case 6 -> menuVenda();
                case 7 -> menuProdutoVenda();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== CATEGORIA ====================
    static void menuCategoria() {
        int opcao;
        do {
            System.out.println("\n=== CATEGORIAS ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Categoria categoria = new Categoria();
                    System.out.print("Nome da Categoria: ");
                    categoria.setNome(scanner.nextLine());
                    categoriaController.salvar(categoria);
                }
                case 2 -> {
                    Categoria categoria = new Categoria();
                    System.out.print("ID da Categoria: ");
                    categoria.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    categoria.setNome(scanner.nextLine());
                    categoriaController.alterar(categoria);
                }
                case 3 -> {
                    Categoria categoria = new Categoria();
                    System.out.print("ID da Categoria a Excluir: ");
                    categoria.setId(scanner.nextInt());
                    scanner.nextLine();
                    categoriaController.excluir(categoria);
                }
                case 4 -> {
                    Categoria categoria = new Categoria();
                    System.out.print("ID da Categoria a Pesquisar: ");
                    categoria.setId(scanner.nextInt());
                    scanner.nextLine();
                    categoriaController.pesquisar(categoria);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== CLIENTE ====================
    static void menuCliente() {
        int opcao;
        do {
            System.out.println("\n=== CLIENTES ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Cliente cliente = new Cliente();
                    System.out.print("Nome: ");
                    cliente.setNome(scanner.nextLine());
                    System.out.print("CPF: ");
                    cliente.setCpf(scanner.nextLine());
                    System.out.print("RG: ");
                    cliente.setRg(scanner.nextLine());
                    System.out.print("Endereço: ");
                    cliente.setEndereco(scanner.nextLine());
                    System.out.print("Telefone: ");
                    cliente.setTelefone(scanner.nextLine());
                    clienteController.salvar(cliente);
                }
                case 2 -> {
                    Cliente cliente = new Cliente();
                    System.out.print("ID do Cliente: ");
                    cliente.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    cliente.setNome(scanner.nextLine());
                    System.out.print("Novo CPF: ");
                    cliente.setCpf(scanner.nextLine());
                    System.out.print("Novo RG: ");
                    cliente.setRg(scanner.nextLine());
                    System.out.print("Novo Endereço: ");
                    cliente.setEndereco(scanner.nextLine());
                    System.out.print("Novo Telefone: ");
                    cliente.setTelefone(scanner.nextLine());
                    clienteController.alterar(cliente);
                }
                case 3 -> {
                    Cliente cliente = new Cliente();
                    System.out.print("ID do Cliente a Excluir: ");
                    cliente.setId(scanner.nextInt());
                    scanner.nextLine();
                    clienteController.excluir(cliente);
                }
                case 4 -> {
                    Cliente cliente = new Cliente();
                    System.out.print("Nome do Cliente a Pesquisar: ");
                    cliente.setNome(scanner.nextLine());
                    clienteController.pesquisar(cliente);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== FORNECEDOR ====================
    static void menuFornecedor() {
        int opcao;
        do {
            System.out.println("\n=== FORNECEDORES ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("Nome Fantasia: ");
                    fornecedor.setNomeFantasia(scanner.nextLine());
                    System.out.print("Razão Social: ");
                    fornecedor.setRazao_social(scanner.nextLine());
                    System.out.print("CNPJ: ");
                    fornecedor.setCnpj(scanner.nextLine());
                    fornecedorController.salvar(fornecedor);
                }
                case 2 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("ID do Fornecedor: ");
                    fornecedor.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Novo Nome Fantasia: ");
                    fornecedor.setNomeFantasia(scanner.nextLine());
                    System.out.print("Nova Razão Social: ");
                    fornecedor.setRazao_social(scanner.nextLine());
                    System.out.print("Novo CNPJ: ");
                    fornecedor.setCnpj(scanner.nextLine());
                    fornecedorController.alterar(fornecedor);
                }
                case 3 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("ID do Fornecedor a Excluir: ");
                    fornecedor.setId(scanner.nextInt());
                    scanner.nextLine();
                    fornecedorController.excluir(fornecedor);
                }
                case 4 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("Nome Fantasia do Fornecedor a Pesquisar: ");
                    fornecedor.setNomeFantasia(scanner.nextLine());
                    fornecedorController.pesquisar(fornecedor);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== PRODUTO ====================
    static void menuProduto() {
        int opcao;
        do {
            System.out.println("\n=== PRODUTOS ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Produto produto = new Produto();
                    System.out.print("Nome do Produto: ");
                    produto.setNome(scanner.nextLine());
                    System.out.print("ID da Categoria: ");
                    Categoria categoria = new Categoria();
                    categoria.setId(scanner.nextInt());
                    scanner.nextLine();
                    produto.setCategoria(categoria);
                    System.out.print("Preço: ");
                    produto.setPreco(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.print("Quantidade em Estoque: ");
                    produto.setQtdeEstoque(scanner.nextDouble());
                    scanner.nextLine();
                    produtoController.salvar(produto);
                }
                case 2 -> {
                    Produto produto = new Produto();
                    System.out.print("ID do Produto: ");
                    produto.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    produto.setNome(scanner.nextLine());
                    System.out.print("ID da Nova Categoria: ");
                    Categoria categoria = new Categoria();
                    categoria.setId(scanner.nextInt());
                    scanner.nextLine();
                    produto.setCategoria(categoria);
                    System.out.print("Novo Preço: ");
                    produto.setPreco(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.print("Nova Quantidade em Estoque: ");
                    produto.setQtdeEstoque(scanner.nextDouble());
                    scanner.nextLine();
                    produtoController.alterar(produto);
                }
                case 3 -> {
                    Produto produto = new Produto();
                    System.out.print("ID do Produto a Excluir: ");
                    produto.setId(scanner.nextInt());
                    scanner.nextLine();
                    produtoController.excluir(produto);
                }
                case 4 -> {
                    Produto produto = new Produto();
                    System.out.print("ID do Produto a Pesquisar: ");
                    produto.setId(scanner.nextInt());
                    scanner.nextLine();
                    produtoController.pesquisar(produto);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== FORNECEDOR PRODUTO ====================
    static void menuFornecedorProduto() {
        int opcao;
        do {
            System.out.println("\n=== FORNECEDOR PRODUTO ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Excluir");
            System.out.println("3 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    FornecedorProduto fp = new FornecedorProduto();
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("ID do Fornecedor: ");
                    fornecedor.setId(scanner.nextInt());
                    scanner.nextLine();
                    fp.setFornecedor(fornecedor);
                    Produto produto = new Produto();
                    System.out.print("ID do Produto: ");
                    produto.setId(scanner.nextInt());
                    scanner.nextLine();
                    fp.setProduto(produto);
                    fornecedorProdutoController.salvar(fp);
                }
                case 2 -> {
                    FornecedorProduto fp = new FornecedorProduto();
                    System.out.print("ID do Fornecedor Produto a Excluir: ");
                    fp.setId(scanner.nextInt());
                    scanner.nextLine();
                    fornecedorProdutoController.excluir(fp);
                }
                case 3 -> {
                    FornecedorProduto fp = new FornecedorProduto();
                    System.out.print("ID do Fornecedor Produto a Pesquisar: ");
                    fp.setId(scanner.nextInt());
                    scanner.nextLine();
                    fornecedorProdutoController.pesquisar(fp);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== VENDA ====================
    static void menuVenda() {
        int opcao;
        do {
            System.out.println("\n=== VENDAS ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Venda venda = new Venda();
                    venda.setDataVenda(LocalDate.now());
                    Cliente cliente = new Cliente();
                    System.out.print("ID do Cliente: ");
                    cliente.setId(scanner.nextInt());
                    scanner.nextLine();
                    venda.setCliente(cliente);

                    List<ProdutoVenda> produtos = new ArrayList<>();
                    String continuar = "s";
                    double valorTotal = 0;

                    while (continuar.equalsIgnoreCase("s")) {
                        ProdutoVenda pv = new ProdutoVenda();
                        Produto produto = new Produto();
                        System.out.print("ID do Produto: ");
                        produto.setId(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Preço do Produto: ");
                        produto.setPreco(scanner.nextDouble());
                        scanner.nextLine();
                        pv.setProduto(produto);
                        System.out.print("Quantidade: ");
                        pv.setQtdeVendida(scanner.nextDouble());
                        scanner.nextLine();
                        valorTotal += produto.getPreco() * pv.getQtdeVendida();
                        pv.setVenda(venda);
                        produtos.add(pv);
                        System.out.print("Adicionar mais um produto? (s/n): ");
                        continuar = scanner.nextLine();
                    }

                    venda.setValorTotal(valorTotal);
                    venda.setProdutosVendidos(produtos);
                    vendaController.salvar(venda);
                }
                case 2 -> {
                    Venda venda = new Venda();
                    System.out.print("ID da Venda: ");
                    venda.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Nova Data (AAAA-MM-DD): ");
                    venda.setDataVenda(LocalDate.parse(scanner.nextLine()));
                    System.out.print("Novo Valor Total: ");
                    venda.setValorTotal(scanner.nextDouble());
                    scanner.nextLine();
                    Cliente cliente = new Cliente();
                    System.out.print("ID do Novo Cliente: ");
                    cliente.setId(scanner.nextInt());
                    scanner.nextLine();
                    venda.setCliente(cliente);
                    vendaController.alterar(venda);
                }
                case 3 -> {
                    Venda venda = new Venda();
                    System.out.print("ID da Venda a Excluir: ");
                    venda.setId(scanner.nextInt());
                    scanner.nextLine();
                    vendaController.excluir(venda);
                }
                case 4 -> {
                    Venda venda = new Venda();
                    System.out.print("ID da Venda a Pesquisar: ");
                    venda.setId(scanner.nextInt());
                    scanner.nextLine();
                    vendaController.pesquisar(venda);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ==================== PRODUTO VENDA ====================
    static void menuProdutoVenda() {
        int opcao;
        do {
            System.out.println("\n=== PRODUTO VENDA ===");
            System.out.println("1 - Salvar");
            System.out.println("2 - Excluir");
            System.out.println("3 - Pesquisar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    ProdutoVenda pv = new ProdutoVenda();
                    Produto produto = new Produto();
                    System.out.print("ID do Produto: ");
                    produto.setId(scanner.nextInt());
                    scanner.nextLine();
                    pv.setProduto(produto);
                    Venda venda = new Venda();
                    System.out.print("ID da Venda: ");
                    venda.setId(scanner.nextInt());
                    scanner.nextLine();
                    pv.setVenda(venda);
                    System.out.print("Quantidade Vendida: ");
                    pv.setQtdeVendida(scanner.nextDouble());
                    scanner.nextLine();
                    produtoVendaController.salvar(pv);
                }
                case 2 -> {
                    ProdutoVenda pv = new ProdutoVenda();
                    System.out.print("ID do Produto Venda a Excluir: ");
                    pv.setId(scanner.nextInt());
                    scanner.nextLine();
                    produtoVendaController.excluir(pv);
                }
                case 3 -> {
                    ProdutoVenda pv = new ProdutoVenda();
                    System.out.print("ID do Produto Venda a Pesquisar: ");
                    pv.setId(scanner.nextInt());
                    scanner.nextLine();
                    produtoVendaController.pesquisar(pv);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}