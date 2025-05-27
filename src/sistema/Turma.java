package sistema;

import sistema.enums.TipoTurma;
import java.io.Serializable;
import java.util.*;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private Disciplina disciplina;
    private Professor professor;
    private int vagas;
    private String semestre;
    private int formulaAvaliacao; // 1 = padrão, 2 = ponderada (exemplo)
    private TipoTurma tipoTurma;

    // Sempre inicialize as coleções!
    protected List<Aluno> alunosMatriculados = new ArrayList<>();
    protected Map<Aluno, Integer> faltas = new HashMap<>();
    protected Map<String, Double> notas = new HashMap<>(); // chave: "matricula_tipoNota", ex: "12345_P1"

    public Turma(String codigo, Disciplina disciplina, Professor professor, int vagas,
                 String semestre, int formulaAvaliacao, TipoTurma tipoTurma) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.vagas = vagas;
        this.semestre = semestre;
        this.formulaAvaliacao = formulaAvaliacao;
        this.tipoTurma = tipoTurma;
        this.alunosMatriculados = new ArrayList<>();
        this.faltas = new HashMap<>();
        this.notas = new HashMap<>();
    }

    // Construtor alternativo para compatibilidade (sem tipoTurma)
    public Turma(String codigo, Disciplina disciplina, Professor professor, int vagas,
                 String semestre, int formulaAvaliacao) {
        this(codigo, disciplina, professor, vagas, semestre, formulaAvaliacao, null);
    }

    public String getCodigo() {
        return codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public int getVagas() {
        return vagas;
    }

    public String getSemestre() {
        return semestre;
    }

    public int getFormulaAvaliacao() {
        return formulaAvaliacao;
    }

    public TipoTurma getTipoTurma() {
        return tipoTurma;
    }

    public List<Aluno> getAlunosMatriculados() {
        if (alunosMatriculados == null)
            alunosMatriculados = new ArrayList<>();
        return alunosMatriculados;
    }

    public Map<String, Double> getNotas() {
        if (notas == null)
            notas = new HashMap<>();
        return notas;
    }

    public Map<Aluno, Integer> getFaltas() {
        if (faltas == null)
            faltas = new HashMap<>();
        return faltas;
    }

    // Lançar nota (por tipo)
    public void lancarNota(Aluno aluno, String tipoNota, double nota) {
        if (aluno instanceof AlunoEspecial && !((AlunoEspecial) aluno).isRecebeNota()) {
            return;
        }
        getNotas().put(aluno.getMatricula() + "_" + tipoNota, nota);
    }

    // Registrar falta
    public void registrarFalta(Aluno aluno) {
        getFaltas().put(aluno, getFaltas().getOrDefault(aluno, 0) + 1);
    }

    // Matricular aluno
    public boolean matricularAluno(Aluno aluno) {
        if (getAlunosMatriculados().size() >= vagas) {
            System.out.println("Turma cheia!");
            return false;
        }
        if (getAlunosMatriculados().contains(aluno)) {
            System.out.println("Aluno já matriculado nesta turma!");
            return false;
        }
        getAlunosMatriculados().add(aluno);
        return true;
    }

    // Calcular média do aluno
    public double calcularMedia(Aluno aluno) {
        if (aluno instanceof AlunoEspecial && !((AlunoEspecial) aluno).isRecebeNota()) {
            return -1;
        }
        Double p1 = getNotas().get(aluno.getMatricula() + "_P1");
        Double p2 = getNotas().get(aluno.getMatricula() + "_P2");
        Double p3 = getNotas().get(aluno.getMatricula() + "_P3");
        Double listas = getNotas().get(aluno.getMatricula() + "_L");
        Double seminario = getNotas().get(aluno.getMatricula() + "_S");

        // Exemplo de média padrão
        double soma = 0;
        int count = 0;
        if (p1 != null) { soma += p1; count++; }
        if (p2 != null) { soma += p2; count++; }
        if (p3 != null) { soma += p3; count++; }
        if (listas != null) { soma += listas; count++; }
        if (seminario != null) { soma += seminario; count++; }

        if (count == 0) return 0;

        if (formulaAvaliacao == 2) {
            // Exemplo ponderada: P1(30%), P2(30%), P3(20%), Listas(10%), Seminário(10%)
            double media = 0;
            double pesoTotal = 0;
            if (p1 != null) { media += p1 * 0.3; pesoTotal += 0.3; }
            if (p2 != null) { media += p2 * 0.3; pesoTotal += 0.3; }
            if (p3 != null) { media += p3 * 0.2; pesoTotal += 0.2; }
            if (listas != null) { media += listas * 0.1; pesoTotal += 0.1; }
            if (seminario != null) { media += seminario * 0.1; pesoTotal += 0.1; }
            if (pesoTotal == 0) return 0;
            return media / pesoTotal;
        } else {
            // Média simples
            return soma / count;
        }
    }

    // Verificar aprovação
    public boolean verificarAprovacao(Aluno aluno) {
        double media = calcularMedia(aluno);
        int numFaltas = getFaltas().getOrDefault(aluno, 0);
        int maxFaltas = 0;
        if (disciplina != null && disciplina.getTotalAulas() > 0) {
            maxFaltas = (int) Math.floor(disciplina.getTotalAulas() * 0.25);
        }
        return media >= 6.0 && numFaltas <= maxFaltas;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", codigo, disciplina != null ? disciplina.getNome() : "Disciplina", professor != null ? professor.getNome() : "Professor");
    }
}