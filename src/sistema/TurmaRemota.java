package sistema;

import sistema.enums.TipoTurma;

public class TurmaRemota extends Turma {
    private static final long serialVersionUID = 1L;
    private String link;

    public TurmaRemota(String codigo, Disciplina disciplina, Professor professor,
                      int vagas, String semestre, int formulaAvaliacao, String link) {
        super(codigo, disciplina, professor, vagas, semestre, formulaAvaliacao, TipoTurma.REMOTA);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // ... resto do código (se necessário)
}