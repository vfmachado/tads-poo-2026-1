public class Cor {
    
    private int r;
    private int g;
    private int b;

    // como o objeto deve NASCER
    // construtor padrao - public, sem parametros
    // quando temos apenas o construtor padrao e ele "nao faz nada", podemos omiti-lo
    public Cor() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    // metodo privado - "utilitario" para  propria classe
    private int getValorCorrigido(int v) {
        if (v < 0) v = 0;
        if (v > 255) v = 255;
        return v;
    }

    /**
     * 
     * @param v int entre 0 e 255
     */
    public void setR(int v) {
        r = getValorCorrigido(v);
    }

    public void setG(int v) {
        g = getValorCorrigido(v);
    }

    public void setB(int v) {
        b = getValorCorrigido(v);
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    // override - é um indicaditivo de sobreescrita
    // toda classe em java HERDA  de OBJECT e este método existe lá.
    // logo estamos alterando o comportamento do método
    @Override
    public String toString() {
        return "RGB(" + r + ", " + g + ", " + b + ")";
    }
}
