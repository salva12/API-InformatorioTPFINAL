package com.infor.apirestescalante.controller;

import com.infor.apirestescalante.model.Post;
import com.infor.apirestescalante.repository.PostRepository;
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
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    //GET Obtener todos los posts
    @GetMapping // ~ /api/post
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    //POST Crear un post
    @PostMapping("nuevo/autor") 
    public ResponseEntity<?> createPost(@RequestBody Post post,@RequestParam Long idAutor) {
        if (userRepository.getOne(idAutor)!=null) {
            post.setFechaCreacion();
            post.setAutor(userRepository.getOne(idAutor));
            postRepository.save(post);
            return new ResponseEntity<>(postRepository.save(post),HttpStatus.CREATED);  
        }   
        else{
            return new ResponseEntity<>("id de autor inexistente",HttpStatus.NOT_ACCEPTABLE);  
        }
    }

     //PUT para modificar un post segun ID
    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @RequestBody Post post) {
        Post postEdit = postRepository.getOne(postId);
        if ((post.getTitulo())!= null){
            postEdit.setTitulo(post.getTitulo());
        }   
        if ((post.getDescripcion())!= null){
            postEdit.setDescripcion(post.getDescripcion());
        }
        if ((post.getContenido())!= null){
            postEdit.setContenido(post.getContenido());
        }
        if ((post.getPublicado())!= null){
            postEdit.setPublicado(post.getPublicado());
        }
        postEdit.setFechaCreacion();
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    //DELETE Borrar un post segun el ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GET Todos los posts que contenga en su titulo una palabra dada
    @GetMapping ("titulo")
    public ResponseEntity<?> getPostsTitulo(@RequestParam String palabra) {
        return new ResponseEntity<>(postRepository.findByTituloContaining(palabra), HttpStatus.OK);
    }

     //GET Todos los posts que hayan sido publicados
     @GetMapping ("publicado")
    public ResponseEntity<?> getPostsPublicados(@RequestParam Boolean publicado) {
        return new ResponseEntity<>(postRepository.findByPublicado(publicado), HttpStatus.OK);
    }

}