package com.people.app.feature.people.dtos;

import com.people.app.feature.people.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class PersonDTO {
    @NotNull
    private UUID uuid;
    @NotBlank
    private String name;
    @Min(1)
    private double height;
    @NotNull
    private Gender gender;
    @NotBlank
    private String type;

//    private Map<String, Object> attributes;
}
