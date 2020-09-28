package br.com.springspec.demospecification.specification;

import br.com.springspec.demospecification.model.Filme;
import br.com.springspec.demospecification.model.Filme_;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmeSpecification {

    public static Specification<Filme> equalsId(Long id) {
        if (id == null) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(Filme_.ID), id);
    }

    public static Specification<Filme> likeTituloOriginal(String tituloOriginal) {
        if (tituloOriginal == null || Strings.isBlank(tituloOriginal)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Filme_.TITULO_ORIGINAL)), tituloOriginal.toLowerCase().trim());
    }

    public static Specification<Filme> likeTituloTraduzido(String tituloTraduzido) {
        if (tituloTraduzido == null || Strings.isBlank(tituloTraduzido)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Filme_.TITULO_TRADUZIDO)), "%" + tituloTraduzido.trim().toLowerCase() + "%");
    }

    public static Specification<Filme> likeNomeDiretor(String nomeDiretor) {
        if (nomeDiretor == null || Strings.isBlank(nomeDiretor)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Filme_.NOME_DIRETOR)), "%" + nomeDiretor.trim().toLowerCase() + "%");
    }

    public static Specification<Filme> equalDataLancamento(LocalDate dataLancamento) {
        if (dataLancamento == null || Strings.isBlank(dataLancamento.toString())) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(Filme_.DATA_LANCAMENTO), dataLancamento);
    }

    public static Specification<Filme> btweenDataLancamento(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null && dataFim == null) {
            return null;
        }
        if (dataInicio != null && dataFim == null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Filme_.DATA_LANCAMENTO), dataInicio);
        }
        if (dataInicio == null && dataFim != null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Filme_.DATA_LANCAMENTO), dataFim);
        }
        return (root, query, cb) -> cb.between(root.get(Filme_.DATA_LANCAMENTO), dataInicio, dataFim);
    }

    public static Specification<Filme> likeGenero(String genero) {
        if (genero == null || Strings.isBlank(genero)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Filme_.GENERO)), "%" + genero.toLowerCase().trim() + "%");
    }

    public static Specification<Filme> equalFlagMaiorIdade(String flag) {
        if (flag == null || Strings.isBlank(flag)) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(Filme_.FLAG_MAIOR_IDADE), flag);
    }

    public static Specification<Filme> btweenValorBilheteria(BigDecimal valorInicio, BigDecimal valorFim) {
        if (valorInicio == null && valorFim == null) {
            return null;
        }
        if (valorInicio != null && valorFim == null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Filme_.VALOR_BILHETERIA), valorInicio);
        }
        return (root, query, cb) -> cb.between(root.get(Filme_.VALOR_BILHETERIA), valorInicio, valorFim);
    }

    public static Specification<Filme> btweenNotaImdb(BigDecimal notaInicial, BigDecimal notaFinal) {
        if (notaInicial == null && notaFinal == null) {
            return null;
        }
        if (notaInicial != null && notaFinal == null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Filme_.NOTA_IMDB), notaInicial);
        }
        return (root, query, cb) -> cb.between(root.get(Filme_.NOTA_IMDB), notaInicial, notaFinal);
    }

    public static Specification<Filme> orderByNotaImdb() {
        return (Specification<Filme>) (root, query, cb) -> {

            List<Order> orderBy = new ArrayList<>();
            orderBy.add(cb.asc(root.get(Filme_.NOTA_IMDB)));
            orderBy.add(cb.asc(root.get(Filme_.ID)));

            List<Predicate> predicates = new ArrayList<>();
            query.orderBy(orderBy);

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}