package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@ToString
public class Film {

    @Id
    @Column(name = "film_id")
    Integer id;

    @Column(name = "length")
    int length;

    @Column(name = "title")
    String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    FilmCategory filmCategory;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    List<FilmActor> actors;
}
