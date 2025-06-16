import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Jogo Unexpected Java!");


        Thread.sleep(1500);
        
        System.out.println("Carregando os níveis do jogo...");
        Thread.sleep(500);
        System.out.println("Carregando a lista de jogadores...");
        Niveis niveis = new Niveis();
        ListaJogadores listaJogadores = new ListaJogadores();

        try (
            FileReader arquivoScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\Lista-cenarios.txt");
            BufferedReader arquivo = new BufferedReader(arquivoScan);
            FileReader introScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\Introducao.txt");
            BufferedReader introducao = new BufferedReader(introScan);
            FileReader jogadoresScan = new FileReader("Jogo-UnexpectedJava\\Arquivos-texto\\Lista-jogadores.txt");
            BufferedReader jogadores = new BufferedReader(jogadoresScan);
            FileWriter escritor = new FileWriter("Jogo-UnexpectedJava\\Arquivos-texto\\Lista-jogadores.txt");

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

            StringBuilder conteudoJogadores = new StringBuilder();
            String lineJogadores = jogadores.readLine();
            while (lineJogadores != null) {
                conteudoJogadores.append(lineJogadores).append("\n");
                lineJogadores = jogadores.readLine();
            }
            listaJogadores.importarJogadores(conteudoJogadores.toString());

            Thread.sleep(1500);


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
            boolean querjogar = true;
            boolean estaLogado = false;
            Jogador jogadorAtivo = new Jogador();

            while (querjogar == true) {
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
                System.out.println();
                Thread.sleep(300);

                if (estaLogado == false) {
                    System.out.println("Ranking dos 10 melhores jogadores:");
                    listaJogadores.atualizarTop10();
                    System.out.println(listaJogadores.exibirTop10());
                    Thread.sleep(500);
                    System.out.println();
                    System.out.println("Para salvar sua pontuação, você precisa estar logado.");
                    System.out.println("Deseja logar ou se cadastrar?");
                    Thread.sleep(300);
                    System.out.println("Digite 'S' para aceitar, ou qualquer outra tecla para recusar.");
                    resposta = scanner.nextLine().toUpperCase();

                    if (resposta.equals("S")) {
                        System.out.println("Digite um login para entrar ou se cadastrar:");
                        resposta = scanner.nextLine();
                        String tempUsername = resposta;
                        jogadorAtivo = listaJogadores.fazerLogin(resposta);
                        if (jogadorAtivo != null) {
                            estaLogado = true;
                            System.out.println("Você agora está logado como: " + jogadorAtivo.getUsername());
                        } else {
                            estaLogado = false;
                            System.out.println("Deseja se cadastrar?");
                            System.out.println("Digite 'S' para aceitar, ou qualquer outra tecla para recusar.");
                            resposta = scanner.nextLine().toUpperCase();
                            if (resposta.equals("S")) {
                                System.out.println("Digite seu nome:");
                                String tempNome = scanner.nextLine();
                                System.out.println("Digite um e-mail:");
                                String tempEmail = scanner.nextLine();
                                System.out.println("Digite uma senha:");
                                String tempSenha = scanner.nextLine();
                                jogadorAtivo = new Jogador(tempNome, tempUsername, tempSenha, tempEmail, pontuacao.getPontos());
                                listaJogadores.adicionarJogador(jogadorAtivo);
                                listaJogadores.salvarJogadores(escritor);
                                System.out.println("Cadastro realizado com sucesso!");
                                System.out.println("Você agora está logado como: " + jogadorAtivo.getUsername());
                                estaLogado = true;
                                listaJogadores.atualizarTop10();
                                System.out.println("Ranking dos 10 melhores jogadores:");
                                System.out.println(listaJogadores.exibirTop10());
                            } else {
                                System.out.println("Ok, você não está logado, e sua pontuação não será salva.");
                            }
                        }
                    } else {
                        System.out.println("Ok, sua pontuação não será salva.");
                    }

                }   else if (listaJogadores.encontrarJogador(jogadorAtivo.getUsername()) != -1) {
                    jogadorAtivo.salvarMelhor(pontuacao.getPontos());
                    listaJogadores.atualizarJogador(jogadorAtivo);
                    listaJogadores.salvarJogadores(escritor);
                    listaJogadores.atualizarTop10();
                    System.out.println(listaJogadores.exibirTop10());
                    System.out.println();
                    System.out.println("Sua pontuação foi atualizada.");
                }

                Thread.sleep(1000);
                
                
                boolean continuarSelecao = true;
                do {
                    if (estaLogado == true) {
                        System.out.println("Você está logado como: " + jogadorAtivo.getUsername());
                        System.out.println("Digite a opção que deseja selecionar:");
                        System.out.println("1 - Jogar novamente");
                        System.out.println("2 - Modificar dados do jogador");
                        System.out.println("3 - Trocar de jogador");
                        System.out.println("4 - Parar de jogar");
                        System.out.println();
                        resposta = scanner.nextLine();
                        Thread.sleep(500);

                        switch (resposta) {
                            case "1":
                                continuarSelecao = false;
                                querjogar = true;
                                break;
                            case "2":
                                    listaJogadores.modificarJogador(jogadorAtivo);
                                    jogadorAtivo = listaJogadores.getListaJogadores()[listaJogadores.encontrarJogador(jogadorAtivo.getUsername())];
                            case "3":
                                jogadorAtivo = listaJogadores.fazerLogin();
                                if (jogadorAtivo != null) {
                                    estaLogado = true;
                                    System.out.println("Você agora está logado como: " + jogadorAtivo.getUsername());
                                } else {
                                    estaLogado = false;
                                    System.out.println("Login ou cadastro falhou. Você não está logado.");
                                    continuarSelecao = false;
                                }
                                break;
                            case "4":
                                continuarSelecao = false;
                                querjogar = false;
                                escritor.close();
                                System.out.println("Obrigado por jogar!");
                                Thread.sleep(300);
                                System.out.println("Se quiser jogar novamente, basta reiniciar o programa.");
                                Thread.sleep(300);
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                continuarSelecao = true;
                        }
                    }
                    Thread.sleep(500);
                } while (continuarSelecao == true);
                

                if (estaLogado == false) {
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
                        escritor.close();
                        System.out.println("Obrigado por jogar!");
                        Thread.sleep(300);
                        System.out.println("Se quiser jogar novamente, basta reiniciar o programa.");
                        Thread.sleep(300);
                    }
                }

            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        scanner.close();
    }
}