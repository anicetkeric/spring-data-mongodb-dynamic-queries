package com.tutorial.springdatamongodbdynamicqueries.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @NonNull
    private String code;

    private String name;
}
