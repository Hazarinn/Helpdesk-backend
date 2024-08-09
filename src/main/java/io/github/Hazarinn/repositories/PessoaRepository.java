package io.github.Hazarinn.repositories;

import io.github.Hazarinn.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
