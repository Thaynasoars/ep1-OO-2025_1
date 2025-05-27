package sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import sistema.enums.SituacaoAluno;

public abstract class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String nome;
    protected String matricula;
    protected SituacaoAluno situacao;
    protected List<Disciplina> disciplinasCursadas;
    protected List<Disciplina> disciplinasAprovadas;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.situacao = SituacaoAluno.CURSANDO;
        this.disciplinasCursadas = new ArrayList<>();
        this.disciplinasAprovadas = new ArrayList<>();
    }

    // Getters básicos
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public SituacaoAluno getSituacao() { return situacao; }
    
    // Métodos abstratos
    public abstract List<Disciplina> getDisciplinasCursadas();
    public abstract boolean cumpriuPreRequisitos(List<Disciplina> preRequisitos);
    public abstract void adicionarDisciplinaCursada(Disciplina disciplina);
    public abstract boolean podeMatricular(Disciplina disciplina);
    
    public List<Disciplina> getDisciplinasAprovadas() {
        return disciplinasAprovadas;
    }
}