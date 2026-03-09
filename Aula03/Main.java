public class Main {
    public static void main(String[] args) {
        
       Nota n1 = new Nota();
       n1.setValor(-9);
       System.out.println(n1.getValor() == 0); // -9 é invalido, tem que virar 0

       // nao podemos acessar diretamente o atributo valor pois este é PRIVADO
    //    Nota n2 = new Nota();
    //    n2.valor = -9;
    //    System.out.println(n2.valor == 0);

        Cor c1 = new Cor();

        c1.setR(255);
        System.out.println(c1); //255, 0 ,0

        c1.setB(100);
        System.out.println(c1); //255, 0 ,100

        c1.setG(200);
        System.out.println(c1); //255, 200 , 100
    }
}