package br.com.api.start.model;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String titulo;
    private @NotNull @Positive BigDecimal preco;
    @Column(unique = true)
    private @NotBlank @ISBN(type = ISBN.Type.ISBN_10) String isbn;

    public Livro(){
    }

    public Livro(@NotBlank String titulo,
                 @NotNull @Positive BigDecimal preco,
                 @NotBlank @ISBN(type = ISBN.Type.ISBN_10) String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public Long getId() {
        Assert.state(id!=null, "Não pode chamar o getId do livro com o id nulo. Será que você já persistiu?");
        return id;
    }
}
