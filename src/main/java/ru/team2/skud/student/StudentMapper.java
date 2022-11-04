package ru.team2.skud.student;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.team2.skud.base.maper.BaseMapperConfig;
import ru.team2.skud.student.dto.NewStudentDto;
import ru.team2.skud.student.dto.UpdateStudentDto;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class StudentMapper {

    public abstract Student toResource(Student item);

    public abstract Student newStudentDtoToStudent(NewStudentDto item);

    public abstract void updateStudentDtoToStudent(@MappingTarget Student parent, UpdateStudentDto updateStudentDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Student student, NewStudentDto studentDto) {
        student.setNew(true);
    }
}
