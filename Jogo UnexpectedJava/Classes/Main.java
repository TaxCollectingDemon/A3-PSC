import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Niveis niveis = new Niveis();
        File referencia = new File("referencia.txt");
        String referenciaPath = referencia.getAbsolutePath();
        System.out.println(referenciaPath);
        referenciaPath = referenciaPath.replace("referencia.txt", "Arquivos de texto\\Modelo escrita de cenarios.txt");
        System.out.println(referenciaPath);

        File arquivo = new File(referenciaPath);


        niveis.importarNiveis("Nivel 1;\r\n" + //
                        "Cenario 1: Capitulo 1;\r\n" + //
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sagittis mauris ac commodo sodales. Morbi pulvinar nisi sit amet tempus laoreet. Phasellus tempus congue sem, sit amet faucibus dui lacinia id. Vestibulum feugiat nunc at metus eleifend, nec laoreet est ullamcorper. Donec et sagittis est. Ut efficitur tellus ut odio tempor, quis ullamcorper arcu accumsan. Suspendisse pharetra ante dui, nec vulputate justo aliquet a.\r\n" + //
                        "\r\n" + //
                        "Resposta 1: Incorreta\r\n" + //
                        "Astra e Neo fazem x\r\n" + //
                        "Conclusao\r\n" + //
                        "O robô quebra tudo.\r\n" + //
                        "\r\n" + //
                        "Resposta 2: Correta\r\n" + //
                        "Astra e Neo fazem y\r\n" + //
                        "Conclusao\r\n" + //
                        "O robô é consertado\r\n" + //
                        "\r\n" + //
                        "Cenario 2: Capitulo 2;\r\n" + //
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sagittis mauris ac commodo sodales. Morbi pulvinar nisi sit amet tempus laoreet. Phasellus tempus congue sem, sit amet faucibus dui lacinia id. Vestibulum feugiat nunc at metus eleifend, nec laoreet est ullamcorper. Donec et sagittis est. Ut efficitur tellus ut odio tempor, quis ullamcorper arcu accumsan. Suspendisse pharetra ante dui, nec vulputate justo aliquet a.\r\n" + //
                        "\r\n" + //
                        "Resposta 1: Correta\r\n" + //
                        "Astra e Neo fazem x\r\n" + //
                        "Conclusao\r\n" + //
                        "O robô é consertado\r\n" + //
                        "\r\n" + //
                        "Resposta 2: Incorreta\r\n" + //
                        "Astra e Neo fazem y\r\n" + //
                        "Conclusao\r\n" + //
                        "O robô quebra tudo\r\n" + //
                        "");

        System.out.println(niveis.getListaNiveis()[0].toString());                   
        System.out.println(niveis.getListaNiveis()[0].getCenarios()[0].toString());                
        System.out.println(niveis.getListaNiveis()[0].getCenarios()[0].getRespostas()[0].toString());
    }
}