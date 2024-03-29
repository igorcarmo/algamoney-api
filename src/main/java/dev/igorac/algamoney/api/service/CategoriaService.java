package dev.igorac.algamoney.api.service;

import dev.igorac.algamoney.api.core.objects.CategoriaDto;
import dev.igorac.algamoney.api.model.Categoria;
import dev.igorac.algamoney.api.model.mapper.CategoriaMapper;
import dev.igorac.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;

    public List<CategoriaDto> listar() {
        return categoriaMapper.entitiesToDtos(categoriaRepository.findAll());
    }

    public CategoriaDto criar(CategoriaDto categoria) {
        Categoria categoriaSalva = categoriaMapper.dtoToEntity(categoria);
        return categoriaMapper.entityToDto(categoriaRepository.save(categoriaSalva));
    }

    public Optional<CategoriaDto> buscarPeloCodigo(Long codigo) {
        return Optional.ofNullable(categoriaMapper.entityToDto(categoriaRepository.getOne(codigo)));
    }

}
