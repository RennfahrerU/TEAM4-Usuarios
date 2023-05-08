package co.edu.uceva.usuarios_service.UsuariosServiceApplication;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.INotificaionDao;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Notificacion;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.INotificacionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnviarNotificacionTest {
    @Mock
    INotificaionDao notificacionDao;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private INotificacionService notificacionService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveNotificacion() throws Exception {
        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setMensaje("Test mensaje");
       // notificacion.setFecha(new Date());

        when(notificacionDao.save(any(Notificacion.class))).thenReturn(notificacion);

        Notificacion savedNotificacion = notificacionService.save(notificacion);

        assertEquals(notificacion.getMensaje(), savedNotificacion.getMensaje());
      //  assertEquals(notificacion.getFecha(), savedNotificacion.getFecha());
    }

    @Test
    public void testFindAllNotificaciones() throws Exception {
        Notificacion notificacion1 = new Notificacion();
        notificacion1.setId(1L);
        notificacion1.setMensaje("Test mensaje 1");
      //  notificacion1.setFecha(new Date());

        Notificacion notificacion2 = new Notificacion();
        notificacion2.setId(2L);
        notificacion2.setMensaje("Test mensaje 2");
       // notificacion2.setFecha(new Date());

        List<Notificacion> notificaciones = new ArrayList<>();
        notificaciones.add(notificacion1);
        notificaciones.add(notificacion2);

        when(notificacionDao.findAll()).thenReturn(notificaciones);

        mockMvc.perform(get("/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].mensaje", is("Test mensaje 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].mensaje", is("Test mensaje 2")));
    }

    // y asi con el resto de los metodos
}
