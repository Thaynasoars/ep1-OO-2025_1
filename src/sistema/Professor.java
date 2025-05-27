package sistema;

import java.io.Serializable;

public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String matricula;

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    // Getters
    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
}