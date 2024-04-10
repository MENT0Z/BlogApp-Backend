package com.nphck.demo.payloads;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
