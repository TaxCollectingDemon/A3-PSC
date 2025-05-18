public class Cenario {
private String nome;
private String introducao;
private String dificuldade;
private Resposta[] respostas;
private int paginaLivro;
private Nivel nivel;

public Cenario(String nome, String introducao, String dificuldade, Resposta[] respostas, int paginaLivro, Nivel nivel) {
    this.nome = nome;
    this.introducao = introducao;
    this.dificuldade = dificuldade;
    this.respostas = respostas;
    this.paginaLivro = paginaLivro;
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

public int getPaginaLivro() {
    return paginaLivro;
}

public Nivel getNivel() {
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

public void setPaginaLivro(int paginaLivro) {
    this.paginaLivro = paginaLivro;
}

public void setNivel(Nivel nivel) {
    this.nivel = nivel;

}
