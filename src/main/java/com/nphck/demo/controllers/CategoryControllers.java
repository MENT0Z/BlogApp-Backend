package com.nphck.demo.controllers;

import com.nphck.demo.payloads.ApiResponse;
import com.nphck.demo.payloads.CategoryDTO;
import com.nphck.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryControllers {
    @Autowired
    private CategoryService categoryService;


    // GET BY ID
    @GetMapping("/{CatId}")
    public ResponseEntity<CategoryDTO>getCategoryById(@PathVariable Integer CatId){
        CategoryDTO categoryDTO1 = this.categoryService.get_ele_by_id(CatId);
        return ResponseEntity.ok(categoryDTO1);
    }
    //POST
    @PostMapping("/")
    public ResponseEntity<CategoryDTO>createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = this.categoryService.CreateCategory(categoryDTO);
        return new ResponseEntity<CategoryDTO>(categoryDTO1, HttpStatus.CREATED);
    }
    //PUT
    @PutMapping("/{CatId}")
    public ResponseEntity<CategoryDTO>UpdateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable("CatId")Integer catid){
        CategoryDTO categoryDTO1 = this.categoryService.UpdateCategory(categoryDTO,catid);
        return ResponseEntity.ok(categoryDTO1);
    }
    //DELETE
    @DeleteMapping("/{Catid}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer Catid){
        this.categoryService.delete_category(Catid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Sucessfully",true),HttpStatus.OK);
    }

    // GET ALL
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>>getAllCategories(){
        return ResponseEntity.ok(this.categoryService.get_all());
    }
}
