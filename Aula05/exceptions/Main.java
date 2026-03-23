package exceptions;

public class Main {

    public static int dividir(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return a / b;
    }

    public static boolean verificarIdade(int idade) {
        if (idade < 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        
        try {
            int resultado = Main.dividir(10, 0);
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.err.println("Erro: " + e.getMessage());
        }

        // OBJETIVO DAS EXCEPTION É TRATAR ERROS DE FORMA CONTROLADA, EVITANDO QUE O PROGRAMA QUEBRE DE MANEIRA INESPERADA
        // TAMBEM TEM A QUESTAO SEMANTICA
        if (Main.verificarIdade(-5)) {
            // Lógica para idade válida
        }
    }
}
