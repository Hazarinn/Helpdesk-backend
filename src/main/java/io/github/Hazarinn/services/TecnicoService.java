package io.github.Hazarinn.services;


import io.github.Hazarinn.domain.Tecnico;
import io.github.Hazarinn.repositories.TecnicoRepository;
import io.github.Hazarinn.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));

    }

    public List<Tecnico> findAll() {

        return repository.findAll();
    }
}
