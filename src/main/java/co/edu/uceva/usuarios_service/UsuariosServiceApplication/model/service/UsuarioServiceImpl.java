package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.IUsuarioDao;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioDao usuarioDao;

    @Override
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).get();
    }

    @Override
    public Usuario findByNombre(String nombre){ return usuarioDao.findByNombre(nombre);}
    public Usuario findAllByNombre(String nombre){ return usuarioDao.findAllByNombre(nombre);}

}
