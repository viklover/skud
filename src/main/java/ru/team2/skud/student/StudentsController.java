package ru.team2.skud.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.db.relations.StudentsParents;
import ru.team2.skud.db.relations.StudentsParentsService;
import ru.team2.skud.student.dto.NewStudentDto;
import ru.team2.skud.student.dto.UpdateStudentDto;

@RestController
@CrossOrigin
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentsController {

    private final StudentService studentService;
    private final StudentsParentsService studentsParentsService;

    @PostMapping
    public Mono<Student> create(final @RequestBody NewStudentDto student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Mono<Student> update(@PathVariable("id") String id, @RequestBody UpdateStudentDto student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return studentService.deleteById(id);
    }

    @GetMapping
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable("id") String id) {
        return studentService.findById(id);
    }

    @PostMapping("/{id}/add_parent")
    public Mono<StudentsParents> addParent(@PathVariable("id") String student_id, @RequestParam Long parent_id) {
        return studentsParentsService.create(student_id, parent_id);
    }

    @DeleteMapping("/{id}/delete_parent")
    public Mono<Void> deleteParent(@PathVariable("id") String student_id, @RequestParam Long parent_id) {
        return studentsParentsService.deletePair(student_id, parent_id);
    }
}
