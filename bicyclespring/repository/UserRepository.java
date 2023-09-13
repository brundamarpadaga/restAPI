package com.prodapt.bicyclespring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.bicyclespring.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    public Optional<User> findByName(String username);
}
