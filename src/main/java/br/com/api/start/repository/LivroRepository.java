package br.com.api.start.repository;

import br.com.api.start.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends org.springframework.data.repository.Repository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

}
