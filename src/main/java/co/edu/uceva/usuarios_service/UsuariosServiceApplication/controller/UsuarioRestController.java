package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/usuario-service")
public class UsuarioRestController {
    @Autowired
    IUsuarioService usuarioService;

    /**
     * EndPoint para listar los usuarios
     *
     * @return Lista de usuarios en JSON
     */
    @GetMapping("/usuarioservice/listarusuarios")
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    /**
     * Función para buscar un usuario de acuerdo a su ID
     * @param id Este recibe el ID del usuario
     * @return Retorna la información del usuario con ese ID en la DB
     */
    @GetMapping("/usuarioservice/buscarusuarioid/{id}")
    public Usuario buscarUsuario(@PathVariable("id") Long id) {//pathvariable es para sacar de la url esa palabra id
        return usuarioService.findById(id);
    }

    /**
     * Función para listar los usuarios con un nombre en específico
     * @param nombre Recibe el nombre del usuario a buscar
     * @return Retorna una lista de usuarios que comparten ese nombre
     */
    @GetMapping("/usuarioservice/buscarusuariosnombre/{nombre}")
    public List<Usuario> buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.findAllByNombre(nombre);
    }

    /**
     * Función para crear un usuario en la DB
     * @param usuario Recibe el JSON con los campos necesarios para agregarlos a la DB
     * @return El usuario se agrega a la lista ya existente con un ID automático
     */
    @PostMapping("/usuarioservice/crearusuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        // Convertir nombre y apellido a mayúsculas
        //usuario.setNombre(usuario.getNombre().toUpperCase());
        //usuario.setApellido(usuario.getApellido().toUpperCase());
        return usuarioService.save(usuario);
    }

    /**
     * Función para eliminar un usuario existente en la DB
     * @param id Recibe el id del usuario a eliminar
     */
    @DeleteMapping("usuarioservice/eliminarusuario/{id}")
    public void borrarUsuario(@PathVariable("id") Long id) {
        Usuario usuario;
        usuario = usuarioService.findById(id); //Busca el usuario con id #
        usuarioService.delete(usuario); //usuario service elimina ese usuario
    }

    /**
     * Función para editar un usuario existente en la DB
     * @param usuario Recibe el JSON con el ID del usuario a editar y los nuevos valores de los campos
     * @return El usuario con los nuevos valores en la DB
     */
    @PutMapping("usuarioservice/editarusuario")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }

    /**
     * Función para buscar a una persona por su nombre y apellido. Problema actual: Por ejemplo Camilo Gonzales, si se busca Camilo G lo encuentra, pero si se busca Cami G no retorna nada. Si solo se busca Cami si lo encuentra.
     * @param termino Recibe el nombre y apellido, si van los dos tiene que ser exacto.
     * @return Las personas que coincidan con el parámetro dado en la DB.
     */
    @GetMapping("/buscarusuariosnombreyapellido/{termino}")
    public List<Usuario> buscarPorNombreYApellido(@PathVariable String termino) {
        return usuarioService.buscarPorNombreYApellido(termino);
    }

    /**
     * Función para buscar a una persona por su nombre y apellido. Recibe ambos campos y sin importar si están incompletos retorna las coincidencias más cercanas.
     * @param nombre Recibe el nombre a buscar, sin importar si está incompleto.
     * @param apellido Recibe el apellido a buscar, sin importar si está incompleto.
     * @return Las coincidencias más cercanas en la DB
     */

    @GetMapping("/buscarusuariosnombreyapellido2/{nombre}/{apellido}")
    public List<Usuario> buscarPorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido) {
        return usuarioService.buscarPorNombreYApellido2(nombre, apellido);
    }
}
