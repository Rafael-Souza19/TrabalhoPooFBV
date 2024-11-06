public class Item implements ItemEstoque {
    private String nome;
    private double preco;
    private int quantidade;

    public Item(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public double getTotal() {
        return preco * quantidade;
    }

    @Override
    public String toString() {
        return "Item: " + nome + ", Preço: R$ " + preco + ", Quantidade disponível: " + quantidade;
    }
}
