package Academy.alt.newsletter.controllers;


import Academy.alt.newsletter.dao.LeadDao;
import Academy.alt.newsletter.models.Lead;
import Academy.alt.newsletter.validator.ValidatorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewslettersController {

    @Autowired
    private LeadDao leadDao;
    ValidatorEmail validarCorreo = new ValidatorEmail();

    /*
     * Este método se encarga de recibir el correo electrónico en formato JSON, realiza una consulta a la base de datos para validar si
     * ya existe o no en la base de datos, si no existe verifica si el correo tiene una estructura adecuada,
     * y si esta ok, lo registra en la base de datos
     * @param email
     * @return el resultado de la consulta de registro con éxito, el email no tiene una estructura válida, el correo ya está registrado
     *
     * */
    @PostMapping("/api/newsletter")
    public ResponseEntity<String> registrarLead(@RequestBody Lead lead){
        if(leadDao.correoYaRegistrado(lead)==null ){
            if(validarCorreo.esValidoEmail(lead.getEmail())){
                leadDao.registrar(lead);
                return new ResponseEntity<>("Correo registrado con éxito....", HttpStatus.OK);
            }
            return new ResponseEntity<>("Oppps-error..., el correo registrado no tiene una estructura valida, intenta de nuevo....", HttpStatus.OK);
        }
       return new ResponseEntity<>("Oppps-error..., el correo ya esta registrado....", HttpStatus.OK);
    }

    /*
     *Este método se encarga de consultar los emails y los presenta en formato JSON
     *@return listado de emails
     * */
    @GetMapping("/api/newsletter")
    public List<Lead> getEmailNewsLetters(){
        return leadDao.getLead();
    }

    /*
     * Este método se encarga de recibir el "id" del email, realiza una consulta a la base de datos para validar si
     * existe o no en la base de datos por el valor de su id, si lo encuentra procede a dar de baja el email en la base de datos
     * @param id
     * @return una respuesta HTTP con lo sucedido en la operación de eliminación por "id"
     * */
    @DeleteMapping("/api/newsletter/{id}")
    public ResponseEntity<String> eliminarLead(@PathVariable Long id){
        if(leadDao.eliminarLead(id)==null){
            return new ResponseEntity<>("Oppps-error..., el Id: " + id + " no esta registrado, intenta de nuevo ", HttpStatus.OK);
        }
        return new ResponseEntity<>("El Id: " + id + " fue eliminado de la lista con éxito..", HttpStatus.OK);
    }

    /*
     * Este método se encarga de recibir el email, realiza una consulta a la base de datos para validar si
     * existe o no en la base de datos por el valor de su email, si lo encuentra procede a dar de baja el email en la base de datos
     * @param email
     * @return una respuesta HTTP con lo sucedido en la operación de eliminación por "email"
     * */
    @DeleteMapping("/api/newsletter/email_/{email}")
    public ResponseEntity<String> eliminarLeadCorreo(@PathVariable String email){

        if(leadDao.eliminarEmail(email)==null){
            return new ResponseEntity<>("Oppps-error..., el correo: " + email + " no esta registrado, intenta de nuevo ", HttpStatus.OK);
        }
        return new ResponseEntity<>("El correo: " + email + " fue eliminado de la lista con éxito..", HttpStatus.OK);
    }
}
