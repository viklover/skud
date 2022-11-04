package ru.team2.skud.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.db.relations.StudentsParentsRepository;
import ru.team2.skud.parent.dto.NewParentDto;
import ru.team2.skud.parent.dto.UpdateParentDto;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    private final StudentsParentsRepository studentsParentsRepository;

    public Mono<Parent> create(NewParentDto parentResource) {
        return parentRepository.save(parentMapper.newParentDtoToParent(parentResource));
    }

    public Mono<Parent> update(Long id, UpdateParentDto parentResource) {
        return parentRepository.findById(id).flatMap(parent -> {
            parentMapper.updateParentDtoToParent(parent, parentResource);
            return parentRepository.save(parent);
        });
    }

    public Flux<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Flux<Parent> findParentsByStudentId(String id) {
        return studentsParentsRepository.findParentsByStudentId(id);
    }
}