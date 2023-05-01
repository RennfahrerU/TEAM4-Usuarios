package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.util.Encriptar;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email(message = "Ingrese un correo valido")
    private String email;

    @NotNull
    @Size(min=8, max = 20, message = "La contraseña debe tener al menos 8 caracteres y maximo 20")
    private String contrasena;

    @NotNull
    @Column(name="nombre")
    private String nombre;

    public String validarUsuario(){

        String contrasenaEncriptada = Encriptar.md5(contrasena);
        System.out.println("Contraseña Encriptada: "+contrasenaEncriptada);

        //Optional<usuariodb> devdbAutenticado = this..login(this.id,contrasenaEncriptada);

        return "";
    }
}
 
