public class Resposta {
    private String resposta;
    private boolean correta;
    private String conclusao;

    public Resposta(String resposta, boolean correta, String conclusao) {
        this.resposta = resposta;
        this.correta = correta;
        this.conclusao = conclusao;
    }

    public String getResposta() {
        return resposta;
    }

    public boolean isCorreta() {
        return correta;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String toString() {
        return "Resposta: \n" +
                "resposta= " + resposta + '\n' +
                "correta= " + correta + '\n' +
                "conclusao= " + conclusao + '\n';
    }

}