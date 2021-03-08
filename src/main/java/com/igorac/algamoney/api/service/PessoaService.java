package com.igorac.algamoney.api.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.igorac.algamoney.api.model.Pessoa;
import com.igorac.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.igorac.algamoney.api.resource.util.ResourceUtils.applyPatch;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPeloCodigo(Long codigo) {
        return pessoaRepository.findById(codigo);
    }

    public void remover(Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElseThrow(IllegalArgumentException::new);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public Pessoa modificar(Long codigo, JsonPatch patch) throws JsonPatchException {
        Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElseThrow(IllegalArgumentException::new);
        pessoaSalva = applyPatch(patch, pessoaSalva, Pessoa.class);
        return pessoaRepository.save(pessoaSalva);
    }
}
