// PUBLIC (NA CLASSE) - VISIVEL PARA OUTRAS CLASSES, ENTAO ELAS CONSEGUEM IMPORTAR E INSTANCIAR
public class Nota {
    
    // ATRIBUTO PRIVADO - visivel apenas dentro da propria classe NOTA
    private float valor;

    /*
    final - constante / nao pode ser alterada
    String - tipo texto
    public - publicamente visivel / outras classes conseguem enxergar o atributo da instancia
    */
    public static final String descricao = "CLASSE VALUE OBJECT QUE DEFINE VALORES POSSIVEIS PARA NOTA";

    
    public void setValor(float valor) {
        // opcao A
        if (valor < 0) {
            valor = 0;
        } else if (valor > 10) { 
            valor = 10;
        } else {
            this.valor = valor;
        } 

        // opcao B
        this.valor = valor > 10 ? 10 : valor < 0 ? 0 : valor;

        // opcao C
        if (valor < 0) {
            this.valor = 0;
            return;
        }

        if (valor > 10) {
            valor = 10;
            return;
        }

        this.valor = valor;
    }

    public float getValor() {
        return this.valor;
    }
    

}
