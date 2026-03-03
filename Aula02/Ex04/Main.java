package Ex04;
// ex5
// List<ItemPedido> itens = new ArrayList<ItemPedido>();

public class Main {
    public static void main(String[] args) {
        SessaoVotacao s = new SessaoVotacao();

        s.votarSim(); // ignorar (fechada)
        s.abrir();
        // if (aberta)      if (!aberta)
        s.votarSim();
        s.votarSim();
        s.votarNao();

        s.fechar();
        s.votarNao(); // ignorar (fechada)

        System.out.println("Sim esperado = 2 | Atual = " + s.sim());
        System.out.println("Nao esperado = 1 | Atual = " + s.nao());
        System.out.println("Total esperado = 3 | Atual = " + s.total());
        System.out.println("Aberta esperado = false | Atual = " + s.estaAberta());
    }
}
