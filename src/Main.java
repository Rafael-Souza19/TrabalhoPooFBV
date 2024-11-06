import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1. Adicionar Item");
            System.out.println("2. Listar Itens");
            System.out.println("3. Atualizar Item");
            System.out.println("4. Remover Item");
            System.out.println("5. Selecionar Itens para Produto Final");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do item: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço do item: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Quantidade do item: ");
                    int quantidade = scanner.nextInt();
                    estoque.adicionarItem(new Item(nome, preco, quantidade));
                    break;

                case 2:
                    System.out.println("Itens no Estoque:");
                    for (ItemEstoque item : estoque.listarItens()) {
                        System.out.println(item);
                    }
                    break;

                case 3:
                    System.out.print("Nome do item a ser atualizado: ");
                    String nomeAtualizar = scanner.nextLine();
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    System.out.print("Nova Quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    estoque.atualizarItem(nomeAtualizar, novoPreco, novaQuantidade);
                    break;

                case 4:
                    System.out.print("Nome do item a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    estoque.removerItem(nomeRemover);
                    break;

                case 5:
                    List<ItemEstoque> itensSelecionados = new ArrayList<>();
                    double totalGastos = 0;
                    boolean continuarSelecionando = true;

                    while (continuarSelecionando) {
                        System.out.println("Selecione um item pelo nome ou digite 'sair' para finalizar:");
                        String nomeSelecionado = scanner.nextLine();
                        if (nomeSelecionado.equalsIgnoreCase("sair")) {
                            continuarSelecionando = false;
                        } else {
                            ItemEstoque itemSelecionado = estoque.buscarItem(nomeSelecionado);
                            if (itemSelecionado != null) {
                                System.out.print("Quantidade desejada: ");
                                int quantidadeDesejada = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer

                                try {
                                    // Validar se a quantidade desejada
                                    if (quantidadeDesejada > itemSelecionado.getQuantidade()) {
                                        throw new QuantidadeInsuficienteException("Quantidade insuficiente no estoque.");
                                    }

                                    itemSelecionado.setQuantidade(itemSelecionado.getQuantidade() - quantidadeDesejada);
                                    itensSelecionados.add(itemSelecionado);
                                    totalGastos += itemSelecionado.getPreco() * quantidadeDesejada;

                                    System.out.println(itemSelecionado + " adicionado. Total até agora: R$ " + totalGastos);
                                } catch (QuantidadeInsuficienteException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Item não encontrado.");
                            }
                        }
                    }

                    System.out.println("Total de gastos para os itens selecionados: R$ " + totalGastos);
                    System.out.println("Produto final calculado. Preço: R$ " + totalGastos);
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
