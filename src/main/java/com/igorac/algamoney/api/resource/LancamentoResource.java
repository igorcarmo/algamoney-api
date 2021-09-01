package com.igorac.algamoney.api.resource;

import com.igorac.algamoney.api.core.filter.LancamentoFilter;
import com.igorac.algamoney.api.core.objects.LancamentoDto;
import com.igorac.algamoney.api.exception.AlgamoneyExceptionHandler;
import com.igorac.algamoney.api.exception.PessoaInexistenteOuInativaException;
import com.igorac.algamoney.api.model.Lancamento;
import com.igorac.algamoney.api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.igorac.algamoney.api.resource.util.ResourceUtils.resourceToURI;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<LancamentoDto> listar(LancamentoFilter filter) {
        return lancamentoService.listar(filter);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoDto> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<LancamentoDto> lancamento = lancamentoService.buscarPeloCodigo(codigo);
        if (lancamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lancamento.get());
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> criar(@Valid @RequestBody LancamentoDto lancamento) {
        LancamentoDto lancamentoSalvo = lancamentoService.criar(lancamento);
        URI uri = resourceToURI(lancamentoSalvo.getCodigo());
        return ResponseEntity
            .created(uri)
            .body(lancamentoSalvo);
    }

    @ExceptionHandler({ PessoaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDev = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Collections.singletonList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDev));
        return ResponseEntity.badRequest().body(erros);
    }

}
