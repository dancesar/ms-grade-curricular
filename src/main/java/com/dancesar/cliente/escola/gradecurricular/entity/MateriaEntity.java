package com.dancesar.cliente.escola.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
public class MateriaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonInclude(Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "nome")
    private String nome;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "hrs")
    private Integer horas;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "cod")
    private String codigo;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "freq")
    private Integer frequencia;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "dataCadastro")
    private LocalDateTime dtCadastro = LocalDateTime.now();
}