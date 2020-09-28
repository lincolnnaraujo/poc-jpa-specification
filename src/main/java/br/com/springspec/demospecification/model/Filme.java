package br.com.springspec.demospecification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FILME")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    public Filme(Long id, String nomeTraduzido, String nomeDiretor, String genero){
        this.id = id;
        this.tituloTraduzido = nomeTraduzido;
        this.nomeDiretor = nomeDiretor;
        this.genero = genero;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "TITULO_ORIGINAL")
    private String tituloOriginal;

    @Column(name = "TITULO_TRADUZIDO")
    private String tituloTraduzido;

    @Column(name = "NOME_DIRETOR")
    private String nomeDiretor;

    @Column(name = "DATA_LANCAMENTO")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "FLAG_MAIOR_IDADE")
    private String flagMaiorIdade;

    @Column(name = "VALOR_BILHETERIA")
    private BigDecimal valorBilheteria;

    @Column(name = "NOTA_IMDB")
    private BigDecimal notaImdb;
}
