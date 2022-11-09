package ru.team2.skud.persons;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@Setter
@Accessors(chain = true)
@Table("students_parents")
public class StudentsParents {
    @Id
    private Long id;
    private String studentId;
    private Long parentId;
}
