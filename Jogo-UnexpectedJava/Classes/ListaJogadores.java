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

    public void salvarJogadores(FileWriter escritor) {
        StringBuilder conteudo = new StringBuilder();
        for (Jogador jogador : listaJogadores) {
            if (jogador != null) {
                conteudo.append("<:> ").append(jogador.getUsername()).append(" <:> ")
                        .append(jogador.getNome()).append(" <:> ")
                        .append(jogador.getSenha()).append(" <:> ")
                        .append(jogador.getEmail()).append(" <:> ")
                        .append(jogador.getPontMax()).append(" <!>\n\n");
            }
        }
        try {
            escritor.write(conteudo.toString());
            escritor.flush();
        } catch (Exception e) {
            System.err.println("Erro ao salvar jogadores: " + e.getMessage());
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
            String tempEmail = arquivo.substring(escopo4 + 3, escopo5).trim();
            String tempSenha = arquivo.substring(escopo3 + 3, escopo4).trim();
            String tempNome = arquivo.substring(escopo2 + 3, escopo3).trim();
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
