package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {



}
