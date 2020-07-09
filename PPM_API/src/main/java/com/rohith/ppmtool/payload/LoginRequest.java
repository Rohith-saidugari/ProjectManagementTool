package com.rohith.ppmtool.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message ="username cannot be blank")
    private String username;
    @NotBlank(message ="password cannot be blank")
    private String password;
}
