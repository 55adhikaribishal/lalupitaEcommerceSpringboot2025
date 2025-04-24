package com.example.SpringbootEcommerce2025.dto;
//adding comments
import lombok.Data;

//@Data commenting
public class CatgeoryDto {
    private Long id;

    private String name;

    private String description;

    public CatgeoryDto() {
    }

    public CatgeoryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
