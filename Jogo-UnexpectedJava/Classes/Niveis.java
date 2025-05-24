public class Niveis {
    private Nivel[] listaNiveis;

    public Niveis(Nivel[] listaNiveis) {
        this.listaNiveis = listaNiveis;
    }

    public Niveis() {
    }

    public Nivel[] getListaNiveis() {
        return listaNiveis;
    }

    public void setListaNiveis(Nivel[] listaNiveis) {
        this.listaNiveis = listaNiveis;
    }

    public void importarNiveis(String arquivo) {
        int escopo1 = arquivo.lastIndexOf("Nivel ");
        int escopo2 = arquivo.indexOf(";", escopo1);
        int numNiveis = Integer.parseInt(arquivo.substring(escopo1 + 6, escopo2).trim());

        Nivel[] tempNiveis = new Nivel[numNiveis];

        for (int i = 0; i < tempNiveis.length; i++) {
            String tempNome = "Nivel " + (i + 1);
            String tempDificuldade = "";
            Cenario[] tempCenarios = null;
            String tempImagem = "";
            String tempFundo = "";

            if (i + 1 <= 2) {
                tempDificuldade = "Fácil";
            }
            else if (i + 1 <= 4) {
                tempDificuldade = "Médio";
            } else {
                tempDificuldade = "Difícil";
            }

            tempNiveis[i] = new Nivel(tempNome, tempDificuldade, tempCenarios, tempImagem, tempFundo);

            tempNiveis[i].importarCenarios(arquivo);
        }

        setListaNiveis(tempNiveis);
    }

}
