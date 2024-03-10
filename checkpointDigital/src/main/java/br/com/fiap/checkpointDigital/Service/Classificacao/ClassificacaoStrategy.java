package br.com.fiap.checkpointDigital.Service.Classificacao;

import br.com.fiap.checkpointDigital.Model.Livros;

import java.util.List;

public interface ClassificacaoStrategy {

    List<Livros> classificar(List<Livros> livros);
}
