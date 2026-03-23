package composicao2;

public class Main {
    public static void main(String[] args) {
        
        Empresa matriz = new Empresa("XPTO");
        System.out.println(matriz);
        try {
            Empresa filial = matriz.addFilial(new Empresa("XPTOzin"));
            System.out.println(filial);

            Empresa filial2 = matriz.addFilial(new Empresa("Betazin"));
            System.out.println(filial2);

            filial.addFilial(new Empresa("filial da filial"));
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }

        // matriz = null;
        // verificar se filial existe
    }
}
