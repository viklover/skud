package ru.team2.skud.persons.parent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.base.api.PersistableImpl;

@Data
@Getter
@Setter
@Accessors(chain = true)
@Table("parent")
public class Parent extends PersistableImpl<Long> {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("telephone_number")
    private String telephoneNumber;
}
