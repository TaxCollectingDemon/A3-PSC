import java.util.Random;

public class Embaralhar {
    public static Resposta[] embaralharRespostas(Resposta[] respostas) {
        Random rnd = new Random();
        for (int i = respostas.length - 1; i > 0; i--)
        {
        int index = rnd.nextInt(i + 1);

        Resposta substitui = respostas[index];
        respostas[index] = respostas[i];
        respostas[i] = substitui;
        }
        return respostas;
    }
}
