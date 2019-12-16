package com.may.dft.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bicycles {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_b;

    private String name;

    private Integer cost;

    private String description;

    public Integer getId() {
        return id_b;
    }

    public void setId(Integer id_b) {
        this.id_b = id_b;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
