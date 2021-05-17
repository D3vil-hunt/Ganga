package com.ganga.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String category;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(	name = "category_recommendation",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name = "recommendation_id", referencedColumnName="ID"))
    private Set<Recommendation> recommendations;
}
