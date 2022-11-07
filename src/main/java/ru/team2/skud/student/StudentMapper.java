package ru.team2.skud.student;

import org.mapstruct.Mapper;
import ru.team2.skud.base.mapper.BaseMapper;
import ru.team2.skud.base.mapper.BaseMapperConfig;
import ru.team2.skud.student.dto.NewStudentDto;
import ru.team2.skud.student.dto.StudentForEventDto;
import ru.team2.skud.student.dto.UpdateStudentDto;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class StudentMapper extends BaseMapper<String, Student, NewStudentDto, UpdateStudentDto> {

    public abstract StudentForEventDto studentToStudentForEventDto(Student student);
}
