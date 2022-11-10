package ru.team2.skud.config.mapper;

import org.mapstruct.NullValuePropertyMappingStrategy;

@org.mapstruct.MapperConfig(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MapperConfig {
}
