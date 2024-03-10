package br.com.fiap.checkpointDigital.Service;

import br.com.fiap.checkpointDigital.Model.Livros;
import br.com.fiap.checkpointDigital.Repository.LivroRepository;
import br.com.fiap.checkpointDigital.Service.Classificacao.ClassificacaoStrategy;
import br.com.fiap.checkpointDigital.Service.Classificacao.ClassificarPorAno;
import br.com.fiap.checkpointDigital.Service.Classificacao.ClassificarPorCategoria;
import br.com.fiap.checkpointDigital.Service.Classificacao.ClassificarPorTitulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    private ClassificacaoStrategy classificacaoStrategy;

    @Autowired
    private ClassificarPorAno classificarPorAno;

    @Autowired
    private ClassificarPorTitulo classificarPorTitulo;

    @Autowired
    private ClassificarPorCategoria classificarPorCategoria;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    public Livros createLivro(Livros livros) {
        return livroRepository.save(livros);
    }
    public List<Livros> findAll(){
        return livroRepository.findAll();
    }

    public Livros updateLivro(Long id, Livros livro) {
        Optional<Livros> livroOptional = livroRepository.findById(id);
        if (livroOptional.isPresent()) {

            Livros existingLivro = livroOptional.get();

            existingLivro.setTitulo(livro.getTitulo());
            existingLivro.setAutor(livro.getAutor());
            existingLivro.setAnoPublicacao(livro.getAnoPublicacao());
            existingLivro.setCategoria(livro.getCategoria());

            return livroRepository.save(existingLivro);
        }
        return null;
    }
    public boolean removerLivro(Long id){
        return livroRepository.findById(id).map(livro -> {
            livroRepository.delete(livro);
            return true;
        }).orElse(false);
    }

    public Livros findById(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public List<Livros> orderByYear() {
        List<Livros> livros = findAll();
        return classificarPorAno.classificar(livros);
    }

    public List<Livros> orderByTitle() {
        List<Livros> livros = findAll();
        return classificarPorTitulo.classificar(livros);
    }

    public List<Livros> orderByCategory() {
        List<Livros> livros = findAll();
        return classificarPorCategoria.classificar(livros);
    }

    public List<Livros> findByTitle(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livros> findByAuthor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Livros> findByCategory(String categoria) {
        return livroRepository.findByCategoriaContainingIgnoreCase(categoria);
    }


}
