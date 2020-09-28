package br.com.springspec.demospecification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Pageable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmeDto {
    @JsonProperty(value = "tituloOriginal")
    private String tituloOriginal;

    @JsonProperty(value = "tituloTraduzido")
    private String tituloTraduzido;

    @JsonProperty(value = "dataLancamento")
    private String dataLancamento;

    @JsonProperty(value = "genero")
    private String genero;

    @JsonProperty(value = "nomeDiretor")
    private String nomeDiretor;

    @JsonProperty(value = "flagMaiorIdade")
    private String flagMaiorIdade;

    @JsonProperty(value = "pageable")
    private Pageable pageable;
}