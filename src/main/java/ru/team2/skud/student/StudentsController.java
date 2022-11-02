package ru.team2.skud.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentsController {

    private final StudentService studentService;

    @PostMapping
    public Mono<Student> create(final @RequestBody NewStudentResource student) {
        return studentService.create(student);
    }

    @PutMapping
    public Mono<Student> update(final @RequestBody NewStudentResource student) {
        return studentService.update(student);
    }

    @GetMapping
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable("id") String id) {
        return studentService.findById(id);
    }
}
