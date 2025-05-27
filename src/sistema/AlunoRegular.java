package sistema;

import java.io.Serializable;
import java.util.List;

public class AlunoRegular extends Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    public AlunoRegular(String nome, String matricula) {
        super(nome, matricula);
    }

    @Override
    public boolean podeMatricular(Disciplina disciplina) {
        return cumpriuPreRequisitos(disciplina.getPreRequisitos());
    }

    @Override
    public List<Disciplina> getDisciplinasCursadas() {
        return super.disciplinasCursadas;
    }

    @Override
    public boolean cumpriuPreRequisitos(List<Disciplina> preRequisitos) {
        return preRequisitos == null || 
               preRequisitos.isEmpty() || 
               getDisciplinasAprovadas().containsAll(preRequisitos);
    }

    @Override
    public void adicionarDisciplinaCursada(Disciplina disciplina) {
        if (!getDisciplinasCursadas().contains(disciplina)) {
            getDisciplinasCursadas().add(disciplina);
        }
    }
}