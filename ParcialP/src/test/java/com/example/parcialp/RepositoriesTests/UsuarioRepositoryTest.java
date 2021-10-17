package com.example.parcialp.RepositoriesTests;

import com.example.parcialp.entities.Pelicula;
import com.example.parcialp.entities.Usuario;
import com.example.parcialp.repositories.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //tell spring to not replace the datasource.
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    private Pelicula pelicula;
    List<Pelicula> peliculas;


    @BeforeEach
    public void crearEntidades(){
        pelicula= new Pelicula("101 dálmatas", "Esta muy divertida, hace ya tiempo que la vi, pero recuerdo un pequeño detalle: las líneas están como recién hechas a lápiz, y en mal estado.", 6);
        peliculas=new ArrayList<>();
        peliculas.add(pelicula);
        usuario= new Usuario("T1t1", peliculas);
    }

    @AfterEach
    public void borrarEntidades(){
        usuarioRepository.deleteAll();
        usuario=null;
        pelicula=null;
        peliculas=null;
    }

    @Test
    public void saveTest(){ //se debe vaciar la tabla para que el id sea 0 por ser generatedvalue
        usuarioRepository.save(usuario);
        Usuario usuarioObtenido= usuarioRepository.findById(usuario.getId()).get();
        assertEquals(1, usuarioObtenido.getId());
    }

    @Test
    public void findAllTest(){
        Usuario usuario1= Usuario.builder().nick("Charly").peliculas(peliculas).build();
        Usuario usuario2= Usuario.builder().nick("Samy632").peliculas(peliculas).build();
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        List<Usuario> usuarioList= (List<Usuario>) usuarioRepository.findAll();
        assertEquals("Samy632", usuarioList.get(1).getNick());
    }

    @Test
    public void findByIdTest(){
        Usuario usuario1= Usuario.builder().nick("Charly").peliculas(peliculas).build();
        Usuario usuario2=usuarioRepository.save(usuario1);
        Optional<Usuario> optional = usuarioRepository.findById(usuario2.getId());
        assertEquals(usuario2.getId(), optional.get().getId());
        assertEquals(usuario2.getNick(), optional.get().getNick());
    }

    @Test
    public void deleteTest(){
        usuarioRepository.save(usuario);
        usuarioRepository.deleteById(usuario.getId());
        Optional optional= usuarioRepository.findById(usuario.getId());
        assertEquals(Optional.empty(), optional);
    }
}
