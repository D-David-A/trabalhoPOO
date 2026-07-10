package estudoDeCaso;

public class Validacoes {

    public static boolean eValidoString(String texto){
        return texto != null && !texto.isBlank();
    }

    public static boolean eValidoInt(int numero){
        return numero > 0;
    }

    public static boolean eValidoDouble(double numero){
        return numero > 0.0;
    }
}