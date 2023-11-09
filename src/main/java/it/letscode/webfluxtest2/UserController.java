package it.letscode.webfluxtest2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;

    @GetMapping("/users")
    public Flux<User> getAll() {
        return userRepo.findAll();
    }
}