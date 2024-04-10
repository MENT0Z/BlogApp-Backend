package com.nphck.demo.services.implem;

import com.nphck.demo.exceptions.ResourceNotFoundException;
import com.nphck.demo.model_entities.Categories;
import com.nphck.demo.payloads.CategoryDTO;
import com.nphck.demo.repositories.CategoryRepo;
import com.nphck.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImple implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public CategoryDTO CreateCategory(CategoryDTO categoryDTO) {
        Categories categories = this.modelMapper.map(categoryDTO,Categories.class);
        Categories addedCat = this.categoryRepo.save(categories);
        return this.modelMapper.map(addedCat,CategoryDTO.class);
    }

    @Override
    public CategoryDTO UpdateCategory(CategoryDTO categoryDTO, Integer Catid) {
        Categories categories = this.categoryRepo.findById(Catid).orElseThrow(()->new ResourceNotFoundException("Category","id",Catid));
        categories.setCategoryTitle(categoryDTO.getCategoryTitle());
        categories.setCategoryDescription(categoryDTO.getCategoryDescription());
        Categories updatedCat = this.categoryRepo.save(categories);
        return this.modelMapper.map(updatedCat,CategoryDTO.class) ;
    }
    @Override
    public void delete_category(Integer catid) {
        Categories categories = this.categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("Category","id",catid));
        this.categoryRepo.delete(categories);
    }

    @Override
    public CategoryDTO get_ele_by_id(Integer catid) {
        return this.modelMapper.map(this.categoryRepo.findById(catid).orElseThrow(()-> new ResourceNotFoundException("Category","id",catid)),CategoryDTO.class) ;

    }

    @Override
    public List<CategoryDTO> get_all() {
        List<Categories>categories = this.categoryRepo.findAll();
        List<CategoryDTO>catDTO=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDTO.class)).collect(Collectors.toList());
        return catDTO;
    }
}
