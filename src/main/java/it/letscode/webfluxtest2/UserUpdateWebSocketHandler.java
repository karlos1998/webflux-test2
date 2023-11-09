package it.letscode.webfluxtest2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserUpdateWebSocketHandler implements WebSocketHandler {

    private final UserRepo userRepo;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<User> userUpdates = userRepo.findAll();

        return session.send(userUpdates
                        .map(user -> session.textMessage("Nowy użytkownik: " + user.getName())))
                .and(session.receive().then());
    }
}