package io.github.Hazarinn.services;


import io.github.Hazarinn.domain.Pessoa;
import io.github.Hazarinn.domain.Tecnico;
import io.github.Hazarinn.domain.dtos.TecnicoDTO;
import io.github.Hazarinn.repositories.PessoaRepository;
import io.github.Hazarinn.repositories.TecnicoRepository;
import io.github.Hazarinn.services.exceptions.DataIntegrityViolationException;
import io.github.Hazarinn.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));

    }

    public List<Tecnico> findAll() {

        return repository.findAll();
    }

    // Para criar um novo técnico, é preciso converter um tecnicoDTO para tecnico
    //Pois o que é pesistido no banco é a entidade e não o DTO
    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEemail(objDTO);
    Tecnico newObj = new Tecnico(objDTO);
    return repository.save(newObj);

    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        if(!objDTO.getSenha().equals(oldObj.getSenha())){

        }
        validaPorCpfEemail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }

    private void validaPorCpfEemail(TecnicoDTO objDTO) {


        // Validação pelo cpf
        Optional<Pessoa> obj =  pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");

        }

        obj =  pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");

        }
    }


    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");

        }
        repository.deleteById(id);
    }
}
