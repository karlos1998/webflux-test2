package it.letscode.webfluxtest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InitService {
    private UserRepo userRepo;

    public InitService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        userRepo.deleteAll()
                .thenMany(
                        Flux.just("Karol", "Szymon", "Wojtek")
                )
                .map(User::new)
                .flatMap(userRepo::save)
                .thenMany(userRepo.findAll())
                .subscribe(System.out::println);
    }
}
