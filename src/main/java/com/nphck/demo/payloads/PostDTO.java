package com.nphck.demo.payloads;

import com.nphck.demo.model_entities.Categories;
import com.nphck.demo.model_entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String imagename;
    private Date addedDate;
    private CategoryDTO categories;
    private UserDTO user;
}
