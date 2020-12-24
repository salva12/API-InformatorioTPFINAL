package com.infor.apirestescalante.repository;

import com.infor.apirestescalante.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTituloContaining(String palabra);
    List<Post> findByPublicado(Boolean publicado);
}