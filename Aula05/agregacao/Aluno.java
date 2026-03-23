package agregacao;

public class Aluno {
    private String nome;

    public Aluno(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
