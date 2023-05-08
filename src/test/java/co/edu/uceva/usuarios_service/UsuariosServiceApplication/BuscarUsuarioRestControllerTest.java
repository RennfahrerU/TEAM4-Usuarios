package co.edu.uceva.usuarios_service.UsuariosServiceApplication;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.IUsuarioDao;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BuscarUsuarioRestControllerTest {
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class UsuarioServiceTest {

        @Mock
        private IUsuarioDao usuarioDao;

        @Autowired
        private IUsuarioService usuarioService;
        private MockMvc mockMvc;
        private WebApplicationContext wac;

        @Before
        public void setUp() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        }

        @Test
        public void buscarPorNombreTest() throws Exception {
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Juan");

            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Pedro");

            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuario1);
            usuarios.add(usuario2);

            when(usuarioService.findAllByNombre("Juan")).thenReturn(usuarios);

            mockMvc.perform(get("/usuariosnombre/Juan"))
                    .andExpect(status().isOk())
                    .andExpect((ResultMatcher) jsonPath("$[0].nombre", is("Juan")))
                    .andExpect((ResultMatcher) jsonPath("$[1].nombre", is("Pedro")));
        }

        /*@Test
        public void buscarPorNombre_deberiaDevolverListaDeUsuariosConNombreBuscado() {
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Juan"));
            usuarios.add(new Usuario("Pedro"));
            usuarios.add(new Usuario("Juan"));
            Mockito.when(usuarioDao.findAllByNombre("Juan")).thenReturn(usuarios);

            List<Usuario> usuariosEncontrados = usuarioService.findAllByNombre("Juan");
            assertEquals(2, usuariosEncontrados.size());
            assertEquals("Juan", usuariosEncontrados.get(0).getNombre());
            assertEquals("Juan", usuariosEncontrados.get(1).getNombre());
        }*/

        private String asJsonString(Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
