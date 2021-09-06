package dev.igorac.algamoney.api.service;

import dev.igorac.algamoney.api.core.Paging;
import dev.igorac.algamoney.api.core.filter.LancamentoFilter;
import dev.igorac.algamoney.api.core.objects.CategoriaDto;
import dev.igorac.algamoney.api.core.objects.LancamentoDto;
import dev.igorac.algamoney.api.core.objects.PessoaDto;
import dev.igorac.algamoney.api.exception.CategoriaInexistenteException;
import dev.igorac.algamoney.api.exception.PessoaInexistenteOuInativaException;
import dev.igorac.algamoney.api.model.Lancamento;
import dev.igorac.algamoney.api.model.mapper.LancamentoMapper;
import dev.igorac.algamoney.api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private LancamentoMapper lancamentoMapper;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private CategoriaService categoriaService;

    public List<LancamentoDto> listar(LancamentoFilter filter, Paging page) {
        return lancamentoMapper.entitiesToDtos(lancamentoRepository.filter(filter, page));
    }

    public Optional<LancamentoDto> buscarPeloCodigo(Long codigo) {
        return lancamentoMapper.entityToDto(lancamentoRepository.findById(codigo));
    }

    public LancamentoDto criar(LancamentoDto lancamento) {
        Optional<PessoaDto> pessoa = pessoaService.buscarPeloCodigo(lancamento.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        if (lancamento.getCategoria() != null && lancamento.getCategoria().getCodigo() != null) {
            Optional<CategoriaDto> categoria = categoriaService.buscarPeloCodigo(lancamento.getCategoria().getCodigo());
            if (categoria.isEmpty()) {
                throw new CategoriaInexistenteException();
            }
        }

        Lancamento lancamentoSalvo = lancamentoMapper.dtoToEntity(lancamento);
        return lancamentoMapper.entityToDto(lancamentoRepository.save(lancamentoSalvo));
    }

    public void remover(Long idLancamento) {
        if (idLancamento == null) throw new IllegalArgumentException("Referência ao lançamento não pode ser nula");

        lancamentoRepository.deleteById(idLancamento);
    }
}
