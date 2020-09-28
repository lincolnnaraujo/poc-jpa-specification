package br.com.springspec.demospecification.service;

import br.com.springspec.demospecification.dto.FilmeDto;
import br.com.springspec.demospecification.model.Filme;
import br.com.springspec.demospecification.repository.FilmeRepository;
import br.com.springspec.demospecification.repository.FilmeRepositoryCriteriaImpl;
import br.com.springspec.demospecification.specification.FilmeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    private final FilmeRepositoryCriteriaImpl filmeRepositoryCriteria;

    FilmeService(FilmeRepository filmeRepository,
                 FilmeRepositoryCriteriaImpl filmeRepositoryCriteria){
        this.filmeRepository = filmeRepository;
        this.filmeRepositoryCriteria = filmeRepositoryCriteria;
    }

    public List<Filme> listaFilmes(){
        Specification<Filme> specs = Specification.where(
                FilmeSpecification.orderByNotaImdb()
        );

        return filmeRepository.findAll(specs);
    }

    public List<Filme> buscarFilmesPorParametros(FilmeDto filmeDto){
        Specification<Filme> specs = Specification.where(
                Objects.requireNonNull(FilmeSpecification.likeTituloOriginal(filmeDto.getTituloOriginal())
                        .and(FilmeSpecification.likeTituloTraduzido(filmeDto.getTituloTraduzido())))
                        .and(FilmeSpecification.likeGenero(filmeDto.getGenero()))
                        .and(FilmeSpecification.likeNomeDiretor(filmeDto.getNomeDiretor()))
                        .and(FilmeSpecification.equalFlagMaiorIdade(filmeDto.getFlagMaiorIdade()))
                        .and(FilmeSpecification.orderByNotaImdb())
        );
        return filmeRepository.findAll(specs);
    }

    public Page<Filme> paginaFilme(FilmeDto filmeDto){
        Specification<Filme> specs = Specification.where(
                FilmeSpecification.likeTituloOriginal(filmeDto.getTituloOriginal())
                        .and(FilmeSpecification.likeTituloTraduzido(filmeDto.getTituloTraduzido()))
                        .and(FilmeSpecification.likeGenero(filmeDto.getGenero()))
                        .and(FilmeSpecification.likeNomeDiretor(filmeDto.getNomeDiretor()))
                        .and(FilmeSpecification.equalFlagMaiorIdade(filmeDto.getFlagMaiorIdade()))
                        .and(FilmeSpecification.orderByNotaImdb())
        );

        return filmeRepository.findAll(specs, filmeDto.getPageable());
    }

    public BigDecimal somatorioBilheteria(FilmeDto filmeDto){

        Specification<Filme> specs = Specification.where(
                FilmeSpecification.likeNomeDiretor(filmeDto.getNomeDiretor())
        );

        return filmeRepositoryCriteria.somatorioValorBilheteria(specs);
    }

}
