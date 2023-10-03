package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Category {

    @Id
    @Column(name = "category_id")
    Integer id;

    @Column(name = "name")
    String name;
}
