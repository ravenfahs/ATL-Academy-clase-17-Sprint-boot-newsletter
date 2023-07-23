package Academy.alt.newsletter.models;

import jakarta.persistence.*;
import lombok.Data;

/*
* Esta clase nos permite representar el modelo y guardar en un base de datos, el registro de un correo electrónico de
* posibles suscriptores o potenciales compradores.
*
* */

@Data
@Entity
@Table(name =  "`lead`")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;
}
