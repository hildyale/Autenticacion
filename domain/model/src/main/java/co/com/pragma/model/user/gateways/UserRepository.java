package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository {
    Mono<User> saveUser(User user);
    Flux<User> getAllUser();
    Mono<User> editUser(User user);
    Mono<User> findByDni(String dni);

}
