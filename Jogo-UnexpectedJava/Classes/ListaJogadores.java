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

    public void salvarJogadores(String arquivo) {
        // Implementar lógica para salvar jogadores em um arquivo
        // Exemplo: escrever os dados dos jogadores no arquivo especificado
    }

    public void importarJogadores(String arquivo) {
        // Implementar lógica para importar jogadores de um arquivo
        // Exemplo: ler o arquivo e criar objetos Jogador a partir dos dados
    }

}
