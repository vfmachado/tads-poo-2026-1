package Ex04;

public class SessaoVotacao {
    
    private boolean aberta = false;
    private int sim = 0;
    private int nao = 0;

    public void abrir() {
        aberta = true;
    }

    public void fechar() {
        aberta = false;
    }

    public boolean estaAberta() {
        return aberta;
    }

    public void votarSim() {
        if (aberta) {
            sim++;
        }
    }

    public int sim() {
        return sim;
    }

    public void votarNao() {
        if (aberta) {
            nao++;
        }
    }

    public int nao() {
        return nao;
    }

    public int total() {
        return sim + nao;
    }
}
