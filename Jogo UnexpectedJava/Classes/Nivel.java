public class Nivel {
    private String nome;
    private String dificuldade;
    private Cenario[] cenarios;
    private String imagem;
    private String fundo;

    public Nivel(String nome, String dificuldade, Cenario[] cenarios, String imagem, String fundo) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.cenarios = cenarios;
        this.imagem = imagem;
        this.fundo = fundo;
    }

    public String getNome() {
        return nome;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public Cenario[] getCenarios() {
        return cenarios;
    }

    public String getImagem() {
        return imagem;
    }

    public String getFundo() {
        return fundo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void setCenarios(Cenario[] cenarios) {
        this.cenarios = cenarios;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setFundo(String fundo) {
        this.fundo = fundo;
    }

    public String toString() {
        return "Nivel{" +
                "nome='" + nome + '\'' +
                ", dificuldade='" + dificuldade + '\'' +
                ", imagem='" + imagem + '\'' +
                ", fundo='" + fundo + '\'' +
                '}';
    }

    public void importarCenarios(String arquivo) {
        int escopo1 = arquivo.indexOf(this.nome);
        escopo1 = arquivo.indexOf(";", escopo1) + 1;
        int escopo3 = arquivo.indexOf("Nivel ", escopo1);
        if (escopo3 == -1) {
            escopo3 = arquivo.length() - 1;
        }

        arquivo = arquivo.substring(escopo1, escopo3);
        int escopo2 = arquivo.lastIndexOf("Cenario ");
        escopo3 = arquivo.indexOf(":", escopo2);
        int numCenarios = Integer.parseInt(arquivo.substring(escopo2 + 8, escopo3).trim());
        Cenario[] tempCenarios = new Cenario[numCenarios];

        for (int i = 0; i < tempCenarios.length; i++) {
            String tempNome = "Cenario " + (i + 1);
            String tempIntroducao = "";
            String tempCapituloLivro = "";
            Resposta[] tempRespostas = null;
            int tempNivel = Integer.parseInt(this.nome.substring(5).trim());

            escopo1 = arquivo.indexOf(tempNome) + tempNome.length() + 1;
            escopo2 = arquivo.indexOf("Capitulo ", escopo1);
            escopo3 = arquivo.indexOf(";", escopo2);

            tempCapituloLivro = arquivo.substring(escopo2 + 8, escopo3).trim();

            escopo1 = escopo3 + 1;
            escopo2 = arquivo.indexOf("Resposta ", escopo1);
            escopo3 = arquivo.indexOf("Cenario ", escopo1);
            int escopo4 = arquivo.indexOf("Nivel ", escopo1);

            if (escopo2 == -1) {
            escopo2 = arquivo.length() - 1;
            }
            if (escopo3 == -1) {
            escopo3 = arquivo.length() - 1;
            }
            if (escopo4 == -1) {
            escopo4 = arquivo.length() - 1;
            }
            if (escopo2 < escopo3) {
                escopo3 = escopo2;
            }
            if (escopo3 < escopo4) {
                escopo4 = escopo3;
            }

            tempIntroducao = arquivo.substring(escopo1, escopo4).trim();

            tempCenarios[i] = new Cenario(tempNome, tempIntroducao, this.dificuldade, tempRespostas, tempCapituloLivro, tempNivel);
            tempCenarios[i].importarRespostas(arquivo);
        }

        setCenarios(tempCenarios);
    }

}
