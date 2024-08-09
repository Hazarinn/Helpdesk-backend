package io.github.Hazarinn.repositories;

import io.github.Hazarinn.domain.Chamado;
import io.github.Hazarinn.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
