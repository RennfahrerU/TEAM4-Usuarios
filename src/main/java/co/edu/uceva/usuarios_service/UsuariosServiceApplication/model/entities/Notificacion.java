package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notificacion {

    /**
     * Se dejo en comentario ya que por recomencacion del profesor se pospone esta implementacion
     */

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
   // private Date fecha;
    @ManyToOne
    @JoinColumn
    private Usuario usuario;*/

}
