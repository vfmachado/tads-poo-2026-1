package agregacao;

public class Main {
    public static void main(String[] args) {
        
        Turma turma = new Turma("POO");
        Aluno a1 = new Aluno("Victor");
        Aluno a2 = new Aluno("Christian");
        Aluno a3 = new Aluno("Rafael");
        turma.addAluno(a1);
        turma.addAluno(a2);
        turma.addAluno(a3);

        Professor p1 = new Professor("Vini");
        turma.associarProfessor(p1);

        // System.out.println(turma.getProfessor());
        // System.out.println(turma.getAlunos());

        System.out.println(turma);
    }
}
