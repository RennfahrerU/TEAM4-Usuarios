package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.IUsuarioDao;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioDao usuarioDao;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).get();
    }

    @Override
    public List<Usuario> findAllByNombre(String nombre) {
        return usuarioDao.findAllByNombreIgnoreCase(nombre);
    }

    @Override
    public List<Usuario> searchByNombre(String nombre) {
        return usuarioDao.searchByNombre(nombre.toLowerCase());
    }

}
