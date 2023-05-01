package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import java.util.List;

public interface IUsuarioService {

        /**
         * Esta función es la implementación de la búsqueda de acuerdo al ID del usuario
         * @param id Recibe el ID del usuario
         * @return Devuelve el JSON de ese ID
         */
        Usuario findById(Long id);

        /**
         * Esta función es la implementación de la búsqueda de acuerdo al nombre del usuario
         * @param nombre Recibe el nombre del usuario a buscar
         * @return Devuelve el JSON de ese usuario
         */
        Usuario findByNombre(String nombre);

        List<Usuario> findAllByNombre(String nombre);
}
