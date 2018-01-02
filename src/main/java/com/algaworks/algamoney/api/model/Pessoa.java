package com.algaworks.algamoney.api.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @Embedded
    private Endereco endereco;

    @NotNull
    private Boolean ativo;

}
