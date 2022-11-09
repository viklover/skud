package ru.team2.skud.persons.parent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateParentDto {

    @Transient
    @JsonProperty("first_name")
    private String firstName;

    @Transient
    @JsonProperty("last_name")
    private String lastName;

    @Transient
    @JsonProperty("students")
    private List<String> studentsId;

    @Transient
    @JsonProperty("telephone_number")
    private String telephoneNumber;
}
