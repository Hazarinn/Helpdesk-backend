package io.github.Hazarinn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.Hazarinn.domain.dtos.ClienteDTO;
import io.github.Hazarinn.domain.dtos.TecnicoDTO;
import io.github.Hazarinn.domain.enums.Perfil;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        this.id = obj.getId();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao =  obj.getDataCriacao();
        this.senha =  obj.getSenha();
        this.email =  obj.getEmail();
        this.cpf =  obj.getCpf();
        this.nome =  obj.getNome();
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
