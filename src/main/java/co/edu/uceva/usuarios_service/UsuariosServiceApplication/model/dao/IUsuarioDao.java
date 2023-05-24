package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    List<Usuario> findAllByNombreIgnoreCase(String nombre);
    @Query("SELECT u FROM Usuario u WHERE CONCAT(LOWER(u.nombre), ' ', LOWER(u.apellido)) LIKE %:termino%")
    List<Usuario> buscarPorNombreYApellido(@Param("termino") String termino);
    List<Usuario> findAllByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
