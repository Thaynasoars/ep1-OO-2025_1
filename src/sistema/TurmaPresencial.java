package sistema;

import sistema.enums.TipoTurma;

public class TurmaPresencial extends Turma {
    private static final long serialVersionUID = 1L;
    private String sala;

    public TurmaPresencial(String codigo, Disciplina disciplina, Professor professor,
                         int vagas, String semestre, int formulaAvaliacao, String sala) {
        super(codigo, disciplina, professor, vagas, semestre, formulaAvaliacao, TipoTurma.PRESENCIAL);
        this.sala = sala;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    // ... resto do código (se necessário)
}