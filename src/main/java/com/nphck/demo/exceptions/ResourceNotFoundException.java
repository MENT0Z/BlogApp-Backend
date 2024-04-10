package com.nphck.demo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String ResourceName,FieldName;
    long FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with  %s :%s",resourceName,fieldName,fieldValue));
        ResourceName = resourceName;
        FieldName = fieldName;
        FieldValue = fieldValue;
    }


}
