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
            FileReader arquivoScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\Lista-cenarios.txt");
            BufferedReader arquivo = new BufferedReader(arquivoScan);
            FileReader introScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\Introducao.txt");
            BufferedReader introducao = new BufferedReader(introScan);
        ) {
            StringBuilder conteudo = new StringBuilder();
            String line = arquivo.readLine();
            while (line != null) {
                conteudo.append(line).append("\n");
                line = arquivo.readLine();
            }
            niveis.importarNiveis(conteudo.toString());

            StringBuilder conteudoIntro = new StringBuilder();
            String lineIntro = introducao.readLine();
            while (lineIntro != null) {
                conteudoIntro.append(lineIntro).append("\n");
                lineIntro = introducao.readLine();
            }

            Thread.sleep(2000);

            System.out.println("Níveis carregados com sucesso!");
            System.out.println();
            System.out.println("Desejas ler a introdução para a história do jogo?");
            System.out.println("Digite 'S' para aceitar, ou qualquer outra tecla para recusar.");
            String resposta = scanner.nextLine().toUpperCase();
            if (resposta.equals("S")) {
                System.out.println(conteudoIntro + "\n");
                Thread.sleep(1500);
            } else {
                System.out.println("Ok, vamos começar o jogo!");
            }
            System.out.println();

            Thread.sleep(500);
            
            resposta = "";
            Random random = new Random();
            Pontuacao pontuacao = new Pontuacao();
            boolean querjogar = true;
            boolean estaLogado = false;
            Jogador jogadorAtivo = new Jogador();

            while (querjogar == true) {
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

                    boolean respostaCorreta = false;
                    boolean respostaValida = false;
                    int conclusao = 0;
                    while (respostaValida == false) {
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
                        Thread.sleep(500);

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
                        pontuacao.addPontos(pontuacao.calcPontos(nivel.getDificuldade()));
                        pontuacao.incAcerto();
                    } else {
                        System.out.println("Resposta incorreta.");
                        System.out.println(respostasRandom[conclusao].getConclusao());
                        pontuacao.incErro();
                    }
                    System.out.println();
                    System.out.println();

                    Thread.sleep(1000);
                }
                System.out.println("Parabéns, você completou todos os níveis!");
                System.out.println();

                Thread.sleep(500);

                System.out.println("Sua pontuação final é de: " + pontuacao.getPontos() + " pontos!");
                Thread.sleep(300);
                System.out.println("Você teve " + pontuacao.getAcertos() + " acertos e " + pontuacao.getErros() + " erros.");
                Thread.sleep(300);
                System.out.println("Seu título final é: " + pontuacao.getTitulo() + "!");
                System.out.println();
                Thread.sleep(300);

                //Inserir aqui todas as funcionalidades de salvar o jogador, atualizar o top 10, etc.

                System.out.println("Deseja jogar novamente?");
                System.out.println("Digite 'S' para aceitar, ou qualquer outra tecla para recusar.");
                Thread.sleep(300);
                resposta = scanner.nextLine().toUpperCase();
                if (resposta.equals("S")) {
                    querjogar = true;
                    Thread.sleep(500);
                }
                else {
                    querjogar = false;
                    System.out.println("Obrigado por jogar!");
                    Thread.sleep(300);
                    System.out.println("Se quiser jogar novamente, basta reiniciar o programa.");
                    Thread.sleep(300);
                }


            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        scanner.close();
    }
}