package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario save(Usuario usuario);

    void delete(Usuario usuario);

    Usuario update(Usuario usuario);

    List<Usuario> findAll();

    /**
     * Esta función es la implementación de la búsqueda de acuerdo al ID del usuario
     *
     * @param id Recibe el ID del usuario
     * @return Devuelve el JSON de ese ID
     */
    Usuario findById(Long id);

    /**
     * Esta función es la implementación de la búsqueda de acuerdo al nombre del usuario
     *
     * @param nombre Recibe el nombre del usuario a buscar
     * @return Devuelve el JSON de ese usuario
     */

    List<Usuario> findAllByNombre(String nombre);
}
