package com.rohith.ppmtool.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectNotFoundExceptionResponse {
    private String projectNotFound;
}
