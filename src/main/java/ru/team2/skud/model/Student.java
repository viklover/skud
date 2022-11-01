package ru.team2.skud.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@Setter
@Accessors(chain = true)
@Table("student")
public class Student implements Persistable<String> {
    @Id
    private String id;

    @Column("card_id")
    @JsonProperty("card_id")
    private Long cardId;

    @Transient
    @JsonIgnore
    private Card card;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @Transient
    @JsonIgnore
    private boolean isNew;

    public boolean isNew() {
        return isNew;
    }
}
