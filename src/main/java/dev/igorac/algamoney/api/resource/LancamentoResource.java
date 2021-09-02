package dev.igorac.algamoney.api.resource;

import dev.igorac.algamoney.api.core.filter.LancamentoFilter;
import dev.igorac.algamoney.api.core.objects.LancamentoDto;
import dev.igorac.algamoney.api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static dev.igorac.algamoney.api.resource.util.ResourceUtils.resourceToURI;

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

}
