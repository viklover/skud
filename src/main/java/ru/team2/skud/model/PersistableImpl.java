package ru.team2.skud.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
@Getter
@Setter
public abstract class PersistableImpl<ID> implements Persistable<ID> {
    @Id
    private ID id;

    @JsonIgnore
    @Transient
    boolean isNew = false;
}