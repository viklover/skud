package ru.team2.skud.parent;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.base.service.BaseEntityService;
import ru.team2.skud.db.relations.StudentsParentsRepository;
import ru.team2.skud.parent.dto.NewParentDto;
import ru.team2.skud.parent.dto.UpdateParentDto;

@Service
public class ParentService extends BaseEntityService<Long, Parent, ParentRepository, ParentMapper, NewParentDto, UpdateParentDto> {

    private final StudentsParentsRepository studentsParentsRepository;

    public ParentService(ParentRepository repository, ParentMapper mapper,
                         StudentsParentsRepository studentsParentsRepository) {
        super(repository, mapper);
        this.studentsParentsRepository = studentsParentsRepository;
    }

    public Flux<Parent> findParentsByStudentId(String id) {
        return studentsParentsRepository.findParentsByStudentId(id);
    }

    public Mono<Parent> findParentByTelephoneNumber(String telephoneNumber) {
        return repository.findParentByTelephoneNumber(telephoneNumber);
    }

    public Mono<Boolean> existsParentByTelephoneNumber(String telephoneNumber) {
        return repository.existsParentByTelephoneNumber(telephoneNumber);
    }
}