package br.com.facasuadieta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapingStatusDTO {
    private boolean emAndamento;
    private int paginaAtual;
    private int totalPaginas;
    private int totalAlimentosProcessados;
    private int totalAlimentosEncontrados;
    private double percentualConcluido;
    private String mensagem;
}