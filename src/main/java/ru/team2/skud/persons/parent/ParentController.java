package ru.team2.skud.persons.parent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.team2.skud.persons.parent.dto.NewParentDto;
import ru.team2.skud.persons.parent.dto.UpdateParentDto;

@RestController
@CrossOrigin
@RequestMapping("/parents")
@RequiredArgsConstructor
@Slf4j
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public Mono<Parent> create(@RequestBody NewParentDto parentResource) {
        return parentService.create(parentResource);
    }

    @PutMapping("/{id}")
    public Mono<Parent> update(@RequestBody UpdateParentDto parentResource, @PathVariable("id") Long id) {
        System.out.println(id);
        return parentService.update(id, parentResource);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return parentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Mono<Parent> findById(@PathVariable("id") Long id) {
        return parentService.findById(id);
    }

    @GetMapping
    public Flux<Parent> findAll() {
        return parentService.findAll();
    }
}
