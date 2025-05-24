public class Cenario {
    private String nome;
    private String introducao;
    private String dificuldade;
    private Resposta[] respostas;
    private String capituloLivro;
    private int nivel;

    public Cenario(String nome, String introducao, String dificuldade, Resposta[] respostas, String capituloLivro, int nivel) {
        this.nome = nome;
        this.introducao = introducao;
        this.dificuldade = dificuldade;
        this.respostas = respostas;
        this.capituloLivro = capituloLivro;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public String getIntroducao() {
        return introducao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public Resposta[] getRespostas() {
        return respostas;
    }

    public String getCapituloLivro() {
        return capituloLivro;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIntroducao(String introducao) {
        this.introducao = introducao;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public void setRespostas(Resposta[] respostas) {
        this.respostas = respostas;
    }

    public void setCapituloLivro(String capituloLivro) {
        this.capituloLivro = capituloLivro;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String toString() {
        StringBuilder retorno = new StringBuilder();
        retorno.append("Cenario: ").append(this.nome).append("\n");
        retorno.append("Introducao: ").append(this.introducao).append("\n");
        retorno.append("Dificuldade: ").append(this.dificuldade).append("\n");
        retorno.append("Capitulo Livro: ").append(this.capituloLivro).append("\n");
        retorno.append("Nivel: ").append(this.nivel).append("\n");
        
        
        return retorno.toString();
    }

    public void importarRespostas(String arquivo) {
        int escopo1 = arquivo.indexOf("Nivel " + this.nivel);
        escopo1 = arquivo.indexOf(this.nome, escopo1);
        String decalagem = this.nome + ": Capitulo " + this.capituloLivro + ";";
        escopo1 = escopo1 + decalagem.length() + 1;
        int escopo2 = arquivo.indexOf("Cenario ", escopo1);
        int escopo3 = arquivo.indexOf("Nivel ", escopo1);

        if (escopo2 == -1) {
            escopo2 = arquivo.length() - 1;
        }
        if (escopo3 == -1) {
            escopo3 = arquivo.length() - 1;
        }
        if (escopo2 < escopo3) {
            escopo3 = escopo2;
        }

        arquivo = arquivo.substring(escopo1, escopo3);
        escopo1 = arquivo.lastIndexOf("Resposta ");
        escopo2 = arquivo.indexOf(":", escopo1);
        int numRespostas = Integer.parseInt(arquivo.substring(escopo1 + 9, escopo2).trim());
        
        Resposta[] tempRespostas = new Resposta[numRespostas];

        for (int i = 0; i < tempRespostas.length; i++) {
            boolean tempCorreta = false;
            String tempResposta = "";
            String tempConclusao = "";
            escopo1 = arquivo.indexOf("Resposta " + (i + 1) + ":");
            escopo1 = arquivo.indexOf("\n", escopo1);
            escopo2 = arquivo.indexOf("Conclusao", escopo1);
            if (i + 2 <= numRespostas) {
                escopo3 = arquivo.indexOf("Resposta " + (i + 2), escopo1);
            } else {
                escopo3 = arquivo.length() - 1;
            }

            if (arquivo.indexOf("Correta", escopo1, escopo1 + 8) != -1) {
                tempCorreta = true;
            }
            tempResposta = arquivo.substring(escopo1, escopo2).trim();
            tempConclusao = arquivo.substring(escopo2 + 10, escopo3).trim();
            tempRespostas[i] = new Resposta(tempResposta, tempCorreta, tempConclusao);
        }
        
        setRespostas(tempRespostas);
    }

}