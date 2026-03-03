package Ex01;

public class CofreDigital {
    // private - outras classes/main nao tem acesso a essa variavel
    private int valor = 0;

    // public outras classes tem acesso
    public void depositar(int v) {
        if (v > 0)
            valor = valor + v;
    }

    public boolean sacar(int v) {
        if (v > valor) {
            return false;
        }

        valor = valor - v;
        return true;
    }

    public int saldo() {
        return valor;
    }
}