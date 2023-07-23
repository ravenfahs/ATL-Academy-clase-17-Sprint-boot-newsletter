package Academy.alt.newsletter.validadorTest;

import Academy.alt.newsletter.validator.ValidatorEmail;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;


@SpringBootTest
public class ValidatorEmailTest {

    @Test
    public void elEmailDebeTenerUnaArroba(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultado = validatorEmail.esValidoEmail("prueba");
        assertFalse(resultado);
    }

    @Test
    public void elEmailTenerEspacios(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultado = validatorEmail.esValidoEmail("prueba @");
        assertFalse(resultado);
    }

    @Test
    public void elEmailDebeTenerPunto(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultad = validatorEmail.esValidoEmail("prueb13245487245465465321321311a@fafcom");
        assertFalse(resultad);
    }

    @Test
    public void elEmailDebeMaxDeCaracteres254(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultad = validatorEmail.esValidoEmail("prueb13245487245465465321321311a@hotmai.com");
        assertFalse(resultad);
    }

    @Test
    public void elEmailIniciaConPunto(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultad = validatorEmail.esValidoEmail(".prueba@hotmail.com");
        assertFalse(resultad);
    }

    @Test
    public void elEmailTieneMasDeUnaArroba(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultad = validatorEmail.esValidoEmail(".prueb@a@hotmail.com");
        assertFalse(resultad);
    }

    @Test
    public void elEmailTieneMasDeUnPunto(){
        ValidatorEmail validatorEmail = new ValidatorEmail();
        boolean resultad = validatorEmail.esValidoEmail("p.rueb@a@hotmail.com");
        assertFalse(resultad);
    }
}
