package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.web.bind.annotation.*;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario-service")
public class UsuarioRestController {
    @Autowired
    IUsuarioService usuarioService;

    /**
     * EndPoint para recibir  un saludo
     *
     * @return Lista de usuarios
     */
    @GetMapping("/usuarios")
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    /*
        @GetMapping("/Usuario/{correo}")
        public String imprimir(@PathVariable("correo") String correo) {
            return "Usuario" + correo;
        }*/
    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable("id") Long id) {//pathvariable es para sacar de la url esa palabra id
        return usuarioService.findById(id);

    }

    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void borrarUsuario(@PathVariable("id") Long id) {
        Usuario usuario;
        usuario = usuarioService.findById(id);//busque usuario con id tal
        usuarioService.delete(usuario);//usuario service borra ese usuario

    }

    @PutMapping("/usuarios")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.update(usuario);
    }
}
