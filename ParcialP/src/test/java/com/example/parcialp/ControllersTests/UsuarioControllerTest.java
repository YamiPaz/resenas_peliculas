package com.example.parcialp.ControllersTests;

import com.example.parcialp.controllers.UsuarioController;
import com.example.parcialp.entities.Pelicula;
import com.example.parcialp.entities.Usuario;
import com.example.parcialp.services.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioServiceImpl usuarioService;
    private Usuario usuario;
    private List<Usuario> usuarioList;
    private Pelicula pelicula;
    List<Pelicula> peliculas;

    @InjectMocks
    private UsuarioController usuarioController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void crearEntidades() {
        pelicula = Pelicula.builder().titulo("El rey león").calificacion(8).resena("Aquellos que quieran ver la misma película de hace 25 años, saldrán desconcertados por la falta de humanización en la apariencia de los personajes y en los números musicales, pero quienes tengan la disposición de verla como película por sí misma, se encontrarán con una maravilla técnica y con un divertido y emotivo relato sobre familia, pérdida, crecimiento y asunción.").build();
        pelicula.setId(1L);
        peliculas = new ArrayList<>();
        peliculas.add(pelicula);
        usuario = Usuario.builder().nick("T1t1").peliculas(peliculas).build();
        usuario.setId(1L);
        mockMvc= MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @AfterEach
    public void borrarEntidades() {
        usuario = null;
        pelicula = null;
        peliculas = null;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void usuarioPostMappingTest() throws Exception {
        when(usuarioService.save(any())).thenReturn(usuario);
        mockMvc.perform(post("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuario))).andExpect(status().isCreated());
        verify(usuarioService, times(1)).save(any());
    }

    @Test
    public void usuarioGetMappingAll() throws Exception {
        when(usuarioService.findAll()).thenReturn(usuarioList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/usuarios").contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuario))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(usuarioService, times(1)).findAll();
    }

    @Test
    public void usuarioGetMappingId() throws Exception {
        when(usuarioService.findById(usuario.getId())).thenReturn(usuario);
        mockMvc.perform(get("/api/v1/usuarios/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuario))).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(usuarioService, times(1)).findById(any());
    }

    @Test
    public void usuarioDeleteMapping() throws Exception {
        when(usuarioService.delete(usuario.getId())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/usuarios/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(usuario))).andExpect(MockMvcResultMatchers.status().isNoContent()).andDo(MockMvcResultHandlers.print());
        verify(usuarioService, times(1)).delete(any());
    }

}
