package it.letscode.webfluxtest2;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User, String> {
}
