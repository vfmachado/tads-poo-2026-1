package composicao;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    
    // inner class privada - só pode ser utilizada dentro da propria classe
    private class Comodo {
        String nome;
        
        public Comodo(String nome) {
            this.nome = nome;
        }
    }

    List<Comodo> comodos;

    public Casa() {
        comodos = new ArrayList<Comodo>();
        comodos.add(new Comodo("banheiro"));
        comodos.add(new Comodo("cozinha"));
        comodos.add(new Comodo("quarto"));
    }

    public void mostrarComodos() {
        // mostrar lista de comodos
        System.out.println("CASA: ");
        for (Comodo comodo : comodos) {
            System.out.println(comodo.nome);
        }
    }
}
