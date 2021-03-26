package com.rafhael.barabas.crud.exception;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 3108686680331729608L;

    private Date timestamp;
    private String message;
    private String detail;

}
