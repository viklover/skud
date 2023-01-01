package ru.team2.skud.persons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonsService {

    private final PersonsRepository studentsParentsRepository;

    public Mono<Persons> create(String student_id, Long parent_id) {
        return studentsParentsRepository.save(new Persons().setStudentId(student_id).setParentId(parent_id));
    }

    public Mono<Void> deletePair(String student_id, Long parent_id) {
        return studentsParentsRepository.findPairByIds(student_id, parent_id)
                .flatMap(studentsParentsRepository::delete);
    }

}
