package estudoDeCaso;

public class Validacoes {

    public static final String REGEX_CPF = "^\\d{11}$";

    public static boolean eValidoString(String texto){
        return texto != null && !texto.isBlank();
    }

    public static boolean eValidoInt(int numero){
        return numero > 0;
    }

    public static boolean eValidoDouble(double numero){
        return numero > 0.0;
    }

    public static boolean eValidoEmail(String texto){
        return texto != null && !texto.isBlank() && texto.contains("@");
    }

    public static boolean eValidoTelefone(String texto){
        return texto != null && texto.matches("\\d{8}$");
    }

    public static boolean eValidoCPF(String texto){
        return erroCPF(texto) == null;
    }

    public static String erroCPF(String texto){
        if(texto == null || texto.length() != 11){
            return "Erro: CPF deve conter 11 números!";
        }
        if(!texto.matches(REGEX_CPF)){
            return "Erro: CPF só deve conter números!";
        }
        if(!validaCPF(texto)){
            return "Erro: CPF incorreto!";
        }
        return null;
    }

    public static int validaX(String digitos) {
        int fator = 10;
        int somaDigitos = 0;

        for (int x = 0; x < digitos.length()-2; x++) {
            somaDigitos = somaDigitos + (Character.getNumericValue(digitos.charAt(x))*fator);
            fator = fator - 1;
        }
        somaDigitos = somaDigitos%11;
        if (somaDigitos < 2) {
            somaDigitos = 0;
        }
        else {
            somaDigitos = 11 - somaDigitos;
        }
        return somaDigitos;
    }

    public static int validaY(String digitos) {
        int fator = 11;
        int somaDigitos = 0;
        for (int x = 0; x < digitos.length()-1; x++) {
            somaDigitos = somaDigitos + (Character.getNumericValue(digitos.charAt(x))*fator);
            fator = fator - 1;
        }
        somaDigitos = somaDigitos%11;
        if (somaDigitos < 2) {
            somaDigitos = 0;
        }
        else {
            somaDigitos = 11 - somaDigitos;
        }
        return somaDigitos;
    }


    public static boolean validaCPF(String digitos) {
        int fatorX  = validaX(digitos);
        int fatorY  = validaY(digitos);
        int digitoX = Character.getNumericValue(digitos.charAt(9));
        int digitoY = Character.getNumericValue(digitos.charAt(10));

        if ((fatorX == digitoX) && (fatorY == digitoY)) {
            return true;
        }
        else {
            return false;
        }
    }
}