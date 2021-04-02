package br.com.api.start.controler;

import br.com.api.start.entities.Instancia;
import br.com.api.start.model.Livro;
import br.com.api.start.model.NovaInstanciaRequest;
import br.com.api.start.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class NovaInstanciaController {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EntityManager manager;

    @PostMapping(value = "/livro/{isbn}/intancias")
    @Transactional
    public ResponseEntity<?> executa(@PathVariable("isbn") String isbn, @RequestBody @Valid NovaInstanciaRequest request){
       Optional<Livro> possivelLivro = livroRepository.findByIsbn(isbn);
        if(possivelLivro.isPresent()){

            Instancia novaInstancia = request.toModel(possivelLivro.get());
            manager.persist(novaInstancia);

            return ResponseEntity.ok(novaInstancia.getId());
        }
        return ResponseEntity.notFound().build();

    }
}
