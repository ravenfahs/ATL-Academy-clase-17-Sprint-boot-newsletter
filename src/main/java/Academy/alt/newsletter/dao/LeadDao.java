package Academy.alt.newsletter.dao;

import Academy.alt.newsletter.models.Lead;

import java.util.List;
/*
* Se establecen los métodos de la interfaz CRUD - faltaria el metodo de actualizar, algunas funciones tienen parámetros
* de entradas
* * */
public interface LeadDao {
    void registrar(Lead lead);

    List<Lead> getLead();

    Lead correoYaRegistrado(Lead lead);

    Long eliminarLead(Long id);

    String eliminarEmail(String email);
}
