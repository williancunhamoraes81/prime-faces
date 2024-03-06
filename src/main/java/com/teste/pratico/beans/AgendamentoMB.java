package com.teste.pratico.beans;

import com.teste.pratico.modelo.Agendamento;
import com.teste.pratico.modelo.Solicitante;
import com.teste.pratico.modelo.Vaga;
import com.teste.pratico.repositorio.AgendamentoRepositorio;
import com.teste.pratico.repositorio.SolicitanteRepositorio;
import com.teste.pratico.repositorio.VagaRepositorio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named(value = "agendamentoMB")
@ViewScoped
public class AgendamentoMB {

    @Getter
    @Setter
    private Long idSolicitanteSelecionado;

    @Getter
    @Setter
    private List<Agendamento> agendamentos = new ArrayList<>();

    @Getter
    @Setter
    private List<Solicitante> solicitantes = new ArrayList<>();

    @Getter
    @Setter
    private Agendamento agendamento;

    @Getter
    @Setter
    private Solicitante solicitanteSelecionado;

    @Getter
    @Setter
    private Agendamento agendamentoNovo = new Agendamento();

    @Autowired
    private AgendamentoRepositorio repositorio;

    @Autowired
    private SolicitanteRepositorio solicitanteRepositorio;

    @PostConstruct
    public List<Agendamento> listarTodos() {
        agendamentos = repositorio.findAll();
        return agendamentos;
    }

    @PostConstruct
    public List<Solicitante> listarTodosSolicitantes() {
        solicitantes = solicitanteRepositorio.findAll();
        return solicitantes;
    }

    public void salvarDocumento() {

        int qtdeAgendamentosRealizados = repositorio.countAgendamentosParaData(agendamentoNovo.getData());
        int qtdeVagasDisponiveis = repositorio.getQuantidadeVagasDisponiveisParaData(agendamentoNovo.getData());
        int qtdeVagasSolicitadas = Integer.parseInt(agendamentoNovo.getNumero());

        if (( (qtdeAgendamentosRealizados + qtdeVagasSolicitadas) < qtdeVagasDisponiveis)) {
            Optional<Solicitante> solicitanteOptional = solicitanteRepositorio.findById(idSolicitanteSelecionado);
            if(solicitanteOptional.isPresent()){
                repositorio.save(Agendamento.builder()
                        .data(agendamentoNovo.getData())
                        .numero(agendamentoNovo.getNumero())
                        .motivo(agendamentoNovo.getMotivo())
                        .solicitante(solicitanteOptional.get())
                        .build());
                agendamentos = repositorio.findAll();
                agendamentoNovo = new Agendamento();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Quantidade de agendamentos " +
                    "indisponíveis para data selecionada."));
        }

    }

    public void excluir(Agendamento agendamento) {
        repositorio.delete(agendamento);
        agendamentos = repositorio.findAll();
    }

    public Integer getTamanhoDaLista() {
        return agendamentos.size();
    }

    public void setTamanhoDaLista(Integer size) {
        // Método criado para ser utilizado pelo primefaces
    }

}
