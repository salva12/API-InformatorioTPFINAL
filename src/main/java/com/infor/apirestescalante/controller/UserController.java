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
import java.time.format.*;  // Este paquete contiene DateTimeFormatter.

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /* funcion para cargar la BDD de users
    public void CargarUsers(){
        Long id = 1L;
        LocalDate fecha = LocalDate.now();
        userRepository.deleteAll();
        userRepository.save(new User(id, "Eduardo", "Salvio", "edusalvio@gmail.com", "edusal22", fecha, "Resistencia", "Chaco", "Argentina"));
        id = id +1L;
        userRepository.save(new User(id,"Juan","Delfino","jdelf@gmail.com","delf15",fecha,"Santa Fe","Santa Fe","Argentina"));
        id = id +1L;
        userRepository.save(new User(id,"Carlos","Magno","carlo@gmail.com","carmag",fecha,"Wanda","Misiones","Argentina"));
        id = id +1L;
        userRepository.save(new User(id,"Octavio","Lell","octalel@gmail.com","octalel33",fecha,"Santa Fe","Santa Fe","Argentina"));
        userRepository.findAll().forEach((user) -> {
            System.out.println("Cargué en la BDD a:" + user.getNombre());
        });
    };
    */

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
