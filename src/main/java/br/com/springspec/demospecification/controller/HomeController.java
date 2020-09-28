package br.com.springspec.demospecification.controller;

import br.com.springspec.demospecification.dto.FilmeDto;
import br.com.springspec.demospecification.model.Filme;
import br.com.springspec.demospecification.service.FilmeService;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/app")
public class HomeController {

    private final Slack slack = Slack.getInstance();
    private static final String SLACK_TOKEN = "xoxb-1372680953409-1404067346704-VRkr7sQAytMoDxdnIWuVYNRW";
    private final MethodsClient methods = slack.methods(SLACK_TOKEN);
    private static final String SLACK_EMOJI_WAVE = ":wave:";
    private static final String SLACK_EMOJI_CAVEIRA = ":skull_and_crossbones:";
    private static final String SLACK_CHANNEL = "geral";

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final FilmeService filmeService;

    HomeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @GetMapping("/")
    public String home(){
        return "Testanto JPA Specification";
    }

    @GetMapping("/listar-filmes")
    public List<Filme> retornarListaFilmes(){
        List<Filme> filmes = filmeService.listaFilmes();

        log.info("Quantida de de filmes encontrados: {}", filmes.stream().count());

        log.trace("Filmes: {}", filmes.stream().distinct());

        return filmes;
    }

    @PostMapping("/listar-filmes-parametros")
    public ResponseEntity<?> retornarFilmesPorParametros(@RequestBody FilmeDto filmeDto){
        try{
            List<Filme> filmes = filmeService.buscarFilmesPorParametros(filmeDto);

            this.enviarMensagemSlack(SLACK_CHANNEL, "\n Sucesso na consulta com os parâmetros: \n" + filmeDto.toString(), SLACK_EMOJI_WAVE);

            return ResponseEntity.ok(filmes);
        }catch (Exception e){
            log.error(e.toString());
            this.enviarMensagemSlack(SLACK_CHANNEL, "\n Parâmetros da consulta: \n" + filmeDto + " \n com erro: \n" + e.toString(), SLACK_EMOJI_CAVEIRA);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/listar-filmes-parametros-pagina")
    public Page<Filme> retornarFilmesPorParametrosPagina(@RequestBody FilmeDto filmeDto, Pageable pageable){
        pageable= PageRequest.of(1, 10);
        filmeDto.setPageable(pageable);
        return filmeService.paginaFilme(filmeDto);
    }

    private void enviarMensagemSlack(String channel, String mensagem, String emoji) {
        try{
            ChatPostMessageRequest request = ChatPostMessageRequest.builder().token(SLACK_TOKEN)
                    .channel(channel)
                    .iconEmoji(emoji)
                    .text(emoji + mensagem)
                    .build();

            methods.chatPostMessage(request);
        }catch (IOException e){
            log.error(e.toString());
        }catch (SlackApiException e){
            log.error(e.toString());
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
