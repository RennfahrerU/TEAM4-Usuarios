package co.edu.uceva.usuarios_service.UsuariosServiceApplication.controller;


import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.entities.Usuario;
import co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.service.IUsuarioService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
 public class UsuarioRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private IUsuarioService usuarioService;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testListarUsuario() throws Exception{
        // Datos de prueba
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Carlos");
        usuario1.setApellido("Pérez");
        usuario1.setEmail("carlos@example.com");
        usuario1.setContrasena("password123");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Juan");
        usuario2.setApellido("Gómez");
        usuario2.setEmail("juan@example.com");
        usuario2.setContrasena("password456");

        // Guardar los usuarios en el servicio
        usuarioService.save(usuario1);
        usuarioService.save(usuario2);

        // Realizar la solicitud GET y realizar las verificaciones
        this.mockMvc.perform(get("/usuario-service/listarusuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[8].nombre", is(usuario1.getNombre())))
                .andExpect(jsonPath("$[8].apellido", is(usuario1.getApellido())))
                .andExpect(jsonPath("$[8].email", is(usuario1.getEmail())))
                .andExpect(jsonPath("$[8].contrasena", is(usuario1.getContrasena())))
                .andExpect(jsonPath("$[9].nombre", is(usuario2.getNombre())))
                .andExpect(jsonPath("$[9].apellido", is(usuario2.getApellido())))
                .andExpect(jsonPath("$[9].email", is(usuario2.getEmail())))
                .andExpect(jsonPath("$[9].contrasena", is(usuario2.getContrasena())));
                System.out.println((jsonPath("$[9].nombre", is(usuario1.getNombre()))));
        // Eliminar los usuarios del servicio
        usuarioService.delete(usuario1);
        usuarioService.delete(usuario2);
    }

    @Test
    public void testBuscarPorParteDelNombre() throws Exception {
        // Crea un usuario con nombre y apellido específicos
        Usuario user = new Usuario();
        user.setNombre("Carlos");
        user.setApellido("Perez");
        user.setEmail("carlitos12@hotmail.com");
        user.setContrasena("carlos123");
        usuarioService.save(user);

        // Realiza la búsqueda por la parte del nombre "Car"
        MvcResult result = this.mockMvc.perform(get("/usuario-service/usuarioservice/buscarusuariosnombreyapellido/Car"))
                .andExpect(status().isOk())
                .andReturn();

        // Obtiene la respuesta y verifica que el resultado sea correcto
        String response = result.getResponse().getContentAsString();
        List<Usuario> usuariosEncontrados = new ObjectMapper().readValue(response, new TypeReference<List<Usuario>>() {});

        // Verifica que se encuentre el usuario con nombre "Carlos" en los resultados
        boolean carlosEncontrado = usuariosEncontrados.stream()
                .anyMatch(u -> u.getNombre().equals("Carlos") && u.getApellido().equals("Perez"));
        assertTrue(carlosEncontrado);

        // Borra el usuario creado para el test
        usuarioService.delete(user);
    }


}