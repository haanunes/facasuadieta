package br.com.facasuadieta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlimentoRequestDTO {

    @NotBlank(message = "O nome do alimento é obrigatório")
    private String nome;

    @NotNull(message = "O valor de energia em Kcal é obrigatório")
    @PositiveOrZero(message = "A energia deve ser um valor positivo ou zero")
    private BigDecimal energiaKcal;

    @PositiveOrZero
    private BigDecimal energiaKj;

    @PositiveOrZero
    private BigDecimal umidadeG;

    @PositiveOrZero
    private BigDecimal carboidratoTotalG;

    @PositiveOrZero
    private BigDecimal carboidratoDisponivelG;

    @PositiveOrZero
    private BigDecimal proteinaG;

    @PositiveOrZero
    private BigDecimal proteinaVegetalG;

    @PositiveOrZero
    private BigDecimal proteinaAnimalG;

    @PositiveOrZero
    private BigDecimal lipidiosG;

    @PositiveOrZero
    private BigDecimal fibraAlimentarG;

    @PositiveOrZero
    private BigDecimal alcoolG;

    @PositiveOrZero
    private BigDecimal cinzasG;

    @PositiveOrZero
    private BigDecimal colesterolMg;

    @PositiveOrZero
    private BigDecimal acidosGraxosSaturadosG;

    @PositiveOrZero
    private BigDecimal acidosGraxosMonoinsaturadosG;

    @PositiveOrZero
    private BigDecimal acidosGraxosPoliinsaturadosG;

    @PositiveOrZero
    private BigDecimal acidosGraxosTransG;

    @PositiveOrZero
    private BigDecimal calcioMg;

    @PositiveOrZero
    private BigDecimal ferroMg;

    @PositiveOrZero
    private BigDecimal sodioMg;

    @PositiveOrZero
    private BigDecimal magnesioMg;

    @PositiveOrZero
    private BigDecimal fosforoMg;

    @PositiveOrZero
    private BigDecimal potassioMg;

    @PositiveOrZero
    private BigDecimal manganesMg;

    @PositiveOrZero
    private BigDecimal zincoMg;

    @PositiveOrZero
    private BigDecimal cobreMg;

    @PositiveOrZero
    private BigDecimal selenioMcg;

    @PositiveOrZero
    private BigDecimal vitaminaAReMcg;

    @PositiveOrZero
    private BigDecimal vitaminaARaeMcg;

    @PositiveOrZero
    private BigDecimal vitaminaDMcg;

    @PositiveOrZero
    private BigDecimal vitaminaEMg;

    @PositiveOrZero
    private BigDecimal tiaminaMg;

    @PositiveOrZero
    private BigDecimal riboflavinaMg;

    @PositiveOrZero
    private BigDecimal niacinaMg;

    @PositiveOrZero
    private BigDecimal vitaminaB6Mg;

    @PositiveOrZero
    private BigDecimal vitaminaB12Mcg;

    @PositiveOrZero
    private BigDecimal vitaminaCMg;

    @PositiveOrZero
    private BigDecimal equivalenteFolatoMcg;

    @PositiveOrZero
    private BigDecimal salAdicaoG;

    @PositiveOrZero
    private BigDecimal acucarAdicaoG;

    @PositiveOrZero
    private BigDecimal gorduraAdicaoG;
}
