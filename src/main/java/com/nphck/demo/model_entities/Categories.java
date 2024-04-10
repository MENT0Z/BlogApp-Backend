package com.nphck.demo.model_entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "title")
    private String categoryTitle;
    @Column(name = "Description")
    private String categoryDescription;

    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();
}
