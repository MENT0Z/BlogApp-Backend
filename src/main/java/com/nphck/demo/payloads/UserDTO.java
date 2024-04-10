package com.nphck.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    @NotEmpty
    @Size(min=4,message = "User Name must be of minimum of length 4")
    private String name;
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 16,message = "password should be minimum of 4 and max 16")
    private String password;
    @NotEmpty
    private String about;
}
