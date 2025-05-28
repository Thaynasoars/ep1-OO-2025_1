# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** Evelyn Thayná de Almeida Soares
- **Matrícula:** 241025757
- **Curso:** engenharias
- **Turma:** Turma 02

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
   ![Captura de tela 2025-05-26 231127](https://github.com/user-attachments/assets/90d2d287-5af1-45d3-8dc9-754ff67b0bb5)

2. Cadastro de Aluno:  
 ![Captura de tela 2025-05-26 231410](https://github.com/user-attachments/assets/d0636b76-53e3-4693-a940-41fe2849f100)

3. Relatório de Frequência/Notas:  
![Captura de tela 2025-05-26 232118](https://github.com/user-attachments/assets/5c937f83-f1fb-4926-88ea-4fb5411b0e11)


## Principais Funcionalidades Implementadas

- [*] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [*] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [*] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [*] Lançamento de notas e controle de presença
- [*] Cálculo de média final e verificação de aprovação/reprovação
- [*] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [ ] Persistência de dados em arquivos (.txt ou .csv)
- [*] Tratamento de duplicidade de matrículas
- [*] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- Infelizmente, não consegui me dedicar ao projeto como gostaria, pois tive que dividir meu tempo com o estudo de outras matérias igualmente exigentes. Durante o desenvolvimento, senti uma enorme dificuldade, muitas vezes me sentindo perdida,experiência frustrante em vários momentos, e apesar de ter tentado, não consegui avançar tanto quanto gostaria.



---

## Contato

- [evelyntha6@gmail.com]
