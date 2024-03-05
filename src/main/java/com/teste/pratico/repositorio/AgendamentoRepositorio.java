package com.teste.pratico.repositorio;

import com.teste.pratico.modelo.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepositorio extends JpaRepository<Agendamento, Long>{
}
