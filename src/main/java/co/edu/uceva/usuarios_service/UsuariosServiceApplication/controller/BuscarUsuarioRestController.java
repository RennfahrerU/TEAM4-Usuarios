package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuscarUsuarioRestController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/usuarioid/{id}")
    public Usuario buscarID(@PathVariable("id") Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/usuarionombre/{nombre}")
    public Usuario buscarNombre(@PathVariable("nombre") String nombre){
        return usuarioService.findByNombre(nombre);
    }

}

