package associacao;

public class Main {
    public static void main(String[] args) {
        
        // medico e paciente estao associados, mas um paciente pode existir sem um médico e um médico pode existir sem um paciente
        Paciente p1 = new Paciente("Theo");
        Medico m1 = new Medico("Cris");

        // na classe Consulta - temos uma AGREGACAO, pois a consulta não existe sem o paciente e o médico
        Consulta c1 = new Consulta(p1, m1);
        c1.cancelar(); // paciente e medico nao deixam

        

    }   
}
