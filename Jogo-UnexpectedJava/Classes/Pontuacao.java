public class Pontuacao {
    int pontos;
    int acertos;
    int erros;
    String titulo;

    public Pontuacao() {
        this.pontos = 0;
        this.acertos = 0;
        this.erros = 0;
        this.titulo = "";
    }
    
    public int getPontos(String dificuldade) {
        switch (dificuldade) {
            case "Fácil":
                pontos = pontos + 100;
                break;
            case "Médio":
                pontos = pontos + 150;
                break;
            case "Difícil":
                pontos = pontos + 200;
                break;
            default:
                System.out.println("Dificuldade inválida.");
        }
        return pontos;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }

    public String getTitulo() {
        if (pontos >= 0 && pontos <= 200) {
            titulo = "Novato";
        } else if (pontos > 200 && pontos <= 350) {
            titulo = "Aprendiz de Programador";
        } else if (pontos > 350 && pontos <= 450) {
            titulo = "Desbravador do Java";
        } else if (pontos > 30 && pontos <= 40) {
            titulo = "Mestre do Código";
        }

        return titulo;
    }

    public void addPontos(int pontos) {
        this.pontos += pontos;
    }

    public void incAcerto() {
        this.acertos++;
    }

    public void incErro() {
        this.erros++;
    }
}
