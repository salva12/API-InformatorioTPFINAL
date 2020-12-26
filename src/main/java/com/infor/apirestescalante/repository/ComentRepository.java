package com.infor.apirestescalante.repository;

import com.infor.apirestescalante.model.Comentario;
import com.infor.apirestescalante.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.*;
import java.util.List;

@Repository
public interface ComentRepository extends JpaRepository<Comentario, Long> {
    Page<Comentario> findByPost(Post id_post, Pageable pageable);
}