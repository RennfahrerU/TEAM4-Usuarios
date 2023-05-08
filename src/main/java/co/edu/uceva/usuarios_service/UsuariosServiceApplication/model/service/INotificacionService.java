package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
   Notificacion save(Notificacion notificacion);
   void delete(Notificacion notificacion);
   Notificacion update(Notificacion notificacion);
   List<Notificacion>findAll();
   Notificacion findById(Long id);
   void sendNotification(Notificacion notificacion);
}
