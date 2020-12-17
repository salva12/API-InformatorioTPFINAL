package com.infor.apirestescalante.controller;

import com.infor.apirestescalante.model.User;
import com.infor.apirestescalante.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.time.*; // Este paquete contiene LocalDate, LocalTime y LocalDateTime.
import java.time.format.*;  // Este paquete contiene DateTimeFormatter.

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //GET Todos los users
    @GetMapping // ~ /api/user
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

     //GET Todos los users de una ciudad
     @GetMapping ("oriundo")
     public ResponseEntity<?> getUsersCiudad(@RequestParam String ciudad) {
        return new ResponseEntity<>(userRepository.findByCiudad(ciudad), HttpStatus.OK);
    }

       //GET Todos los users con fecha de creación mayor a una fecha dada
    @GetMapping ("despues")
    public ResponseEntity<?> getUsersMayorFecha(@RequestParam String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(fecha, formatter);
        return new ResponseEntity<>(userRepository.findByFechaCreacionGreaterThan(formatDateTime), HttpStatus.OK);
    }

    //POST Crear un user
    @PostMapping // ~ /api/user
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setFechaCreacion();
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    //PUT para modificar un user segun ID
    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId, @Valid @RequestBody User user) {
        User userEdit = userRepository.getOne(userId);
        userEdit.setNombre(user.getNombre());
        userEdit.setApellido(user.getApellido());
        userEdit.setPassword(user.getPassword());
        userEdit.setPais(user.getPais());
        userEdit.setProvincia(user.getProvincia());
        userEdit.setCiudad(user.getCiudad());
        userEdit.setEmail(user.getEmail());
        userEdit.setFechaCreacion();
        return new ResponseEntity<>(userRepository.save(userEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User userDelete = userRepository.getOne(userId);
        userRepository.delete(userDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
