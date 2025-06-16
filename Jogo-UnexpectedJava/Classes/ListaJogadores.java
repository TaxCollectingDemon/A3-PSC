import java.io.FileWriter;

public class ListaJogadores {
    private Jogador[] listaJogadores;
    private Jogador[] top10Jogadores;

    public ListaJogadores() {
        this.top10Jogadores = new Jogador[10];
    }

    public Jogador[] getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(Jogador[] listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public Jogador[] getTop10Jogadores() {
        return top10Jogadores;
    }

    public void setTop10Jogadores(Jogador[] top10Jogadores) {
        this.top10Jogadores = top10Jogadores;
    }

    public String exibirTop10() {
        StringBuilder construcao = new StringBuilder();
        for (int i = 0; i < top10Jogadores.length; i++) {
            if (top10Jogadores[i] != null) {
                construcao.append((i + 1) + ". " + top10Jogadores[i].getNome() + " - Pontuação Máxima: " + top10Jogadores[i].getPontMax() + "\n");
            }
        }
        return construcao.toString();
    }

    public void adicionarJogador(Jogador jogador) {
        if (listaJogadores == null) {
            listaJogadores = new Jogador[1];
            listaJogadores[0] = jogador;
        } else {
            Jogador[] tempLista = new Jogador[listaJogadores.length + 1];
            System.arraycopy(listaJogadores, 0, tempLista, 0, listaJogadores.length);
            tempLista[listaJogadores.length] = jogador;
            listaJogadores = tempLista;
        }
    }

    public void atualizarTop10() {
        if (listaJogadores != null) {
            java.util.Arrays.sort(listaJogadores, (j1, j2) -> Integer.compare(j2.getPontMax(), j1.getPontMax()));

            for (int i = 0; i < top10Jogadores.length; i++) {
                if (i < listaJogadores.length) {
                    top10Jogadores[i] = listaJogadores[i];
                } else {
                    top10Jogadores[i] = null;
                }
            }
        }
    }

    public void salvarJogadores() {
        try {
            FileWriter escritor = new FileWriter("Jogo-UnexpectedJava\\Arquivos-texto\\Lista-jogadores.txt", false);
            StringBuilder conteudo = new StringBuilder();
        for (Jogador jogador : listaJogadores) {
            if (jogador != null) {
                conteudo.append("<:> ").append(jogador.getUsername()).append(" <:> ")
                        .append(jogador.getEmail()).append(" <:> ")
                        .append(jogador.getSenha()).append(" <:> ")
                        .append(jogador.getNome()).append(" <:> ")
                        .append(jogador.getPontMax()).append(" <!>\n\n");
            }
        }
        escritor.write(conteudo.toString());
        escritor.close();
        } catch (Exception e) {
            System.err.println("Erro ao abrir o arquivo para escrita: " + e.getMessage());
        }
    }

    public int encontrarJogador(String username) {
        if (listaJogadores != null) {
            for (int i = 0; i < listaJogadores.length; i++) {
                if (listaJogadores[i].getUsername().equals(username)) {
                    return i;
                }
            }
        }
        return -1; // Jogador não encontrado
    }

    public void atualizarJogador(Jogador jogador) {
        int index = encontrarJogador(jogador.getUsername());
        listaJogadores[index] = jogador;
    }

    public Jogador fazerLogin() {
        System.out.println("Digite seu login:");
        String username = System.console().readLine();
        int index = encontrarJogador(username);
        boolean usuarioEncontrado = false;
        do {
            if (index != -1) {
                System.out.println("Olá, " + listaJogadores[index].getNome() + ". Por favor, digite sua senha:");
                String senha = System.console().readLine();
                boolean tentarNovamente = true;
                while (tentarNovamente == true) {
                    if (listaJogadores[index].getSenha().equals(senha)) {
                        System.out.println("Login realizado com sucesso!");
                        return listaJogadores[index];
                    } else {
                        System.out.println("Senha incorreta. Digite 'S' para tentar novamente, senão insira qualquer outra tecla.");
                        String resposta = System.console().readLine();
                        if (!resposta.equalsIgnoreCase("S")) {
                            tentarNovamente = false;
                            System.out.println("Login falhou. Tente novamente mais tarde.");
                        } else {
                            System.out.println("Por favor, digite sua senha novamente:");
                            senha = System.console().readLine();
                        }
                    }
            }
            } else {
                System.out.println("Usuário não encontrado. Digite 'S' para tentar novamente, senão insira qualquer outra tecla.");
                    String resposta = System.console().readLine();
                    if (!resposta.equalsIgnoreCase("S")) {
                        usuarioEncontrado = true;
                        System.out.println("Login falhou. Tente novamente mais tarde.");
                    } else {
                        System.out.println("Por favor, digite seu login novamente:");
                        username = System.console().readLine();
                    }
                
            }
        } while (usuarioEncontrado == false);

        return null;
    }

    public Jogador fazerLogin(String username) {
        int index = encontrarJogador(username);
        boolean continuarSelecao = true;
        do {
            if (index != -1) {
                System.out.println("Olá, " + listaJogadores[index].getNome() + ". Por favor, digite sua senha:");
                String senha = System.console().readLine();
                boolean tentarNovamente = true;
                while (tentarNovamente == true) {
                    if (listaJogadores[index].getSenha().equals(senha)) {
                        System.out.println("Login realizado com sucesso!");
                        return listaJogadores[index];
                    } else {
                        System.out.println("Senha incorreta. Digite 'S' para tentar novamente, senão insira qualquer outra tecla.");
                        String resposta = System.console().readLine();
                        if (!resposta.equalsIgnoreCase("S")) {
                            tentarNovamente = false;
                            System.out.println("Login falhou. Tente novamente mais tarde.");
                            continuarSelecao = false;
                        } else {
                            System.out.println("Por favor, digite sua senha novamente:");
                            senha = System.console().readLine();
                        }
                    }
            }
            } else {
                System.out.println("Usuário não encontrado.");
                continuarSelecao = false;
            }
        } while (continuarSelecao == true);

        return null;
    }

    public void modificarJogador(Jogador jogador) {
        int index = encontrarJogador(jogador.getUsername());

        if (index != -1) {
            boolean continuarModificacao = true;
            while (continuarModificacao) {
                System.out.println("Digite a opção que deseja selecionar:");
                System.out.println("1 - Nome do jogador: " + listaJogadores[index].getNome());
                System.out.println("2 - e-mail do jogador: " + listaJogadores[index].getEmail());
                System.out.println("3 - Senha do jogador: " + listaJogadores[index].getSenha());
                System.out.println("4 - Sair");
                System.out.println();
                String resposta = System.console().readLine();
                boolean respostaValida = false;
                while (respostaValida == false) {
                    switch (resposta) {
                        case "1":
                            System.out.println("Digite o novo nome do jogador:");
                            String novoNome = System.console().readLine();
                            listaJogadores[index].setNome(novoNome);
                            respostaValida = true;
                            break;
                        case "2":
                            System.out.println("Digite o novo e-mail do jogador:");
                            String novoEmail = System.console().readLine();
                            listaJogadores[index].setEmail(novoEmail);
                            respostaValida = true;
                            break;
                        case "3":
                            System.out.println("Digite a nova senha do jogador:");
                            String novaSenha = System.console().readLine();
                            listaJogadores[index].setSenha(novaSenha);
                            respostaValida = true;
                            break;
                        case "4":
                            System.out.println("Saindo da modificação do jogador.");
                            respostaValida = true;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            resposta = System.console().readLine();
                    }
                }
                System.out.println("Deseja continuar modificando o jogador?)");
                System.out.println("Digite 'S' para continuar ou qualquer outra tecla para sair.");
                String continuarResposta = System.console().readLine();
                if (!continuarResposta.equalsIgnoreCase("S")) {
                    continuarModificacao = false;
                    atualizarJogador(listaJogadores[index]);
                    System.out.println("Modificações salvas com sucesso.");
                }
            }
        } else {
            System.out.println("Jogador não encontrado.");
        }
    }

    public void importarJogadores(String arquivo) {
        int escopo1 = arquivo.indexOf("<:>");
        boolean temProximo = true;
        if (escopo1 == -1) {
            System.out.println("Nenhum jogador encontrado no arquivo.");
            temProximo = false;
        }
        else {
            System.err.println("Jogadores encontrados no arquivo.");
        }

        while (temProximo == true) {
            int escopo2 = arquivo.indexOf("<:>", escopo1 + 3);
            int escopo3 = arquivo.indexOf("<:>", escopo2 + 3);
            int escopo4 = arquivo.indexOf("<:>", escopo3 + 3);
            int escopo5 = arquivo.indexOf("<:>", escopo4 + 3);
            int escopo6 = arquivo.indexOf("<!>", escopo5 + 3);

            String tempUsername = arquivo.substring(escopo1 + 3, escopo2).trim();
            String tempEmail = arquivo.substring(escopo2 + 3, escopo3).trim();
            String tempSenha = arquivo.substring(escopo3 + 3, escopo4).trim();
            String tempNome = arquivo.substring(escopo4 + 3, escopo5).trim();
            int tempPontMax = Integer.parseInt(arquivo.substring(escopo5 + 3, escopo6).trim());
            
            Jogador tempJogador = new Jogador(tempNome, tempUsername, tempSenha, tempEmail, tempPontMax);
            adicionarJogador(tempJogador);

            if (arquivo.indexOf("<:>", escopo6 + 3) == -1) {
                temProximo = false;
            } else {
                escopo1 = arquivo.indexOf("<:>", escopo6 + 3);
            }
        }
    }

}
