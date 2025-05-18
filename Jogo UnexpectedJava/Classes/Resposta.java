public class Resposta {
private String resposta;
private boolean correta;
private String final;

public Resposta(String resposta, boolean correta, String final) {
    this.resposta = resposta;
    this.eCorreta = correta;
    this.final = final;
}

public String getResposta() {
    return resposta;

}

public boolean isCorreta() {
    return correta;
}

public String getFinal() {
    return final;
}

public void setResposta(String resposta) {
    this.resposta = resposta;
}

public void setCorreta(boolean correta) {
    this.correta = correta;
}

public void setFinal(String final) {
    this.final = final;
}

}