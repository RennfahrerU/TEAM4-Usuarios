package co.edu.uceva.usuarios_service.UsuariosServiceApplication;



import co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller.NotificacionesRestController;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.INotificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NotificacionesRestController.class)
public class NotificacionesRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private INotificacionService notificacionService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendNotification() throws Exception {
        // Given
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje("Hola Mundo!");

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"mensaje\":\"Hola Mundo!\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Then
        Mockito.verify(notificacionService, Mockito.times(1)).sendNotification(notificacion);
    }


}
