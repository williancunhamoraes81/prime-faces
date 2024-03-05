package com.teste.pratico.beans;

import com.teste.pratico.modelo.Solicitante;
import com.teste.pratico.repositorio.SolicitanteRepositorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "solicitanteMB")
@ViewScoped
public class SolicitanteMB {

    @Getter
    @Setter
    private List<Solicitante> solicitantes = new ArrayList<>();

    @Getter
    @Setter
    private Solicitante solicitante;

    @Getter
    @Setter
    private Solicitante solicitanteNovo = new Solicitante();

    @Autowired
    private SolicitanteRepositorio repositorio;

    @PostConstruct
    public List<Solicitante> listarTodos() {
        solicitantes = repositorio.findAll();
        return solicitantes;
    }

    public void salvarDocumento() {
        if(solicitanteNovo.getNome() == null || solicitanteNovo.getNome() == ""){
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O nome é obrigatório."));
        }else{
            repositorio.save(Solicitante.builder().nome(solicitanteNovo.getNome()).build());
            solicitantes = repositorio.findAll();
            solicitanteNovo = new Solicitante(null,null);
        }
    }

    public void excluir(Solicitante solicitante) {
        repositorio.delete(solicitante);
        solicitantes = repositorio.findAll();
    }

    public Integer getTamanhoDaLista() {
        return solicitantes.size();
    }

    public void setTamanhoDaLista(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }
}
