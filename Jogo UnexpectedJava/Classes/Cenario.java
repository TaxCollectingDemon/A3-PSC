public class Cenario {
private String nome;
private String introducao;
private String dificuldade;
private int valorPontuacao;
private Resposta[] respostas;

public Cenario(String nome, String introducao, String dificuldade, int valorPontuacao, Resposta[] respostas) {
    this.nome = nome;
    this.introducao = introducao;
    this.dificuldade = dificuldade;
    this.valorPontuacao = valorPontuacao;
    this.respostas = respostas;
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

public int getValorPontuacao() {
    return valorPontuacao;
}

public Resposta[] getRespostas() {
    return respostas;
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

public void setValorPontuacao(int valorPontuacao) {
    this.valorPontuacao = valorPontuacao;
}

public void setRespostas(Resposta[] respostas) {
    this.respostas = respostas;
}

}
