package com.nphck.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDTO> postDTOList;
    private int pageNumber;
    private int PageSize;
    private long total_elements;
    private int total_pages;
    private boolean lastPage;
}
