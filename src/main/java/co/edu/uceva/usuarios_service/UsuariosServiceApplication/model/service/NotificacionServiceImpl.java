package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.INotificaionDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements INotificacionService {
    @Autowired
    INotificaionDao notificacionDao;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public Notificacion save(Notificacion notificacion) {
        notificacion = notificacionDao.save(notificacion);
        rabbitTemplate.convertAndSend("notificaciones.exchange", "notificaciones.key", notificacion);
        return notificacion;
    }

    @Override
    public void delete(Notificacion notificacion) {
        notificacionDao.delete(notificacion);
    }

    @Override
    public Notificacion update(Notificacion notificacion) {
        return notificacionDao.save(notificacion);
    }

    @Override
    public List<Notificacion> findAll() {
        return (List<Notificacion>) notificacionDao.findAll();
    }

    @Override
    public Notificacion findById(Long id) {
        Optional<Notificacion> notificacionOptional = notificacionDao.findById(id);
        return notificacionOptional.orElse(null);
    }
    public void sendNotification(Notificacion notificacion) {
        // Lógica para enviar notificación a través de un proveedor externo
        // Por ejemplo, enviar correo electrónico o notificación push
        System.out.println("Notificación enviada: " + notificacion.getMensaje());
    }
}
