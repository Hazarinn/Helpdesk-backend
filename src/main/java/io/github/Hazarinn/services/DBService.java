package io.github.Hazarinn.services;

import io.github.Hazarinn.domain.Chamado;
import io.github.Hazarinn.domain.Cliente;
import io.github.Hazarinn.domain.Tecnico;
import io.github.Hazarinn.domain.enums.Perfil;
import io.github.Hazarinn.domain.enums.Prioridade;
import io.github.Hazarinn.domain.enums.Status;
import io.github.Hazarinn.repositories.ChamadoRepository;
import io.github.Hazarinn.repositories.ClienteRepository;
import io.github.Hazarinn.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired private ChamadoRepository chamadoRepository;



    public void instaciaDB(){
        Tecnico tec1 = new Tecnico(null, "Rubens Diego", "75145341059", "rubens@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "87689323081", "torvalds@gmail.com", "123");

        Chamado c1 =  new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));

        clienteRepository.saveAll(Arrays.asList(cli1));

        chamadoRepository.saveAll(Arrays.asList(c1));

    }
}
