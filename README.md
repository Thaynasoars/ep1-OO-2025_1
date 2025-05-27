# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** [Evelyn Thayná de Almeida Soares]
- **Matrícula:** [241025757]
- **Curso:** [engenharias]
- **Turma:** [Turma 02]

---

## Instruções para Compilação e Execução

1. **Compilação:**  
javac -d bin src/sistema/Main.java

2. **Execução:**  
java -cp bin sistema.Main

3. **Estrutura de Pastas:**  
   [SistemaAcademicoFCTE/
├── src/
│   ├── sistema/
│   │   ├── Aluno.java
│   │   ├── AlunoEspecial.java
│   │   ├── AlunoRegular.java
│   │   ├── Avaliacao.java
│   │   ├── Disciplina.java
│   │   ├── Main.java
│   │   ├── Professor.java
│   │   ├── Turma.java
│   │   ├── TurmaPresencial.java
│   │   └── TurmaRemota.java
│   ├── sistema.enums/
│   │   ├── SituacaoAluno.java
│   │   └── TipoTurma.java
│   └── sistema.utils/
│       ├── ArquivoUtil.java
│       └── RelatorioAcademico.java
├── module-info.java
├── alunos.csv
├── disciplinas.csv
├── notas.csv
├── professores.csv
├── relatorio_1.txt
└── turmas.csv]

3. **Versão do JAVA utilizada:**  
   java 21

---

## Vídeo de Demonstração

- [Inserir o link para o vídeo no YouTube/Drive aqui]

---

## Prints da Execução

1. Menu Principal:  
   ![![image](https://github.com/user-attachments/assets/887305e8-aa69-4256-9101-5de3f0b427cf)
](caminho/do/print1.pn)

2. Cadastro de Aluno:  
   ![![image](https://github.com/user-attachments/assets/1c5eb12e-9b48-4f34-b9e7-9a4b3ad19204)
](caminho/do/print2.png)

3. Relatório de Frequência/Notas:  
   ![![image](https://github.com/user-attachments/assets/d4480032-db06-466a-944a-98ea21c26343)
](caminho/do/print3.png)

---

## Principais Funcionalidades Implementadas

- [ ] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [*] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [*] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [*] Lançamento de notas e controle de presença
- [*] Cálculo de média final e verificação de aprovação/reprovação
- [*] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [*] Persistência de dados em arquivos (.txt ou .csv)
- [ ] Tratamento de duplicidade de matrículas
- [*] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- [O tempo foi curto devido ao estudo de outras matérias, o que dificultou a dedicação ao projeto.]

---

## Contato

- [evelyntha6@gmail.com]
