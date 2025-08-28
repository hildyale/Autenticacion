package co.com.pragma.usecase.user;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> saveUser(User user){
        user.validarCamposCrearUsuario();
         return userRepository.saveUser(user);
    }

    public Flux<User> getAllUsers(){
        return userRepository.getAllUser();
    }

    public Mono<User> editUser(User user){
        return userRepository.editUser(user);
    }

    public Mono<User> findByDni(String dni){
        return userRepository.findByDni(dni);
    }
}
