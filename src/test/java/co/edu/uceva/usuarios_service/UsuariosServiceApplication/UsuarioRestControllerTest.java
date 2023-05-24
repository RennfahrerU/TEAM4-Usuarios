package co.edu.uceva.usuarios_service.UsuariosServiceApplication;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.dao.IUsuarioDao;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioRestControllerTest {
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
        public void testListar()throws Exception{
            Usuario usuario1= new Usuario();
            usuario1.setNombre("Carlos");
            Usuario usuario2= new Usuario();
            usuario2.setNombre("Juan");

            usuarioService.save(usuario1);
            usuarioService.save(usuario2);

            List <Usuario> listUser = new ArrayList<>();
            listUser.add(usuario1);
            listUser.add(usuario2);

            this.mockMvc.perform(get("/usuario-service/usuarios"))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", Matchers.is(usuario1.getNombre())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre", Matchers.is(usuario2.getNombre())));
        }



//        @Test
//        public void buscarPorNombreTest() throws Exception {
//            Usuario usuario1 = new Usuario();
//            usuario1.setNombre("Juan");
//
//            Usuario usuario2 = new Usuario();
//            usuario2.setNombre("Pedro");
//
//            List<Usuario> usuarios = new ArrayList<>();
//            usuarios.add(usuario1);
//            usuarios.add(usuario2);
//
//            when(usuarioService.findAllByNombre("Juan")).thenReturn(usuarios);
//
//            mockMvc.perform(get("/usuariosnombre/Juan"))
//                    .andExpect(status().isOk())
//                    .andExpect((ResultMatcher) jsonPath("$[0].nombre", is("Juan")))
//                    .andExpect((ResultMatcher) jsonPath("$[1].nombre", is("Pedro")));
//        }

        private String asJsonString(Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
