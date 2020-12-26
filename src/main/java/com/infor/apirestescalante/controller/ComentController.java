package com.infor.apirestescalante.controller;

import com.infor.apirestescalante.model.Comentario;
import com.infor.apirestescalante.repository.ComentRepository;
import com.infor.apirestescalante.model.Post;
import com.infor.apirestescalante.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.management.MXBean;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.*; // Este paquete contiene LocalDate, LocalTime y LocalDateTime.

@RestController
@RequestMapping("/api/post")
public class ComentController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentRepository comentarioRepository;

    // Crear un nuevo comentario asociado a un post específico
    @PostMapping("/{id_post}/coment")
    public ResponseEntity<?> createComent(@PathVariable Long id_post, @RequestBody Comentario comentario) {
        //1 -Buscar el post a comentar.

        Post post = postRepository.getOne(id_post);

        //2- Buscar autor del comentario.
        String emailAutorComent = comentario.getAutor();

        //3 - Crear comentario y asociar al post.
        Comentario coment = new Comentario();
        coment.setAutor(emailAutorComent); // asocio al autor del post
        coment.setComentario(comentario.getComentario());
        coment.setFechaCreacion();
        coment.setPost(post);
        return new ResponseEntity<>(comentarioRepository.save(coment), HttpStatus.CREATED);
    }

    
    //PUT para modificar un comentario segun ID
    @PutMapping("coment/{comentId}")
    public ResponseEntity<?> editComent(@PathVariable Long comentId, @RequestBody Comentario coment) {
        Comentario comentEdit = comentarioRepository.getOne(comentId);
        if ((coment.getComentario())!= null){
            comentEdit.setComentario(coment.getComentario());
        }   
        comentEdit.setFechaCreacion();
        return new ResponseEntity<>(comentarioRepository.save(comentEdit), HttpStatus.OK);
    }

    //DELETE Borrar un comentario segun el ID
    @DeleteMapping("coment/{comentId}")
    public ResponseEntity<?> deleteComent(@PathVariable Long comentId) {
        Comentario comentDelete = comentarioRepository.getOne(comentId);
        comentarioRepository.delete(comentDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GET Todos los comentarios de un post con limite
    @GetMapping ("/{id_post}/coment/maximo")
    public ResponseEntity<?> getComentsMax(@PathVariable Post id_post,@RequestParam Integer max) {
        List<Comentario> result = comentarioRepository.findByPost(id_post,PageRequest.of(0, max)).getContent();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}