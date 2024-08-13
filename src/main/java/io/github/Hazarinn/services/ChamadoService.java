package io.github.Hazarinn.services;

import io.github.Hazarinn.domain.Chamado;
import io.github.Hazarinn.domain.Cliente;
import io.github.Hazarinn.domain.Tecnico;
import io.github.Hazarinn.domain.dtos.ChamadoDTO;
import io.github.Hazarinn.domain.enums.Prioridade;
import io.github.Hazarinn.domain.enums.Status;
import io.github.Hazarinn.repositories.ChamadoRepository;
import io.github.Hazarinn.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));

    }

    private Chamado newChamado(ChamadoDTO obj) {

        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());
        Chamado chamado = new Chamado();
        if (obj.getId() != null){
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;


    }
}
