package com.example.stanley.usermanagement.controller;

import com.example.stanley.usermanagement.models.Users;
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
public class UserController{
    @Autowired
    UserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Users> saveUser(@RequestBody Users user){
        try {
            //String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            //user.setPassword(encodedPassword);
            Users response = userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUser(@PathVariable long id){
        try{
            Optional<Users> user = userRepository.findById(id);
            return ResponseEntity.of(user);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable long id){
        try {
            return userRepository.findById(id)
                    .map(u -> {
                        u.setUsername(user.getUsername());
                        u.setEmail(user.getEmail());
                        Users response = userRepository.save(u);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }).orElseGet(() -> {
                        user.setId(id);
                        Users response = userRepository.save(user);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    });
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Users> deleteUser(@PathVariable long id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
