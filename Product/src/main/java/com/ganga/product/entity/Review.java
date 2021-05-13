package com.ganga.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NotNull
    private  String subject;
    @NotNull
    private  String body;

}
