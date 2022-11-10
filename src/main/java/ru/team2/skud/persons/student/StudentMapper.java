package ru.team2.skud.persons.student;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.team2.skud.config.mapper.MapperConfig;
import ru.team2.skud.persons.student.dto.NewStudentDto;
import ru.team2.skud.persons.student.dto.StudentForEventDto;
import ru.team2.skud.persons.student.dto.UpdateStudentDto;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public abstract class StudentMapper {
    public abstract Student newStudentDtoToStudent(NewStudentDto newStudentDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Student student, NewStudentDto newStudentDto) {
        student.setNew(true);
    }

    public abstract void updateStudentDtoToStudent(@MappingTarget Student student, UpdateStudentDto updateStudentDto);

    public abstract StudentForEventDto studentToStudentForEventDto(Student student);
}
