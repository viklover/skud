package ru.team2.skud.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.model.Student;
import ru.team2.skud.rest.api.NewStudentResource;
import ru.team2.skud.service.StudentService;

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

    @GetMapping
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable("id") String id) {
        return studentService.findById(id);
    }
}
