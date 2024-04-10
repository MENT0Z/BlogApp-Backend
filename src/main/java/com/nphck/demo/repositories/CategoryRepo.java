package com.nphck.demo.repositories;

import com.nphck.demo.model_entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Categories,Integer> {
}
