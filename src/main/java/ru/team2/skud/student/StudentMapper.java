package ru.team2.skud.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student toResource(Student item);

    @Mapping(target = "id")
    @Mapping(target = "firstName")
    @Mapping(target = "lastName")
    @Mapping(target = "cardId")
    public abstract Student toModel(NewStudentResource item);
}
