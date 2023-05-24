package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    List<Usuario> findAllByNombre(String nombre);
    List<Usuario> findAllByNombreIgnoreCase(String nombre);
}
