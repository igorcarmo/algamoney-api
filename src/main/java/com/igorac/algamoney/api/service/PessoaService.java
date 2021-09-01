package com.igorac.algamoney.api.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.igorac.algamoney.api.core.objects.PessoaDto;
import com.igorac.algamoney.api.model.Pessoa;
import com.igorac.algamoney.api.model.mapper.PessoaMapper;
import com.igorac.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.igorac.algamoney.api.service.util.ServiceUtils.applyPatch;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaMapper pessoaMapper;

    public List<PessoaDto> listar() {
        return pessoaMapper.entitiesToDtos(pessoaRepository.findAll());
    }

    public PessoaDto criar(PessoaDto pessoaDto) {
        Pessoa pessoa = pessoaMapper.dtoToEntity(pessoaDto);
        return pessoaMapper.entityToDto(pessoaRepository.save(pessoa));
    }

    public Optional<PessoaDto> buscarPeloCodigo(Long codigo) {
        return pessoaMapper.entityToDto(pessoaRepository.findById(codigo));
    }

    public void remover(Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    public PessoaDto atualizar(Long codigo, PessoaDto pessoa) {
        PessoaDto pessoaAntes = pessoaMapper.entityToDto(pessoaRepository.findById(codigo)).orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(pessoa, pessoaAntes, "codigo");
        Pessoa pessoaDepois = pessoaRepository.save(pessoaMapper.dtoToEntity(pessoaAntes));
        return pessoaMapper.entityToDto(pessoaDepois);
    }

    public PessoaDto modificar(Long codigo, JsonPatch pessoaPatch) throws JsonPatchException {
        PessoaDto pessoaAntes = pessoaMapper.entityToDto(pessoaRepository.findById(codigo))
            .orElseThrow(IllegalArgumentException::new);
        PessoaDto pessoaDepois = applyPatch(pessoaPatch, pessoaAntes, PessoaDto.class);
        Pessoa pessoaSalva = pessoaRepository.save(pessoaMapper.dtoToEntity(pessoaDepois));
        return pessoaMapper.entityToDto(pessoaSalva);
    }
}
