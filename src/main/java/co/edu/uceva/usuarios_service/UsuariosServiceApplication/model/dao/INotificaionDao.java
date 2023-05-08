package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import org.springframework.data.repository.CrudRepository;

public interface INotificaionDao extends CrudRepository<Notificacion, Long> {

}
