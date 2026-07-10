package estudoDeCaso;

public class Validador {

    public static boolean eValidoString(String texto){
        return texto != null && !texto.isBlank();
    }

    public static boolean eValidoInt(int numero){
        return numero > 0;
    }
}