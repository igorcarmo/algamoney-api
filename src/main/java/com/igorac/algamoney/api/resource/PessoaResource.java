package com.igorac.algamoney.api.resource;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.igorac.algamoney.api.model.Pessoa;
import com.igorac.algamoney.api.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.igorac.algamoney.api.resource.util.ResourceUtils.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listar() {
        return pessoaService.listar();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.criar(pessoa);
        return ResponseEntity.created(resourceToURI(pessoaSalva.getCodigo())).body(pessoa);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Pessoa> pessoa = pessoaService.buscarPeloCodigo(codigo);
        if (pessoa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa.get());
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        pessoaService.remover(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo,
                                            @Valid @RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
            return ResponseEntity.status(HttpStatus.OK).body(pessoaSalva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/{codigo}", consumes = "application/json-patch+json")
    public ResponseEntity<Pessoa> modificar(@PathVariable Long codigo,
                                            @RequestBody JsonPatch jsonPatch) {
        try {
            Pessoa pessoaSalva = pessoaService.modificar(codigo, jsonPatch);
            return ResponseEntity.status(HttpStatus.OK).body(pessoaSalva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
