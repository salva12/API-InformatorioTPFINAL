package com.infor.apirestescalante.controller;

import com.infor.apirestescalante.model.User;
import com.infor.apirestescalante.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.*; // Este paquete contiene LocalDate, LocalTime y LocalDateTime.


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //GET Todos los users
    @GetMapping // ~ /api/user
    public ResponseEntity<?> getUsers() {
        List<User> usersSinPass = new ArrayList<User>();
        userRepository.findAll().forEach((user)->{
            user.setPassword("***");
            System.out.println(user);
            usersSinPass.add(user);
        });
        return new ResponseEntity<>(usersSinPass, HttpStatus.OK);
    }

     //GET Todos los users de una ciudad
     @GetMapping ("oriundo")
     public ResponseEntity<?> getUsersCiudad(@RequestParam String ciudad) {
        List<User> usersSinPass = new ArrayList<User>();
        userRepository.findByCiudad(ciudad).forEach((user)->{
            user.setPassword("***");
            System.out.println(user);
            usersSinPass.add(user);
        });
        return new ResponseEntity<>(usersSinPass, HttpStatus.OK);
    }

    //GET Todos los users con fecha de creación mayor a una fecha dada
    @GetMapping ("despues")
    public ResponseEntity<?> getUsersDespuesFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<User> usersSinPass = new ArrayList<User>();
        userRepository.findByFechaCreacionIsAfter(fecha).forEach((user)->{
            user.setPassword("***");
            System.out.println(user);
            usersSinPass.add(user);
        });
        return new ResponseEntity<>(usersSinPass, HttpStatus.OK);
    }

    //POST Crear un usuario
    @PostMapping // ~ /api/user
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setFechaCreacion();
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    //PUT para modificar un usuario segun ID
    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId, @RequestBody User user) {
        User userEdit = userRepository.getOne(userId);
        System.out.println(userEdit);
        if ((user.getNombre())!= null){
            userEdit.setNombre(user.getNombre());
        }   
        if ((user.getApellido())!= null){
            userEdit.setApellido(user.getApellido());
        }
        if ((user.getPassword())!= null){
            userEdit.setPassword(user.getPassword());
        }
        if ((user.getPais())!= null){
            userEdit.setPais(user.getPais());
        }
        if ((user.getProvincia())!= null){
            userEdit.setProvincia(user.getProvincia());
        }
        if ((user.getCiudad())!= null){
            userEdit.setCiudad(user.getCiudad());
        }
        if ((user.getEmail())!= null){
            userEdit.setEmail(user.getEmail());
        }
        userEdit.setFechaCreacion();
        return new ResponseEntity<>(userRepository.save(userEdit), HttpStatus.OK);
    }

    //DELETE Borrar un usuario segun el ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User userDelete = userRepository.getOne(userId);
        userRepository.delete(userDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
