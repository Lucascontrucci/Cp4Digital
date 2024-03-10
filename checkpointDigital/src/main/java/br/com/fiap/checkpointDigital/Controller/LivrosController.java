package br.com.fiap.checkpointDigital.Controller;

import br.com.fiap.checkpointDigital.Model.Livros;
import br.com.fiap.checkpointDigital.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livros> getAllLivros() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public Livros getLivroById(@PathVariable Long id) {
        return livroService.findById(id);
    }

    @PostMapping
    public Livros addLivro(@RequestBody Livros livro) {
        return livroService.createLivro(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
        Livros livro = livroService.findById(id);
        if (livro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }

        livroService.removerLivro(id);
        return ResponseEntity.ok("Livro removido com sucesso");
    }

    @PutMapping("/{id}")
    public Livros updateLivro(@PathVariable Long id, @RequestBody Livros livro) {
        return livroService.updateLivro(id, livro);
    }

    @GetMapping("/orderByYear")
    public List<Livros> getLivrosPorAno() {
        List<Livros> livros = livroService.orderByYear();
        return livros;
    }

    @GetMapping("/orderByTitle")
    public List<Livros> getLivrosPorTitulo() {
        List<Livros> livros = livroService.orderByTitle();
        return livros;
    }

    @GetMapping("/orderByCategory")
    public List<Livros> getLivrosPorCategoria() {
        List<Livros> livros = livroService.orderByCategory();
        return livros;
    }

    @GetMapping("/findByTitle")
    public List<Livros> buscarPorTitulo(@RequestParam String titulo) {
        return livroService.findByTitle(titulo);
    }

    @GetMapping("/findByAuthor")
    public List<Livros> buscarPorAutor(@RequestParam String autor) {
        return livroService.findByAuthor(autor);
    }

    @GetMapping("/findByCategory")
    public List<Livros> buscarPorCategoria(@RequestParam String categoria) {
        return livroService.findByCategory(categoria);
    }

}
