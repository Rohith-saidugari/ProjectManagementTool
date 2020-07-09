package com.rohith.ppmtool.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class InvalidLoginResponse {

    private String username = "Invalid username";
    private String password = "Invalid password";

}
