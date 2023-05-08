package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.util.rabbitmq;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.INotificacionService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Component
public class NotificacionConsumer {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionConsumer.class);

    @Autowired
    INotificacionService notificacionService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "notificaciones.queue", durable = "true"),
            exchange = @Exchange(value = "notificaciones.exchange", type = ExchangeTypes.TOPIC),
            key = "notificaciones.key"))
    public void receiveMessage(Notificacion notificacion) {
        logger.info("Received message: {}", notificacion);
        notificacionService.save(notificacion);
    }
}
