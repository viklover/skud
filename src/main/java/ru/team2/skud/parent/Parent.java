package ru.team2.skud.parent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@Setter
@Accessors(chain = true)
@Table("parent")
public class Parent implements Persistable<Long> {

    @Id
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("telephone_number")
    private String telephoneNumber;

    @JsonIgnore
    @ReadOnlyProperty
    @Transient
    boolean isNew = false;

    public boolean isNew() {
        return isNew;
    }
}
