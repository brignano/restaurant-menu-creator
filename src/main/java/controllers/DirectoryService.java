/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Paolo
 */
@Service
public class DirectoryService {
    
    @Autowired
    private UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(DirectoryService.class);
    
    public UserClass saveUser(UserClass userClass){
        return getUserRepository().saveAndFlush(userClass);
    }
    
    public void deleteUser(Long id) {
        getUserRepository().delete(id);
    }
    
    public UserClass findUser(Long id) {
        return getUserRepository().findOne(id);
    }

    /**
     * @return the userRepository
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * @param userRepository the userRepository to set
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
