package co.com.pragma.api.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes{

    private static final Logger logger = LogManager.getLogger(GlobalErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
        Throwable error = getError(request);
        logger.error(error);
        errorAttributes.put("message", error.getMessage());
        //errorAttributes.put("path", request.path());
        errorAttributes.put("error", "Bad Request.");
        return errorAttributes;
    }
}
