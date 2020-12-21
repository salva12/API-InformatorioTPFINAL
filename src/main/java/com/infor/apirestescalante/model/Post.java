package com.infor.apirestescalante.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank

    @Size(min = 4)
    private String titulo;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="autor", referencedColumnName = "id")
    private User autor;

    @Size(min = 4)
    @Size(max = 300)
    private String descripcion;

    @Size(min = 4)
    @Size(max = 600)
    private String contenido;

    private Boolean publicado;

    private LocalDate fechaCreacion;

    @OneToMany
    private List<Comentario> comentarios = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
    }
    
    public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
    }
    
    public Boolean getPublicado() {
		return this.publicado;
	}

	public void setPublicado(Boolean publicado) {
		this.publicado = publicado;
  }
    
  public LocalDate getFechaCreacion() {
		return this.fechaCreacion;
  }
  
  public void setFechaCreacion() {
		this.fechaCreacion = LocalDate.now();
  } 
}
