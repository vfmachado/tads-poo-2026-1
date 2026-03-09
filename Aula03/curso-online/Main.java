public class Main {

    public static void main(String[] args) {
        
        Curso poo = new Curso("POO IFRS", 99.9f);
        poo.setSlogan("Do basico ao avançado com 9 projetos");

        poo.addAula(new Aula("Introducao Abstracao", 3.0f));
        poo.addAula(new Aula("Getters Setters & Construtores", 2.0f));
        
        Aula aula03 = new Aula("03", 1);
        poo.addAula(aula03);

        System.out.println(poo.getTotalAulas()); // 3
        System.out.println(poo.getTotalCH()); // 6

        // poo.removerAula(aula03);
        // System.out.println(poo.getTotalAulas()); // 2
        // System.out.println(poo.getTotalCH()); // 5

        System.out.println(poo);    // titulo + slogan
        poo.setSlogan(" outro slogan");

        poo.setTitulo("ab"); // nao pode ter menos que 7
        System.out.println(poo);    // titulo + slogan
    }

}