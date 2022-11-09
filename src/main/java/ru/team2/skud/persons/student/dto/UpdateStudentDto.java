package ru.team2.skud.persons.student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateStudentDto {

    @Transient
    @JsonProperty("first_name")
    private String firstName;

    @Transient
    @JsonProperty("last_name")
    private String lastName;

    @Transient
    @JsonProperty("card_id")
    private Long cardId;
}
