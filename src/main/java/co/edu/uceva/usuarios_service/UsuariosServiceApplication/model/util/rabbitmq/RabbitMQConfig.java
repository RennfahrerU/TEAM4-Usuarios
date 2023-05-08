package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.util.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfig {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory.getRabbitConnectionFactory();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
    }

    @Bean
    public Queue notificacionQueue() {
        return new Queue("notificacionQueue");
    }

    @Bean
    public DirectExchange notificacionExchange() {
        return new DirectExchange("notificacionExchange");
    }

    @Bean
    public Binding notificacionBinding(Queue notificacionQueue, DirectExchange notificacionExchange) {
        return BindingBuilder.bind(notificacionQueue).to(notificacionExchange).with("notificacionRoutingKey");
    }
}
