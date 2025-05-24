import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Jogo Unexpected Java!");

        
        Thread.sleep(1500);
        
        System.out.println("Carregando os níveis do jogo...");
        Niveis niveis = new Niveis();
        try (
            FileReader arquivoScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\modelo-cenarios.txt");
            BufferedReader arquivo = new BufferedReader(arquivoScan)
        ) {
            StringBuilder conteudo = new StringBuilder();
            String line = arquivo.readLine();
            while (line != null) {
                conteudo.append(line).append("\n");
                line = arquivo.readLine();
            }
            niveis.importarNiveis(conteudo.toString());

            Thread.sleep(2000);

            System.out.println("Níveis carregados com sucesso!");
            System.out.println();

            Thread.sleep(500);
            
            Random random = new Random();
            Pontuacao pontuacao = new Pontuacao();

            for (Nivel nivel : niveis.getListaNiveis()) {

                System.out.println(nivel.getNome());
                System.out.println("Dificuldade: " + nivel.getDificuldade());
                System.out.println();

                Thread.sleep(500);

                Cenario cenario = nivel.getCenarios()[random.nextInt(nivel.getCenarios().length - 1)];
                System.out.println(cenario.getIntroducao());
                System.out.println();
                Thread.sleep(300);
                System.out.println("Capítulo do livro para referência: " + cenario.getCapituloLivro());
                System.out.println();

                Thread.sleep(300);

                Resposta[] respostasRandom = Embaralhar.embaralharRespostas(cenario.getRespostas());

                System.out.println("Respostas:");
                Thread.sleep(100);
                System.out.println("A: " + respostasRandom[0].getResposta());
                Thread.sleep(100);
                System.out.println("B: " + respostasRandom[1].getResposta());
                Thread.sleep(100);
                System.out.println("C: " + respostasRandom[2].getResposta());
                Thread.sleep(100);
                System.out.println("D: " + respostasRandom[3].getResposta());
                Thread.sleep(100);
                System.out.println();
                System.out.print("Para responder, digite a letra correspondente: ");

                String respostaUsuario = scanner.nextLine().toUpperCase();
                boolean respostaCorreta = false;
                boolean respostaValida = false;
                int conclusao = 0;
                while (respostaValida == false) {
                    switch (respostaUsuario) {
                        case "A":
                            respostaCorreta = respostasRandom[0].isCorreta();
                            respostaValida = true;
                            conclusao = 0;
                            break;
                        case "B":
                            respostaCorreta = respostasRandom[1].isCorreta();
                            respostaValida = true;
                            conclusao = 1;
                            break;
                        case "C":
                            respostaCorreta = respostasRandom[2].isCorreta();
                            respostaValida = true;
                            conclusao = 2;
                            break;
                        case "D":
                            respostaCorreta = respostasRandom[3].isCorreta();
                            respostaValida = true;
                            conclusao = 3;
                            break;
                        default:
                            System.out.println("Resposta inválida. Tente novamente.");
                    }
                }

                Thread.sleep(300);

                if (respostaCorreta) {
                    System.out.println("Resposta correta!");
                    System.out.println(respostasRandom[conclusao].getConclusao());
                    pontuacao.addPontos(pontuacao.getPontos(nivel.getDificuldade()));
                    pontuacao.incAcerto();
                } else {
                    System.out.println("Resposta incorreta.");
                    System.out.println(respostasRandom[conclusao].getConclusao());
                    pontuacao.incErro();
                }
                System.out.println();
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        scanner.close();
    }
}