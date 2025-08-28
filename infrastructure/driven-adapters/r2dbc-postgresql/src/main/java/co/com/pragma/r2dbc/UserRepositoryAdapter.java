package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.entity.UserEntity;
import co.com.pragma.r2dbc.exception.UserNotFoundException;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<User, UserEntity, BigInteger, UserRepository>  implements co.com.pragma.model.user.gateways.UserRepository {

    public static final String USER_NOT_FOUND = "User not found with dni: %s";
    private static final Logger logger = LogManager.getLogger(UserRepositoryAdapter.class);

    public UserRepositoryAdapter(UserRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class/* change for domain model */));
    }

    @Override
    @Transactional
    public Mono<User> saveUser(User user) {
        return save(user).doOnSuccess(logger::info);
    }

    @Override
    public Flux<User> getAllUser() {
        return findAll();
    }

    @Override
    @Transactional
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
