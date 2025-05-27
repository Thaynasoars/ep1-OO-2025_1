package sistema;

import java.io.Serializable;
import java.util.List;

public class AlunoEspecial extends Aluno implements Serializable {
    private static final long serialVersionUID = 2L;
    public static final int MAX_DISCIPLINAS = 2;
    private boolean recebeNota;

    public AlunoEspecial(String nome, String matricula, boolean recebeNota) {
        super(nome, matricula);
        this.recebeNota = recebeNota;
    }

    @Override
    public boolean podeMatricular(Disciplina disciplina) {
        return getDisciplinasCursadas().size() < MAX_DISCIPLINAS;
    }

    @Override
    public List<Disciplina> getDisciplinasCursadas() {
        return super.disciplinasCursadas;
    }

    @Override
    public boolean cumpriuPreRequisitos(List<Disciplina> preRequisitos) {
        return true; // Alunos especiais ignoram pré-requisitos
    }

    @Override
    public void adicionarDisciplinaCursada(Disciplina disciplina) {
        if (!getDisciplinasCursadas().contains(disciplina)) {
            getDisciplinasCursadas().add(disciplina);
        }
    }

    public boolean isRecebeNota() {
        return recebeNota;
    }
}