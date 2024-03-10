package br.com.fiap.checkpointDigital.Service.Classificacao;

import br.com.fiap.checkpointDigital.Model.Livros;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassificarPorAno implements ClassificacaoStrategy{
    @Override
    public List<Livros> classificar(List<Livros> livros) {
        return livros.stream()
                .sorted(Comparator.comparingInt(Livros::getAnoPublicacao).reversed())
                .collect(Collectors.toList());
    }
}
