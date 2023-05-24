package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuscarUsuarioRestController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/usuarioid/{id}")
    public Usuario buscarID(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }


    @GetMapping("/usuariosnombre/{nombre}")
    public List<Usuario> buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.findAllByNombre(nombre);
    }

}

