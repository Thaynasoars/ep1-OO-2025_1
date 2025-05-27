package sistema;

import sistema.utils.ArquivoUtil;
import sistema.utils.RelatorioAcademico;
import java.util.*;
import java.io.File;

public class Main {
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();
        verificarArquivos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SISTEMA ACADÊMICO FCTE ===");
            System.out.println("Dados atuais:");
            System.out.printf("Alunos: %d | Disciplinas: %d | Professores: %d | Turmas: %d\n",
                alunos.size(), disciplinas.size(), professores.size(), turmas.size());
            System.out.println("\nMenu Principal:");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Disciplina");
            System.out.println("3. Cadastrar Professor");
            System.out.println("4. Criar Turma");
            System.out.println("5. Matricular Aluno");
            System.out.println("6. Vincular Pré-requisito");
            System.out.println("7. Lançar Notas/Faltas");
            System.out.println("8. Gerar Relatórios");
            System.out.println("9. Listar Todos os Dados");
            System.out.println("10. Listar Alunos Especiais");
            System.out.println("11. Gerar Boletim de Aluno");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> cadastrarAluno(scanner);
                    case 2 -> cadastrarDisciplina(scanner);
                    case 3 -> cadastrarProfessor(scanner);
                    case 4 -> criarTurma(scanner);
                    case 5 -> matricularAluno(scanner);
                    case 6 -> vincularPreRequisito(scanner);
                    case 7 -> lancarNotasFaltas(scanner);
                    case 8 -> gerarRelatorios(scanner);
                    case 9 -> listarDados();
                    case 10 -> listarAlunosEspeciais();
                    case 11 -> gerarBoletimAluno(scanner);
                    case 0 -> {
                        salvarDados();
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números!");
            }
        }
    }

    private static void verificarArquivos() {
        File[] arquivos = {
            new File("alunos.dat"),
            new File("disciplinas.dat"),
            new File("professores.dat"),
            new File("turmas.dat")
        };

        System.out.println("\n=== ARQUIVOS EXISTENTES ===");
        for (File arquivo : arquivos) {
            System.out.println(arquivo.getName() + ": " +
                (arquivo.exists() ? "Encontrado" : "Não encontrado"));
        }
    }

    private static void carregarDados() {
        System.out.println("\n=== INICIALIZANDO SISTEMA ===");
        try {
            alunos = (List<Aluno>) ArquivoUtil.carregarDados("alunos.dat");
            disciplinas = (List<Disciplina>) ArquivoUtil.carregarDados("disciplinas.dat");
            professores = (List<Professor>) ArquivoUtil.carregarDados("professores.dat");
            turmas = (List<Turma>) ArquivoUtil.carregarDados("turmas.dat");

            System.out.println("Dados carregados com sucesso!");
            System.out.println("Alunos: " + (alunos != null ? alunos.size() : 0));
            System.out.println("Disciplinas: " + (disciplinas != null ? disciplinas.size() : 0));
            System.out.println("Professores: " + (professores != null ? professores.size() : 0));
            System.out.println("Turmas: " + (turmas != null ? turmas.size() : 0));
        } catch (Exception e) {
            System.out.println("Arquivos não encontrados. Iniciando com dados vazios.");
            System.out.println("Motivo: " + e.getMessage());
        } finally {
            if (alunos == null) alunos = new ArrayList<>();
            if (disciplinas == null) disciplinas = new ArrayList<>();
            if (professores == null) professores = new ArrayList<>();
            if (turmas == null) turmas = new ArrayList<>();
        }
    }

    private static void salvarDados() {
        try {
            System.out.println("\nSalvando dados...");
            ArquivoUtil.salvarDados("alunos.dat", alunos);
            ArquivoUtil.salvarDados("disciplinas.dat", disciplinas);
            ArquivoUtil.salvarDados("professores.dat", professores);
            ArquivoUtil.salvarDados("turmas.dat", turmas);
            System.out.println("Dados salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cadastrarAluno(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE ALUNO ---");
        System.out.println("1. Aluno Regular");
        System.out.println("2. Aluno Especial");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        if (nome.isBlank() || matricula.isBlank()) {
            System.out.println("Erro: Nome e matrícula não podem ser vazios!");
            return;
        }

        if (alunos.stream().anyMatch(a -> a.getMatricula().equals(matricula))) {
            System.out.println("Erro: Matrícula já cadastrada!");
            return;
        }

        if (tipo == 1) {
            alunos.add(new AlunoRegular(nome, matricula));
            System.out.println("Aluno regular cadastrado com sucesso!");
        } else if (tipo == 2) {
            System.out.print("Recebe notas? (S/N): ");
            boolean recebeNota = scanner.nextLine().equalsIgnoreCase("S");
            alunos.add(new AlunoEspecial(nome, matricula, recebeNota));
            System.out.println("Aluno especial cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void cadastrarDisciplina(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE DISCIPLINA ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Total de aulas: ");
        int totalAulas = Integer.parseInt(scanner.nextLine());

        if (codigo.isBlank() || nome.isBlank()) {
            System.out.println("Erro: Código e nome não podem ser vazios!");
            return;
        }

        if (disciplinas.stream().anyMatch(d -> d.getCodigo().equalsIgnoreCase(codigo))) {
            System.out.println("Erro: Código de disciplina já cadastrado!");
            return;
        }

        Disciplina disciplina = new Disciplina(codigo, nome);
        disciplina.setTotalAulas(totalAulas);
        disciplinas.add(disciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE PROFESSOR ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        if (nome.isBlank() || matricula.isBlank()) {
            System.out.println("Erro: Nome e matrícula não podem ser vazios!");
            return;
        }

        if (professores.stream().anyMatch(p -> p.getMatricula().equals(matricula))) {
            System.out.println("Erro: Matrícula já cadastrada!");
            return;
        }

        professores.add(new Professor(nome, matricula));
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void criarTurma(Scanner scanner) {
        System.out.println("\n--- CRIAÇÃO DE TURMA ---");

        if (disciplinas.isEmpty() || professores.isEmpty()) {
            System.out.println("Cadastre disciplinas e professores primeiro!");
            return;
        }

        try {
            System.out.println("Disciplinas disponíveis:");
            for (int i = 0; i < disciplinas.size(); i++) {
                System.out.println(i + " - " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")");
            }
            System.out.print("Escolha a disciplina: ");
            int discIndex = Integer.parseInt(scanner.nextLine());

            if (discIndex < 0 || discIndex >= disciplinas.size()) {
                System.out.println("Índice inválido!");
                return;
            }

            System.out.println("Professores disponíveis:");
            for (int i = 0; i < professores.size(); i++) {
                System.out.println(i + " - " + professores.get(i).getNome());
            }
            System.out.print("Escolha o professor: ");
            int profIndex = Integer.parseInt(scanner.nextLine());

            if (profIndex < 0 || profIndex >= professores.size()) {
                System.out.println("Índice inválido!");
                return;
            }

            System.out.print("Código da Turma: ");
            String codigo = scanner.nextLine();

            System.out.print("Vagas: ");
            int vagas = Integer.parseInt(scanner.nextLine());

            System.out.print("Semestre (ex: 2023.2): ");
            String semestre = scanner.nextLine();

            System.out.print("Fórmula de avaliação (1-Padrão / 2-Ponderada): ");
            int formula = Integer.parseInt(scanner.nextLine());

            System.out.print("Tipo (1-Presencial / 2-Remota): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            if (tipo == 1) {
                System.out.print("Sala: ");
                String sala = scanner.nextLine();
                turmas.add(new TurmaPresencial(
                    codigo, disciplinas.get(discIndex), professores.get(profIndex),
                    vagas, semestre, formula, sala
                ));
            } else if (tipo == 2) {
                System.out.print("Link da aula: ");
                String link = scanner.nextLine();
                turmas.add(new TurmaRemota(
                    codigo, disciplinas.get(discIndex), professores.get(profIndex),
                    vagas, semestre, formula, link
                ));
            } else {
                System.out.println("Tipo inválido!");
                return;
            }
            System.out.println("Turma criada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números válidos!");
        }
    }

    private static void matricularAluno(Scanner scanner) {
        System.out.println("\n--- MATRÍCULA DE ALUNO ---");

        if (alunos.isEmpty() || turmas.isEmpty()) {
            System.out.println("Cadastre alunos e turmas primeiro!");
            return;
        }

        try {
            System.out.println("Alunos disponíveis:");
            for (int i = 0; i < alunos.size(); i++) {
                Aluno aluno = alunos.get(i);
                System.out.printf("%d - %s (%s)%n",
                    i,
                    aluno.getNome(),
                    aluno instanceof AlunoEspecial ? "Especial" : "Regular");
            }
            System.out.print("Escolha o aluno: ");
            int alunoIndex = Integer.parseInt(scanner.nextLine());
            Aluno aluno = alunos.get(alunoIndex);

            if (aluno instanceof AlunoEspecial) {
                AlunoEspecial especial = (AlunoEspecial) aluno;
                if (especial.getDisciplinasCursadas().size() >= AlunoEspecial.MAX_DISCIPLINAS) {
                    System.out.println("Este aluno especial já está no limite de " +
                            AlunoEspecial.MAX_DISCIPLINAS + " disciplinas!");
                    return;
                }
            }

            System.out.println("Turmas disponíveis:");
            for (int i = 0; i < turmas.size(); i++) {
                Turma t = turmas.get(i);
                System.out.println(i + " - " + t.getCodigo() +
                        " (" + t.getDisciplina().getNome() + ") - Vagas: " +
                        (t.getVagas() - t.getAlunosMatriculados().size()));
            }
            System.out.print("Escolha a turma: ");
            int turmaIndex = Integer.parseInt(scanner.nextLine());
            Turma turma = turmas.get(turmaIndex);

            if (turma.matricularAluno(aluno)) {
                System.out.println("Matrícula realizada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void vincularPreRequisito(Scanner scanner) {
        if (disciplinas.size() < 2) {
            System.out.println("Cadastre pelo menos 2 disciplinas primeiro!");
            return;
        }

        System.out.println("\n--- VINCULAR PRÉ-REQUISITO ---");
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println(i + " - " + disciplinas.get(i).getNome() + " (" + disciplinas.get(i).getCodigo() + ")");
        }

        try {
            System.out.print("Escolha a disciplina que terá pré-requisito: ");
            int indexDisciplina = Integer.parseInt(scanner.nextLine());
            Disciplina disciplinaAlvo = disciplinas.get(indexDisciplina);

            System.out.print("Escolha a disciplina pré-requisito: ");
            int indexPreReq = Integer.parseInt(scanner.nextLine());
            Disciplina preRequisito = disciplinas.get(indexPreReq);

            if (indexDisciplina == indexPreReq) {
                System.out.println("Erro: Uma disciplina não pode ser pré-requisito de si mesma!");
                return;
            }

            if (disciplinaAlvo.getPreRequisitos().contains(preRequisito)) {
                System.out.println("Este pré-requisito já está vinculado!");
                return;
            }

            disciplinaAlvo.adicionarPreRequisito(preRequisito);
            System.out.println("Pré-requisito vinculado com sucesso!");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido! Digite um número entre 0 e " + (disciplinas.size() - 1));
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números válidos!");
        }
    }

    private static void lancarNotasFaltas(Scanner scanner) {
        System.out.println("\n--- LANÇAR NOTAS/FALTAS ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada!");
            return;
        }

        try {
            System.out.println("Turmas disponíveis:");
            for (int i = 0; i < turmas.size(); i++) {
                System.out.println(i + " - " + turmas.get(i).getCodigo() + " (" + turmas.get(i).getDisciplina().getNome() + ")");
            }
            System.out.print("Escolha a turma: ");
            int turmaIndex = Integer.parseInt(scanner.nextLine());
            Turma turma = turmas.get(turmaIndex);

            if (turma.getAlunosMatriculados().isEmpty()) {
                System.out.println("Nenhum aluno matriculado nesta turma!");
                return;
            }

            System.out.println("Alunos matriculados:");
            for (int i = 0; i < turma.getAlunosMatriculados().size(); i++) {
                System.out.println(i + " - " + turma.getAlunosMatriculados().get(i).getNome());
            }
            System.out.print("Escolha o aluno: ");
            int alunoIndex = Integer.parseInt(scanner.nextLine());
            Aluno aluno = turma.getAlunosMatriculados().get(alunoIndex);

            if (aluno instanceof AlunoEspecial && !((AlunoEspecial) aluno).isRecebeNota()) {
                System.out.print("Registrar falta? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    turma.registrarFalta(aluno);
                    System.out.println("Falta registrada!");
                }
                return;
            }

            // Tipos de avaliação
            System.out.println("\nTipos de avaliação:");
            System.out.println("1 - Prova 1 (P1)");
            System.out.println("2 - Prova 2 (P2)");
            System.out.println("3 - Prova 3 (P3)");
            System.out.println("4 - Listas (L)");
            System.out.println("5 - Seminário (S)");
            System.out.print("Escolha o tipo: ");
            int tipoAvaliacao = Integer.parseInt(scanner.nextLine());

            String tipo = "";
            switch(tipoAvaliacao) {
                case 1: tipo = "P1"; break;
                case 2: tipo = "P2"; break;
                case 3: tipo = "P3"; break;
                case 4: tipo = "L";  break;
                case 5: tipo = "S";  break;
                default:
                    System.out.println("Tipo de avaliação inválido!");
                    return;
            }

            System.out.print("Nota (0-10): ");
            double nota = Double.parseDouble(scanner.nextLine());
            turma.lancarNota(aluno, tipo, nota);

            System.out.print("Registrar falta? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                turma.registrarFalta(aluno);
            }

            System.out.println("Operação concluída!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void gerarRelatorios(Scanner scanner) {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Desempenho por Turma");
        System.out.println("2. Disciplinas com Pré-requisitos");
        System.out.println("3. Alunos por Situação");
        System.out.print("Opção: ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> relatorioTurma(scanner);
                case 2 -> relatorioPreRequisitos();
                case 3 -> relatorioSituacaoAlunos();
                default -> System.out.println("Opção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números!");
        }
    }

    private static void relatorioTurma(Scanner scanner) {
        System.out.println("\n--- RELATÓRIO DE TURMA ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada!");
            return;
        }

        System.out.println("Turmas disponíveis:");
        for (int i = 0; i < turmas.size(); i++) {
            System.out.println(i + " - " + turmas.get(i).getCodigo() + " (" + turmas.get(i).getDisciplina().getNome() + ")");
        }
        System.out.print("Escolha a turma: ");
        int turmaIndex = Integer.parseInt(scanner.nextLine());
        Turma turma = turmas.get(turmaIndex);

        System.out.println("\n=== RELATÓRIO DA TURMA " + turma.getCodigo() + " ===");
        System.out.println("Disciplina: " + turma.getDisciplina().getNome());
        System.out.println("Professor: " + turma.getProfessor().getNome());
        System.out.println("Vagas: " + turma.getVagas() + " | Matriculados: " + turma.getAlunosMatriculados().size());
        System.out.println("Tipo: " + (turma instanceof TurmaPresencial ? "Presencial" : "Remota"));
        System.out.println("Semestre: " + turma.getSemestre());
        System.out.println("Fórmula de avaliação: " + turma.getFormulaAvaliacao());

        if (turma.getAlunosMatriculados().isEmpty()) {
            System.out.println("\nNenhum aluno matriculado nesta turma.");
        } else {
            System.out.println("\nALUNOS MATRICULADOS:");
            System.out.println("Nome | Matrícula | Média | Faltas | Situação");
            System.out.println("------------------------------------------");

            for (Aluno aluno : turma.getAlunosMatriculados()) {
                double media = turma.calcularMedia(aluno);
                int faltas = turma.getFaltas().getOrDefault(aluno, 0);
                String situacao = turma.verificarAprovacao(aluno) ? "Aprovado" : "Reprovado";
                System.out.printf("%s | %s | %.1f | %d | %s\n",
                        aluno.getNome(),
                        aluno.getMatricula(),
                        media,
                        faltas,
                        situacao);
            }
        }
    }

    private static void relatorioPreRequisitos() {
        System.out.println("\n--- DISCIPLINAS COM PRÉ-REQUISITOS ---");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada!");
            return;
        }

        boolean encontrou = false;
        for (Disciplina d : disciplinas) {
            if (d.getPreRequisitos() != null && !d.getPreRequisitos().isEmpty()) {
                System.out.print(d.getCodigo() + " - " + d.getNome() + ": ");
                d.getPreRequisitos().forEach(p -> System.out.print(p.getCodigo() + " "));
                System.out.println();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma disciplina com pré-requisitos cadastrados.");
        }
    }

    private static void relatorioSituacaoAlunos() {
        System.out.println("\n--- ALUNOS POR SITUAÇÃO ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
            return;
        }

        System.out.println("ALUNOS:");
        System.out.println("Nome | Matrícula | Situação");
        System.out.println("---------------------------");
        alunos.forEach(a -> {
            String situacao = (a.getSituacao() != null) ? a.getSituacao().toString() : "-";
            System.out.printf("%s | %s | %s\n",
                    a.getNome(),
                    a.getMatricula(),
                    situacao);
        });
    }

    private static void listarDados() {
        System.out.println("\n=== LISTAGEM COMPLETA ===");

        System.out.println("\nALUNOS (" + alunos.size() + "):");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado");
        } else {
            alunos.forEach(a -> System.out.println(
                    "Nome: " + a.getNome() +
                            " | Matrícula: " + a.getMatricula() +
                            " | Status: " + ((a.getSituacao() != null) ? a.getSituacao().toString() : "-")));
        }

        System.out.println("\nDISCIPLINAS (" + disciplinas.size() + "):");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada");
        } else {
            disciplinas.forEach(d -> {
                System.out.print("Código: " + d.getCodigo() +
                        " | Nome: " + d.getNome() +
                        " | Aulas: " + d.getTotalAulas());
                if (d.getPreRequisitos() != null && !d.getPreRequisitos().isEmpty()) {
                    System.out.print(" | Pré-requisitos: ");
                    d.getPreRequisitos().forEach(p -> System.out.print(p.getCodigo() + " "));
                }
                System.out.println();
            });
        }

        System.out.println("\nPROFESSORES (" + professores.size() + "):");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado");
        } else {
            professores.forEach(p -> System.out.println(
                    "Nome: " + p.getNome() +
                            " | Matrícula: " + p.getMatricula()));
        }

        System.out.println("\nTURMAS (" + turmas.size() + "):");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada");
        } else {
            turmas.forEach(t -> {
                System.out.print(
                        "Código: " + t.getCodigo() +
                                " | Disciplina: " + t.getDisciplina().getNome() +
                                " | Professor: " + t.getProfessor().getNome() +
                                " | Vagas: " + t.getVagas() +
                                " | Matriculados: " + t.getAlunosMatriculados().size() +
                                " | Tipo: " + (t instanceof TurmaPresencial ? "Presencial" : "Remota") +
                                " | Semestre: " + t.getSemestre() +
                                " | Fórmula: " + t.getFormulaAvaliacao()
                );
                System.out.println();
            });
        }
    }

    private static void listarAlunosEspeciais() {
        System.out.println("\n=== ALUNOS ESPECIAIS ===");
        System.out.printf("%-20s %-15s %-10s %-15s\n",
                "Nome", "Matrícula", "Recebe Notas", "Disciplinas");
        System.out.println("--------------------------------------------------");

        alunos.stream()
                .filter(a -> a instanceof AlunoEspecial)
                .map(a -> (AlunoEspecial) a)
                .forEach(esp -> {
                    System.out.printf("%-20s %-15s %-10s %d/%d\n",
                            esp.getNome(),
                            esp.getMatricula(),
                            esp.isRecebeNota() ? "Sim" : "Não",
                            esp.getDisciplinasCursadas().size(),
                            AlunoEspecial.MAX_DISCIPLINAS);
                });
    }

    // Novo método para gerar boletim do aluno
    private static void gerarBoletimAluno(Scanner scanner) {
        System.out.print("Mostrar detalhes da turma? (S/N): ");
        boolean detalhes = scanner.nextLine().equalsIgnoreCase("S");

        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i).getNome());
        }
        System.out.print("Escolha o aluno: ");
        Aluno aluno = alunos.get(Integer.parseInt(scanner.nextLine()));

        RelatorioAcademico.gerarBoletimAluno(aluno, turmas, detalhes);
    }
}