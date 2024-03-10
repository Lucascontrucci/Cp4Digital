package br.com.fiap.checkpointDigital.Repository;

import br.com.fiap.checkpointDigital.Model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livros,Long> {

    List<Livros> findByTituloContainingIgnoreCase(String titulo);
    List<Livros> findByAutorContainingIgnoreCase(String autor);
    List<Livros> findByCategoriaContainingIgnoreCase(String categoria);
}
