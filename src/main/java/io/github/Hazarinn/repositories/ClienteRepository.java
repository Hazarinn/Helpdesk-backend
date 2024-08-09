package io.github.Hazarinn.repositories;

import io.github.Hazarinn.domain.Cliente;
import io.github.Hazarinn.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
