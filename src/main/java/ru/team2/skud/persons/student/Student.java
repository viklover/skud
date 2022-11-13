package ru.team2.skud.persons.student;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.model.PersistableImpl;
import ru.team2.skud.card.Card;
import ru.team2.skud.persons.parent.Parent;
import java.util.List;

@Data
@Getter
@Setter
@Accessors(chain = true)
@Table("student")
public class Student extends PersistableImpl<String> {

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
    @JsonProperty("in_college")
    private boolean inCollege;

    @Transient
    @JsonProperty("parents")
    private List<Parent> parents;
}
