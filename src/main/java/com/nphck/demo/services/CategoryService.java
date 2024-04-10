package com.nphck.demo.services;

import com.nphck.demo.payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    // create
    CategoryDTO CreateCategory(CategoryDTO categoryDTO);
    //update
    CategoryDTO UpdateCategory(CategoryDTO categoryDTO,Integer Catid);
    //delete
    void delete_category(Integer catid);
    //get by id
    CategoryDTO get_ele_by_id(Integer catid);
    //getall
    List<CategoryDTO>get_all();

}
