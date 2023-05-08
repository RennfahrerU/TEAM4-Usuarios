package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.INotificacionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificaciones")
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionesRestController {

    /**
     * Se dejo en comentario ya que por recomencacion del profesor se pospone esta implementacion
     */

    /* @Autowired
     INotificacionService NotificacionService;
    @Autowired
    private RabbitTemplate rabbitTemplate;



    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody Notificacion notificacion) {
        rabbitTemplate.convertAndSend("direct-exchange", "notificaciones-routing-key", notificacion);
        return ResponseEntity.ok().build();
    }*/
}


