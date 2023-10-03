package com.gmail.pzalejko.sql.demo.sqldemo.as_usually.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Actor {

    @Id
    @Column(name = "actor_id")
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
