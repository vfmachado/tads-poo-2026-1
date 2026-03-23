package agregacao;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    private String nome;
    private List<Aluno> alunos;
    private Professor professor;

    public Turma(String nome) {
       this.nome = nome;
       this.alunos = new ArrayList<Aluno>();
    }

    public void associarProfessor(Professor p1) {
        this.professor = p1;
    }

    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return "Turma: " + nome + "\nProfessor: " + professor + "\nAlunos: " + alunos;
    }

}
