package sistema.utils;

import java.io.*;
import java.util.List;

public class ArquivoUtil {
    // Método para salvar dados em formato binário (serialização)
    public static void salvarDados(String arquivo, Object dados) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(dados);
        }
    }

    // Método para carregar dados em formato binário (desserialização)
    public static Object carregarDados(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return in.readObject();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    // Novo método para exportar lista de objetos para CSV (chama toString() de cada item)
    public static void exportarParaCSV(String arquivo, List<?> dados) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            dados.forEach(writer::println);
        }
    }
}