package com.tutorial.springdatamongodbdynamicqueries.document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "department")
public class Department {

    @NotNull
    private String code;

    private String name;
}
