package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao;

import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
