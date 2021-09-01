package com.igorac.algamoney.api.service;

import com.igorac.algamoney.api.core.filter.LancamentoFilter;
import com.igorac.algamoney.api.core.objects.LancamentoDto;
import com.igorac.algamoney.api.core.objects.PessoaDto;
import com.igorac.algamoney.api.exception.PessoaInexistenteOuInativaException;
import com.igorac.algamoney.api.model.Lancamento;
import com.igorac.algamoney.api.model.mapper.LancamentoMapper;
import com.igorac.algamoney.api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private LancamentoMapper lancamentoMapper;

    public List<LancamentoDto> listar(LancamentoFilter filter) {
        return lancamentoMapper.entitiesToDtos(lancamentoRepository.filter(filter));
    }

    public Optional<LancamentoDto> buscarPeloCodigo(Long codigo) {
        return lancamentoMapper.entityToDto(lancamentoRepository.findById(codigo));
    }

    public LancamentoDto criar(LancamentoDto lancamento) {
        Optional<PessoaDto> pessoa = pessoaService.buscarPeloCodigo(lancamento.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        Lancamento lancamentoSalvo = lancamentoMapper.dtoToEntity(lancamento);
        return lancamentoMapper.entityToDto(lancamentoRepository.save(lancamentoSalvo));
    }
}
