package ru.team2.skud.base.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import ru.team2.skud.base.api.PersistableImpl;

public abstract class BaseMapper<ID, Entity extends PersistableImpl<ID>, NewEntityDto, UpdateEntityDto> {

    public abstract Entity newEntityDtoToEntity(NewEntityDto newEntityDto);
    public abstract void updateEntityDtoToEntity(@MappingTarget Entity entity, UpdateEntityDto updateEntityDto);

    @AfterMapping
    public void afterMapping(@MappingTarget Entity entity, NewEntityDto entityDto) {
        entity.setNew(true);
    }
}
