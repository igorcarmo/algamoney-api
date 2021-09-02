package dev.igorac.algamoney.api.repository;

import dev.igorac.algamoney.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
