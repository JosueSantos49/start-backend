package br.com.api.start.model;

import br.com.api.start.compartilhado.UniqueValue;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovoLivroRequest {

    @NotBlank
    private String titulo;
    @NotNull
    @Positive
    private BigDecimal preco;
    @NotBlank
    @ISBN(type = ISBN.Type.ISBN_10)
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn") //erro de validacao de registro -> nao deixar duplicar
    private String isbn;

    public NovoLivroRequest(@NotBlank String titulo,
                            @NotNull @Positive BigDecimal preco,
                            @NotBlank @ISBN(type = ISBN.Type.ISBN_10) String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public Livro toModel() {
        Assert.state(titulo!=null, "Titulo neste momento nao pode ser nulo... Será que você esqueceu de colocar um NotBlank");
        return new Livro(titulo, preco, isbn);
    }
}
