package com.brisbiere.Brisbiere.persistence;


import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.repository.UserRepository;
import com.brisbiere.Brisbiere.persistence.crud.UsuarioCrudRepository;
import com.brisbiere.Brisbiere.persistence.entity.Usuario;
import com.brisbiere.Brisbiere.persistence.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<User> getUser(int userId) {
        return usuarioCrudRepository.findById(userId).map(usuario -> userMapper.toUser(usuario));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        User user = userMapper.toUser(usuarioCrudRepository.hasAccount(email));
        return Optional.of(user);
    }

    @Override
    public void save(User user) {
        Usuario usuario = userMapper.toUsuario(user);
        usuarioCrudRepository.save(usuario);
    }

    @Override
    public void delete(int userId) {
        usuarioCrudRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public Optional<User> hasAccount(String email) {
        return Optional.ofNullable(userMapper.toUser(this.usuarioCrudRepository.hasAccount(email)));
    }
}

