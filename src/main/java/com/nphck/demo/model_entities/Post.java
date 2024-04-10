package com.nphck.demo.model_entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="post")
@Setter
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(nullable = false)
    private String title;
    @Column(length = 100000)
    private String content;
    private String imagename;
    private Date addedDate;

    // for the relation stuffs
    @ManyToOne
    @JoinColumn()
    private Categories categories;
    @ManyToOne
    private User user;

}
