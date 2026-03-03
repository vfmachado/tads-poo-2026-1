package Ex05;

public class ItemPedido {
    
    private String nome;
    private double valor;
    private int quantidade;

    // existe uma forma de iniciar o objeto passando parametros indicando COMO EU GOSTARIA QUE ELE FOSSE INCIADO
    // construtor
    ItemPedido(String nome, double valorUnitario, int quantidade) {
        // this remete a propria instancia
        // this.nome é atributo
        // só "nome" é o parametro do construtor
        this.nome = nome;
    }

}
