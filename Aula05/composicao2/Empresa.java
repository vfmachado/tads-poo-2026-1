package composicao2;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nome;
    private boolean filial;
    private List<Empresa> filiais;

    public Empresa(String nome) {
        this.nome = nome;
        this.filial = false;
        this.filiais = null;
    }

    public Empresa addFilial(Empresa empresa) throws Exception{
        if (filiais == null && !this.filial) {
            this.filiais = new ArrayList<>();
        }

        if (!this.filial) {
            this.filiais.add(empresa);
            empresa.filial = true;
            return empresa;
        }
        
        throw new Exception("Filiais nao podem ter filiais");
    }

    @Override
    public String toString() {
        if (!filial) {
            return "MATRIZ " + nome;
        } else {
            return "FILIAL " + nome;
        }

    }
}
