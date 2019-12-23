/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.springframework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import guru.springframework.domain.Users;

/**
 *
 * @author didin
 */
public interface UserRepository extends MongoRepository<Users, String> {
    
    Users findByEmail(String email);
    
}
