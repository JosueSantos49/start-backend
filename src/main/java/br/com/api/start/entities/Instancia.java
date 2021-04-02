package br.com.api.start.entities;

import br.com.api.start.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Instancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull Tipo tipo;
    @ManyToOne
    private @NotNull @Valid Livro livro;

    public Instancia(@NotNull Tipo tipo,
                     @NotNull @Valid Livro livro) {
        this.tipo = tipo;
        this.livro = livro;
    }

    public Long getId() {
        Assert.state(id!=null, "O id está nulo. Chamou o persist?");
        return id;
    }
}
