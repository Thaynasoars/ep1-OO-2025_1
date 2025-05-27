package sistema.utils;

import sistema.*;
import java.util.List;

public class RelatorioAcademico {
    
    public static void gerarBoletimAluno(Aluno aluno, List<Turma> turmas, boolean detalhesTurma) {
        System.out.println("\n=== BOLETIM ACADÊMICO ===");
        System.out.println("Aluno: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        
        System.out.println("\nDisciplinas Matriculadas:");
        System.out.println(detalhesTurma ? getHeaderCompleto() : getHeaderSimplificado());
        
        turmas.stream()
            .filter(t -> t.getAlunosMatriculados().contains(aluno))
            .forEach(t -> {
                if (detalhesTurma) {
                    System.out.printf("%-10s %-20s %-15s %-10s %-8s %-10s %-15s\n",
                        t.getDisciplina().getCodigo(),
                        t.getDisciplina().getNome(),
                        t.getProfessor().getNome(),
                        t.getSemestre(),
                        t instanceof TurmaPresencial ? "Presencial" : "Remoto",
                        String.format("%.1f", t.calcularMedia(aluno)),
                        t.verificarAprovacao(aluno) ? "Aprovado" : "Reprovado");
                } else {
                    System.out.printf("%-10s %-20s %-8s %-10s\n",
                        t.getDisciplina().getCodigo(),
                        t.getDisciplina().getNome(),
                        String.format("%.1f", t.calcularMedia(aluno)),
                        t.verificarAprovacao(aluno) ? "Aprovado" : "Reprovado");
                }
            });
    }
    
    private static String getHeaderCompleto() {
        return String.format("%-10s %-20s %-15s %-10s %-8s %-10s %-15s",
            "Código", "Disciplina", "Professor", "Semestre", "Modalidade", "Média", "Situação");
    }
    
    private static String getHeaderSimplificado() {
        return String.format("%-10s %-20s %-8s %-10s",
            "Código", "Disciplina", "Média", "Situação");
    }
    
    // Outros métodos de relatório...
}