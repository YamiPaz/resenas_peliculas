package com.example.parcialp.ServicesTests;

import com.example.parcialp.entities.Pelicula;
import com.example.parcialp.entities.Usuario;
import com.example.parcialp.repositories.UsuarioRepository;
import com.example.parcialp.services.UsuarioServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Autowired
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    private Usuario usuario1;
    private Usuario usuario2;
    private Pelicula pelicula1;
    private Pelicula pelicula2;
    private Pelicula pelicula3;
    List<Usuario> usuarios;
    List<Pelicula> peliculas1;
    List<Pelicula> peliculas2;

    @BeforeEach
    public void crearEntidades(){
       //3 peliculas, 2 listas
        pelicula1= Pelicula.builder().titulo("Titanic").calificacion(8).resena("No es mi intención analizar aquí el argumento de la película, no es este el foro idóneo para eso, pero sí tomaré como punto de partida la historia de amor que tiene enredados a Jack, un buscavidas bohemio perteneciente a la tercera clase del Titanic, y a Rose, una joven de primera clase que viaja en el transatlántico huyendo de su propia realidad.").build();
        pelicula2= Pelicula.builder().titulo("La vida es bella").calificacion(7).resena("Sin duda merece ser vista, es triste pero deja un pensamiento sobre cómo vemos la vida y sobre qué poder tienen nuestros pensamientos ante aquellas situaciones que nos acomplejan.").build();
        pelicula3= Pelicula.builder().titulo("El rey león").calificacion(8).resena("Aquellos que quieran ver la misma película de hace 25 años, saldrán desconcertados por la falta de humanización en la apariencia de los personajes y en los números musicales, pero quienes tengan la disposición de verla como película por sí misma, se encontrarán con una maravilla técnica y con un divertido y emotivo relato sobre familia, pérdida, crecimiento y asunción.").build();
        peliculas1=new ArrayList<>();
        peliculas2=new ArrayList<>();
        peliculas1.add(pelicula1);
        peliculas1.add(pelicula2);
        peliculas2.add(pelicula3);
        //2 usuarios, 1 lista
        usuario1= Usuario.builder().nick("Lola54").peliculas(peliculas1).build();
        usuario2=Usuario.builder().nick("Pepin7").peliculas(peliculas2).build();
        usuario1.setId(1L);
        usuario2.setId(2L);
        pelicula1.setId(1L);
        pelicula2.setId(2L);
        pelicula3.setId(3L);
        usuarios= new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
    }

    @AfterEach
    public void borrarEntidades(){
        usuario1=usuario2=null;
        pelicula1=pelicula2=pelicula3=null;
        usuarios=null;
        peliculas1=peliculas2=null;
    }

    @Test
    public void saveTest() throws Exception {
        when(usuarioRepository.save(any())).thenReturn(usuario1);
        usuarioService.save(usuario1);
        verify(usuarioRepository, times(1)).save(any());
    }
    @Test
    public void findAllTest() throws Exception {
        usuarioRepository.save(usuario1);
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> usuarios1 = usuarioService.findAll();
        assertEquals(usuarios1, usuarios);
        verify(usuarioRepository,times (1)).save(usuario1);
        verify(usuarioRepository,times(1)).findAll();
    }

    @Test
    public void findByIdTest() throws Exception {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.ofNullable(usuario1));
        assertThat(usuarioService.findById(usuario1.getId())).isEqualTo(usuario1);
    }


}
