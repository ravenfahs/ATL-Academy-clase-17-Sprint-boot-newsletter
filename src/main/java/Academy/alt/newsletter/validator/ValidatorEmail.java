package Academy.alt.newsletter.validator;

public class ValidatorEmail {

    /*
     * Este método se encarga de validar si un email tiene una estructura válida como
     * 1: si el email tiene una "@"
     * 2: si el email tener espacios
     * 3: si el email tiene un punto "."
     * 4: si el email debe más de 254 caracteres
     * 5: si el email tiene un punto al inicio
     * 6: si el email Tiene más de una arroba
     * 7: si el email tiene más de un punto
     * @param email
     * @return true o false
     * */


    public boolean esValidoEmail(String email){
        char arroba = '@';
        int contadorDeArroba = 0;

        char punto = '.';
        int contadoDePunto = 0;

        if(!email.contains("@")){
            return false;
        }

        if(email.contains(" ")){
            return false;
        }

        if(!email.contains(".")){
            return false;
        }

        if(email.length()>29){
            System.out.println(email.length());
            return false;
        }

        if(email.startsWith(".")){
            return false;
        }

        for (int i = 0; i <email.length() ; i++) {
            if(email.charAt(i) == arroba){
                contadorDeArroba ++;
            }
        }
        if(contadorDeArroba>=2){
            return false;
        }

        for (int i = 0; i <email.length() ; i++) {
            if(email.charAt(i) == punto){
                contadoDePunto ++;
            }
        }
        if(contadoDePunto>=2){
            return false;
        }
        return true;

    }

}
