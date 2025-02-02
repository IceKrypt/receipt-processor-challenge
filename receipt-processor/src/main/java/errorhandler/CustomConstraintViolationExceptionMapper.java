package errorhandler;

import io.dropwizard.jersey.validation.JerseyViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomConstraintViolationExceptionMapper implements ExceptionMapper<JerseyViolationException> {

    @Override
    public Response toResponse(JerseyViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity("The receipt is invalid.").build();

    }
}