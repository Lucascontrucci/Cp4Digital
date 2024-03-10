package br.com.fiap.checkpointDigital.Service.Classificacao;

import br.com.fiap.checkpointDigital.Model.Livros;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassificarPorCategoria implements ClassificacaoStrategy{

    @Override
    public List<Livros> classificar(List<Livros> livros) {
        return livros.stream()
                .sorted(Comparator.comparing(Livros::getCategoria))
                .collect(Collectors.toList());
    }
}
