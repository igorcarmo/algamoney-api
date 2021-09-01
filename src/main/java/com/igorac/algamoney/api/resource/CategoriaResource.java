package com.igorac.algamoney.api.resource;

import com.igorac.algamoney.api.core.objects.CategoriaDto;
import com.igorac.algamoney.api.model.Categoria;
import com.igorac.algamoney.api.model.mapper.CategoriaMapper;
import com.igorac.algamoney.api.repository.CategoriaRepository;
import com.igorac.algamoney.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.igorac.algamoney.api.resource.util.ResourceUtils.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDto> listar() {
        return categoriaService.listar();
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> criar(@Valid @RequestBody CategoriaDto categoriaDto) {
        CategoriaDto categoriaSalva = categoriaService.criar(categoriaDto);
        return ResponseEntity
            .created(resourceToURI(categoriaSalva.getCodigo()))
            .body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaDto> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<CategoriaDto> categoria = categoriaService.buscarPeloCodigo(codigo);
        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoria.get());
    }

}
