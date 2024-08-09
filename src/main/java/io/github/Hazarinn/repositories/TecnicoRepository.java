package io.github.Hazarinn.repositories;

import io.github.Hazarinn.domain.Cliente;
import io.github.Hazarinn.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
