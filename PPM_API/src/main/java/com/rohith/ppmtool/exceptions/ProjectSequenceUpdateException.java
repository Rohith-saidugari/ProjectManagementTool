package com.rohith.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectSequenceUpdateException extends RuntimeException {
    public ProjectSequenceUpdateException() {
        super("Project Sequence Update not permitted ");
    }
}
