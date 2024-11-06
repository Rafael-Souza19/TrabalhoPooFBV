import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Item> itens;

    public Estoque() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public List<Item> listarItens() {
        return itens;
    }

    public Item buscarItem(String nome) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public void atualizarItem(String nome, double novoPreco, int novaQuantidade) {
        Item item = buscarItem(nome);
        if (item != null) {
            itens.remove(item);
            itens.add(new Item(nome, novoPreco, novaQuantidade));
        }
    }

    public void removerItem(String nome) {
        Item item = buscarItem(nome);
        if (item != null) {
            itens.remove(item);
        }
    }

    public double calcularTotalGastos() {
        double total = 0;
        for (Item item : itens) {
            total += item.getTotal();
        }
        return total;
    }
}
