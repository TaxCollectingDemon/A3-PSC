import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Working dir = " + System.getProperty("user.dir"));

        Niveis niveis = new Niveis();
        try (
            FileReader arquivoScan = new FileReader("JogoArquivos-texto\\modelo-cenarios.txt");
            BufferedReader arquivo = new BufferedReader(arquivoScan)
        ) {
            StringBuilder conteudo = new StringBuilder();
            String line = arquivo.readLine();
            while (line != null) {
                conteudo.append(line).append("\n");
                line = arquivo.readLine();
            }
            niveis.importarNiveis(conteudo.toString());

            System.out.println(niveis.getListaNiveis()[0].toString());
            System.out.println(niveis.getListaNiveis()[0].getCenarios()[0].toString());
            System.out.println(niveis.getListaNiveis()[0].getCenarios()[0].getRespostas()[0].toString());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}