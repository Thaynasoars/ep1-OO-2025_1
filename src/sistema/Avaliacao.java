package sistema;

public class Avaliacao {
    private String nome;
    private double nota;

    public Avaliacao(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }
}

