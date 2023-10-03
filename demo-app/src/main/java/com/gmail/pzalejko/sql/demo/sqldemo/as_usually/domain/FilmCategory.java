package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@ToString
public class FilmCategory {

    @Id
    @Column(name = "category_id")
    Integer categoryId;

    @Column(name = "film_id")
    Integer filmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Category category;
}
