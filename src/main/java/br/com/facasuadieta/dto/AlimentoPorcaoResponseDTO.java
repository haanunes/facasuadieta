package br.com.facasuadieta.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlimentoPorcaoResponseDTO {

    private Long id;
    private String nome;
    private Double gramasPorcao;

    private BigDecimal energiaKj;
    private BigDecimal energiaKcal;
    private BigDecimal umidadeG;
    private BigDecimal carboidratoTotalG;
    private BigDecimal carboidratoDisponivelG;
    private BigDecimal proteinaG;
    private BigDecimal proteinaVegetalG;
    private BigDecimal proteinaAnimalG;
    private BigDecimal lipidiosG;
    private BigDecimal fibraAlimentarG;
    private BigDecimal alcoolG;
    private BigDecimal cinzasG;
    private BigDecimal colesterolMg;
    private BigDecimal acidosGraxosSaturadosG;
    private BigDecimal acidosGraxosMonoinsaturadosG;
    private BigDecimal acidosGraxosPoliinsaturadosG;
    private BigDecimal acidosGraxosTransG;
    private BigDecimal calcioMg;
    private BigDecimal ferroMg;
    private BigDecimal sodioMg;
    private BigDecimal magnesioMg;
    private BigDecimal fosforoMg;
    private BigDecimal potassioMg;
    private BigDecimal manganesMg;
    private BigDecimal zincoMg;
    private BigDecimal cobreMg;
    private BigDecimal selenioMcg;
    private BigDecimal vitaminaAReMcg;
    private BigDecimal vitaminaARaeMcg;
    private BigDecimal vitaminaDMcg;
    private BigDecimal vitaminaEMg;
    private BigDecimal tiaminaMg;
    private BigDecimal riboflavinaMg;
    private BigDecimal niacinaMg;
    private BigDecimal vitaminaB6Mg;
    private BigDecimal vitaminaB12Mcg;
    private BigDecimal vitaminaCMg;
    private BigDecimal equivalenteFolatoMcg;
    private BigDecimal salAdicaoG;
    private BigDecimal acucarAdicaoG;
    private BigDecimal gorduraAdicaoG;
}