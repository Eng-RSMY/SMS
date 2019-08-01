package com.dream.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.User;
import com.dream.repository.UserRepository;
import com.dream.service.UserService;

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

    public boolean save(User user) {
        if(user != null) {
        	userRepository.save(user);
        	return true;
        }
    	return false;
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

	@Override
	public List<User> findAllUsers(String st) {
		List<User> list = new ArrayList<>();
		if(list.size() <= 0) {
			userRepository.findAllUsers().forEach(l -> list.add(l));
		}
		list.forEach(System.out::println);
		return list.stream().filter(s -> s.getName().toLowerCase().contains(st.toLowerCase())).collect(Collectors.toList());
	}
	
	public User findUserByFullName(String name, String lastName) {
		return userRepository.findByNameAndLastName(name, lastName);
	}
}
