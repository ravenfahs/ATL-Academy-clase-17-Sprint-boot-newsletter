package Academy.alt.newsletter.dao;

import Academy.alt.newsletter.models.Lead;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
* Esta clase encarga de hacer la comunicación con la base de datos o persistencia y que extiendo de la interface LeadDao que
* tiene dos los métodos, de crear, leer, modificar y borrar, el objeto y la variable entityManager
* con el método .createQuery(), recibe la como parámetro una consulta en SQL
* */


@Repository
@Transactional
public class LeadDaoImp implements LeadDao{

    @PersistenceContext
    EntityManager entityManager;

    /*
     *Se encarga de enviar los datos a la base de datos
     *@param lead en este caso es un correo de prospectos de ventas
     */

    @Override
    @Transactional
    public void registrar(Lead lead) {

        entityManager.merge(lead);
    }

    /*
    * Consulta y proyecta todos los correos registrados en la base de datos
    * @return lista de todos de correos
    * */
    @Override
    @Transactional
    public List<Lead> getLead() {
        String query = "FROM Lead";
        return entityManager.createQuery(query,Lead.class).getResultList();
    }

    /*
    * Consulta si un correo electrónico existe o no en la base de datos
    * @param lead y se captura dato del email para la verificación
    * @return el resultado de la consulta
     * */
    @Override
    public Lead correoYaRegistrado(Lead lead) {
        String query = "FROM Lead WHERE email = :email";
        List<Lead> listaLead = entityManager.createQuery(query,Lead.class)
                .setParameter("email",lead.getEmail())
                .getResultList();

        return listaLead.isEmpty() ? null : listaLead.get(0);

        /*
        if(listaLead.isEmpty()){
            return null;
        }
        return listaLead.get(0);
        */
    }

    /*
     * elimina un correo electrónico por su id, se valida si correo existe o no en la base de datos
     * @param id
     * @return el resultado de la consulta
     * */
    @Override
    public Long eliminarLead(Long id) {
        String buscar = "SELECT id FROM Lead WHERE id = :id";
        List<Lead> leadEliminar =  entityManager.createQuery(buscar,Lead.class)
                .setParameter("id",id)
                .getResultList();
        System.out.println(leadEliminar);


        if (leadEliminar.contains(id)){
            String query = "delete FROM Lead WHERE id = :id";
            entityManager.createQuery(query)
                    .setParameter("id",id)
                    .executeUpdate();
            return 1L;
        }
        else {
            return null;
        }
    }
    /*
     * elimina un correo electrónico por su email, se valida si correo existe o no en la base de datos
     * @param email
     * @return el resultado de la consulta
     * */
    @Override
    public String eliminarEmail(String email) {
        String buscar = "SELECT email FROM Lead WHERE email = :email";
        String resultado = entityManager.createQuery(buscar)
                .setParameter("email",email)
                .getResultList().toString();

        if (resultado.contains(email)) {
            System.out.println("esta vacio....");
            String query = "delete FROM Lead WHERE email = :email";
            entityManager.createQuery(query)
                    .setParameter("email",email)
                    .executeUpdate();
            return "ok";
        } else {
            return null;
        }
    }
}
