package co.com.pragma.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    private static final String USER_PATH = "/api/v1/usuarios";
    private static final String USER_BY_ID_PATH = "/api/v1/usuarios/{dni}";

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET(USER_PATH), handler::listenGetAllUsers)
                .andRoute(POST(USER_PATH), handler::listenSaveUser)
                .andRoute(GET(USER_BY_ID_PATH), handler::listenGetUserByDni);
    }
}
