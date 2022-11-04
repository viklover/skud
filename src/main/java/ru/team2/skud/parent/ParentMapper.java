package ru.team2.skud.parent;

import org.mapstruct.*;
import ru.team2.skud.base.maper.BaseMapperConfig;
import ru.team2.skud.parent.dto.NewParentDto;
import ru.team2.skud.parent.dto.UpdateParentDto;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class ParentMapper {

    public abstract Parent newParentDtoToParent(NewParentDto item);

    public abstract void updateParentDtoToParent(@MappingTarget Parent parent, UpdateParentDto updateParentDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Parent parent, NewParentDto parentDto) {
        parent.setNew(true);
    }
}
