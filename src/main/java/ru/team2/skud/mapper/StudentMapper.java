package ru.team2.skud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team2.skud.model.Student;
import ru.team2.skud.rest.api.NewStudentResource;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student toResource(Student item);

    @Mapping(target = "id")
    @Mapping(target = "firstName")
    @Mapping(target = "lastName")
    public abstract Student toModel(NewStudentResource item);
}
