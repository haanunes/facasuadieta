package br.com.facasuadieta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoTbca;

    @Column(nullable = false, length = 800)
    private String nome;

    @Column(precision = 8, scale = 2)
    private BigDecimal energiaKj;

    @Column(precision = 8, scale = 2)
    private BigDecimal energiaKcal;

    @Column(precision = 8, scale = 2)
    private BigDecimal umidadeG;

    @Column(precision = 8, scale = 2)
    private BigDecimal carboidratoTotalG;

    @Column(precision = 8, scale = 2)
    private BigDecimal carboidratoDisponivelG;

    @Column(precision = 8, scale = 2)
    private BigDecimal proteinaG;

    @Column(precision = 8, scale = 2)
    private BigDecimal proteinaVegetalG;

    @Column(precision = 8, scale = 2)
    private BigDecimal proteinaAnimalG;

    @Column(precision = 8, scale = 2)
    private BigDecimal lipidiosG;

    @Column(precision = 8, scale = 2)
    private BigDecimal fibraAlimentarG;

    @Column(precision = 8, scale = 2)
    private BigDecimal alcoolG;

    @Column(precision = 8, scale = 2)
    private BigDecimal cinzasG;

    @Column(precision = 8, scale = 2)
    private BigDecimal colesterolMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal acidosGraxosSaturadosG;

    @Column(precision = 8, scale = 2)
    private BigDecimal acidosGraxosMonoinsaturadosG;

    @Column(precision = 8, scale = 2)
    private BigDecimal acidosGraxosPoliinsaturadosG;

    @Column(precision = 8, scale = 2)
    private BigDecimal acidosGraxosTransG;

    @Column(precision = 8, scale = 2)
    private BigDecimal calcioMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal ferroMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal sodioMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal magnesioMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal fosforoMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal potassioMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal manganesMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal zincoMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal cobreMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal selenioMcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaAReMcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaARaeMcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaDMcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaEMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal tiaminaMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal riboflavinaMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal niacinaMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaB6Mg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaB12Mcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal vitaminaCMg;

    @Column(precision = 8, scale = 2)
    private BigDecimal equivalenteFolatoMcg;

    @Column(precision = 8, scale = 2)
    private BigDecimal salAdicaoG;

    @Column(precision = 8, scale = 2)
    private BigDecimal acucarAdicaoG;

    @Column(precision = 8, scale = 2)
    private BigDecimal gorduraAdicaoG;
}
