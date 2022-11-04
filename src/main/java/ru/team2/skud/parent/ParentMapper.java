package ru.team2.skud.parent;

import org.mapstruct.*;
import ru.team2.skud.base.mapper.BaseMapperConfig;
import ru.team2.skud.base.mapper.BaseMapper;
import ru.team2.skud.parent.dto.NewParentDto;
import ru.team2.skud.parent.dto.UpdateParentDto;

@Mapper(componentModel = "spring", config = BaseMapperConfig.class)
public abstract class ParentMapper extends BaseMapper<Long, Parent, NewParentDto, UpdateParentDto> {
}