package com.prodapt.bicyclespring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.bicyclespring.entity.User;
import com.prodapt.bicyclespring.exception.CycleShopBusinessException;
import com.prodapt.bicyclespring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String password) throws CycleShopBusinessException {
        Optional<User> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
            throw new CycleShopBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public User create(User user) {
        return userRepository.save(user);
    }
	

}
