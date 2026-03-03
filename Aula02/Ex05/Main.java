package Ex05;

public class Main {
    public static void main(String[] args) {
        Pedido p = new Pedido();

        p.adicionarItem(new ItemPedido("Café", 8.50, 2));
        p.adicionarItem(new ItemPedido("Pão", 1.50, 6));

        System.out.println("Itens esperados = 2 | Atual = " + p.quantidadeDeItens());
        System.out.println("Total esperado = 26.0 | Atual = " + p.total());
        // 8.50*2 = 17.0
        // 1.50*6 = 9.0
        // total = 26.0
    }
}
