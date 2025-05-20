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

    public void importarRespostas(String arquivo) {
        int escopo1 = arquivo.indexOf("Nivel " + this.nivel);
        escopo1 = arquivo.indexOf(this.nome, escopo1);
        //escopo1 = arquivo.indexOf("Resposta");

    }

}