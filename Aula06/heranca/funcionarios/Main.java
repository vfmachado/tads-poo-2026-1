package heranca.funcionarios;

public class Main {
    public static void main(String[] args) {
        
        // Funcionario func = new Funcionario() {
        //     @Override
        //     public String getDepartamento() {
        //         return "Departamento de Vendas";
        //     }
        // };

        Gerente gerente = new Gerente();
        System.out.println(gerente.salarioCalculado());

        FuncionarioSemHeranca func = new FuncionarioSemHeranca("Gerente", 5000);
    }
}
