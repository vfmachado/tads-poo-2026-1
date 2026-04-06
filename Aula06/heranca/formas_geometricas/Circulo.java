package heranca.formas_geometricas;

public class Circulo extends Forma {
    
    private float raio;
    
    public Circulo(float raio) {
        this.raio = raio;
    }
    
    @Override
    public float getArea() {
        return (float) (Math.PI * Math.pow(raio, 2));
    }
    
    @Override
    public float getPerimetro() {
        return (float) (2 * Math.PI * raio);
    }
}
