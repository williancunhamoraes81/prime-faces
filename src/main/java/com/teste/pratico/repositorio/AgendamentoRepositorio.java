package com.teste.pratico.repositorio;

import com.teste.pratico.modelo.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AgendamentoRepositorio extends JpaRepository<Agendamento, Long>{
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.data = :data")
    int countAgendamentosParaData(@Param("data") Date data);

    @Query("SELECT v.quantidade FROM Vaga v WHERE v.inicio <= :data AND v.fim >= :data")
    int getQuantidadeVagasDisponiveisParaData(@Param("data") Date data);

}
