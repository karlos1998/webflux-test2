package it.letscode.webfluxtest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.Random;
import java.time.Duration;

@Service
public class InitService {
    private UserRepo userRepo;

    public InitService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void get() {
//        userRepo.deleteAll()
//                .thenMany(
//                        Flux.just("Karol", "Szymon", "Wojtek")
//                )
//                .map(User::new)
//                .flatMap(userRepo::save)
//                .thenMany(userRepo.findAll())
//                .subscribe(System.out::println);
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        userRepo.deleteAll().thenMany(createAndSaveRandomUserPeriodically()).subscribe();
    }

    private Flux<User> createAndSaveRandomUserPeriodically() {
        return Flux.interval(Duration.ofSeconds(5))
                .flatMap(ignore -> userRepo.save(new User(generateRandomName())))
                .doOnNext(user -> System.out.println("Nowy użytkownik: " + user.getName()));
    }

    private String generateRandomName() {
        // Tutaj można wygenerować losowe imię na potrzeby użytkownika
        // Możesz użyć biblioteki do generowania losowych danych
        return "User" + new Random().nextInt(100);
    }
}
