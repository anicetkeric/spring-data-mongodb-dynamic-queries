package com.tutorial.springdatamongodbdynamicqueries.document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @NotNull
    private String code;

    private String name;
}
