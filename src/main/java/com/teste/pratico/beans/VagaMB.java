package com.teste.pratico.beans;

import com.teste.pratico.modelo.Solicitante;
import com.teste.pratico.modelo.Vaga;
import com.teste.pratico.repositorio.VagaRepositorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named(value = "vagaMB")
@ViewScoped
public class VagaMB {

    @Getter
    @Setter
    private List<Vaga> vagas = new ArrayList<>();

    @Getter
    @Setter
    private Vaga vaga;

    @Getter
    @Setter
    private Vaga vagaNova = new Vaga();

    @Autowired
    private VagaRepositorio repositorio;

    @PostConstruct
    public List<Vaga> listarTodos() {
        vagas = repositorio.findAll();
        return vagas;
    }

    public void salvarDocumento() {
        repositorio.save(Vaga.builder()
                        .quantidade(vagaNova.getQuantidade())
                        .inicio(vagaNova.getInicio())
                        .fim(vagaNova.getFim())
                .build());
        vagas = repositorio.findAll();
        vagaNova = new Vaga();
    }

    public void excluir(Vaga vaga) {
        repositorio.delete(vaga);
        vagas = repositorio.findAll();
    }

    public Integer getTamanhoDaLista() {
        return vagas.size();
    }

    public void setTamanhoDaLista(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}
