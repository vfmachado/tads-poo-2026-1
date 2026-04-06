package heranca.funcionarios;

public class FuncionarioSemHeranca {
    
    private String tipo;
    private float salarioBase;

    FuncionarioSemHeranca(String tipo, float salarioBase) {
        this.tipo = tipo;
        this.salarioBase = salarioBase;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    private float multiplicador() {
        switch (tipo) {
            case "Gerente":
                return 1.3f;
            case "Diretor":
                return 1.5f;
            default:
                return 1.0f;
        }
    }
    
    public float salarioCalculado() {
        return salarioBase * multiplicador();
    }

}
