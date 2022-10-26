package ru.team2.skud.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("student")
public class Student {
    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long cardId;

    @Transient
    @JsonIgnore
    private Card card;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
