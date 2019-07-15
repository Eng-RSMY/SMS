package com.dream.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.User;
import com.dream.repository.UserRepository;

/**
 * The UserService class
 *
 * @author Dileep
 * @version 1.0
 * Date 20/05/2019.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        Iterable<User> itr = userRepository.findAll();
        return (List<User>) itr;
    }
}
