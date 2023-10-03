package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@ToString
public class FilmActor {

    @Id
    @Column(name = "actor_id")
    Integer categoryId;

    @Column(name = "film_id")
    Integer filmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Actor actor;
}
