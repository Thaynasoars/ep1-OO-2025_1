package sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nome;
    private int totalAulas;
    private List<Disciplina> preRequisitos;

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.preRequisitos = new ArrayList<>();
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public void adicionarPreRequisito(Disciplina disciplina) {
        if (!preRequisitos.contains(disciplina)) {
            preRequisitos.add(disciplina);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina that = (Disciplina) obj;
        return codigo.equalsIgnoreCase(that.codigo);
    }
    
    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}