package ru.team2.skud.persons.parent;

import org.mapstruct.*;
import ru.team2.skud.config.mapper.MapperConfig;
import ru.team2.skud.persons.parent.dto.NewParentDto;
import ru.team2.skud.persons.parent.dto.UpdateParentDto;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public abstract class ParentMapper {
    public abstract Parent newParentDtoToParent(NewParentDto newParentDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Parent parent, NewParentDto newParentDto) {
        parent.setNew(true);
    }

    public abstract void updateParentDtoToParent(@MappingTarget Parent parent, UpdateParentDto updateParentDto);
}
