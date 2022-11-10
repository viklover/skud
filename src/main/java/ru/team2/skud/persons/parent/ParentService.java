package ru.team2.skud.persons.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.PersonsRepository;
import ru.team2.skud.persons.parent.dto.NewParentDto;
import ru.team2.skud.persons.parent.dto.UpdateParentDto;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    private final PersonsRepository studentsParentsRepository;

    public Mono<Parent> create(NewParentDto newEntityDto) {
        return parentRepository.save(parentMapper.newParentDtoToParent(newEntityDto));
    }

    public Mono<Parent> update(Long id, UpdateParentDto updateParentDto) {
        return parentRepository.findById(id)
                .flatMap(parent -> {
                    parentMapper.updateParentDtoToParent(parent, updateParentDto);
                    return parentRepository.save(parent);
                });
    }

    public Mono<Void> deleteById(Long id) {
        return parentRepository.deleteById(id);
    }

    public Mono<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public Flux<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Mono<Parent> findParentByTelephoneNumber(String telephoneNumber) {
        return parentRepository.findParentByTelephoneNumber(telephoneNumber);
    }

    public Mono<Boolean> existsParentByTelephoneNumber(String telephoneNumber) {
        return parentRepository.existsParentByTelephoneNumber(telephoneNumber);
    }

    public Flux<Parent> findParentsByStudentId(String id) {
        return studentsParentsRepository.findParentsByStudentId(id);
    }
}