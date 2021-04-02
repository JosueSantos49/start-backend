package br.com.api.start.model;

import br.com.api.start.entities.Instancia;
import br.com.api.start.entities.Tipo;
import br.com.api.start.repository.LivroRepository;

import javax.validation.constraints.NotNull;

public class NovaInstanciaRequest {

    @NotNull
    private Tipo tipo;

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Instancia toModel(Livro livro) {
        return new Instancia(tipo, livro);
    }
}
