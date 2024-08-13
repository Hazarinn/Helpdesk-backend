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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Rubens Diego", "99964737084", "rubens@gmail.com", encoder.encode("123"));
        Tecnico tec2 = new Tecnico( null, "Zeca Manel", "29360078018", "zeca@gmail.com", encoder.encode("321"));
        Tecnico tec3 = new Tecnico(null, "Janja Maria", "59175601028", "janja@gmail.com", encoder.encode("456"));
        Tecnico tec4 = new Tecnico(null, "Isabel Cristina", "68156423054", "isabel@gmail.com", encoder.encode("789"));
        Tecnico tec5 = new Tecnico(null, "Saulo Ícaro", "35517961061", "saulo@gmail.com", encoder.encode("101"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "63086061030", "torvalds@gmail.com", encoder.encode("147"));
        Cliente cli2 = new Cliente(null, "Milano", "90689827024", "milano@gmail.com", encoder.encode("258"));
        Cliente cli3 = new Cliente(null, "Jupiter", "30820514055", "jupiter@gmail.com", encoder.encode("369"));
        Cliente cli4 = new Cliente(null, "Amendoim", "25092828080", "amendoim@gmail.com", encoder.encode("741"));
        Cliente cli5 = new Cliente(null, "Pretao", "42969428083", "pretao@gmail.com", encoder.encode("159"));

        Chamado c1 =  new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli2);
        Chamado c2 =  new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Primeiro chamado", tec2, cli3);
        Chamado c3 =  new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 03", "Primeiro chamado", tec1, cli4);
        Chamado c4 =  new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 04", "Primeiro chamado", tec3, cli1);
        Chamado c5 =  new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 05", "Primeiro chamado", tec1, cli5);
        Chamado c6 =  new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 06", "Primeiro chamado", tec1, cli2);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

    }
}
