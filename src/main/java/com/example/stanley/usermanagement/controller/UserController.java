package com.example.stanley.usermanagement.controller;

import com.example.stanley.usermanagement.models.User;
import com.example.stanley.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try {
            //String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            //user.setPassword(encodedPassword);
            User response = userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable long id){
        try{
            Optional<User> user = userRepository.findById(id);
            return ResponseEntity.of(user);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id){
        try {
            return userRepository.findById(id)
                    .map(u -> {
                        u.setUsername(user.getUsername());
                        u.setEmail(user.getEmail());
                        User response = userRepository.save(u);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }).orElseGet(() -> {
                        user.setId(id);
                        User response = userRepository.save(user);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    });
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteUser(@PathVariable long id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
