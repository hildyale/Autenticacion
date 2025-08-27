package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.entity.UserEntity;
import co.com.pragma.r2dbc.exception.UserNotFoundException;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, BigInteger, MyReactiveRepository>  implements UserRepository {

    public static final String USER_NOT_FOUND = "User not found with dni: %s";

    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class/* change for domain model */));
    }

    @Override
    public Mono<User> saveUser(User user) {
        return save(user);
    }

    @Override
    public Flux<User> getAllUser() {
        return findAll();
    }

    @Override
    public Mono<User> editUser(User user) {
        return null;
    }

    @Override
    public Mono<User> findByDni(String dni) {
        return repository.findByDni(dni)
                .switchIfEmpty(Mono.error(new UserNotFoundException(String.format(USER_NOT_FOUND, dni))))
                .map(this::toEntity);
    }
}
