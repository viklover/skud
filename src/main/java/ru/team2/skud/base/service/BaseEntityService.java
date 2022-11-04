package ru.team2.skud.base.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.base.api.PersistableImpl;
import ru.team2.skud.base.mapper.BaseMapper;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

@RequiredArgsConstructor
public abstract class BaseEntityService<
        ID,
        Entity extends PersistableImpl<ID>,
        Repository extends ReactiveSortingRepository<Entity, ID>,
        Mapper extends BaseMapper<ID, Entity, NewEntityDto, UpdateEntityDto>,
        NewEntityDto,
        UpdateEntityDto> {

    protected final Repository repository;
    protected final Mapper mapper;

    public Mono<Entity> create(NewEntityDto newEntityDto) {
        return repository.save(mapper.newEntityDtoToEntity(newEntityDto))
                .flatMap(this::loadRelations);
    }

    public Mono<Entity> update(ID id, UpdateEntityDto updateEntityDto) {
        return repository.findById(id)
                .flatMap(entity -> {
                    mapper.updateEntityDtoToEntity(entity, updateEntityDto);
                    return repository.save(entity);
                })
                .flatMap(this::loadRelations);
    }

    public Mono<Void> deleteById(ID id) {
        return repository.deleteById(id);
    }

    public Mono<Entity> findById(ID id) {
        return repository.findById(id)
                .flatMap(this::loadRelations);
    }

    public Flux<Entity> findAll() {
        return findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Flux<Entity> findAll(Sort sort) {
        return repository.findAll(sort)
                .flatMap(this::loadRelations);
    }

    protected Mono<Entity> loadRelations(Entity entity) {
        return Mono.just(entity);
    }
}
