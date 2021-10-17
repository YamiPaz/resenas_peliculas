package com.example.parcialp.services;

import com.example.parcialp.entities.Usuario;
import com.example.parcialp.repositories.BaseRepository;
import com.example.parcialp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository){ super(baseRepository);   }

}