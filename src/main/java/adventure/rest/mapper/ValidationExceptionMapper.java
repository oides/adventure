package adventure.rest.mapper;

import static javax.servlet.http.HttpServletResponse.SC_PRECONDITION_FAILED;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.method.MethodConstraintViolationException;

import adventure.persistence.ValidationException;

@Provider
@SuppressWarnings("deprecation")
public class ValidationExceptionMapper implements ExceptionMapper<javax.validation.ValidationException> {

	@Override
	public Response toResponse(javax.validation.ValidationException exception) {
		ValidationException validation = null;

		if (exception instanceof MethodConstraintViolationException) {
			validation = ValidationException.parse((MethodConstraintViolationException) exception);
		} else if (exception instanceof ValidationException) {
			validation = ((ValidationException) exception);
		} else {
			System.out.println(exception.getClass().getName());
			exception.printStackTrace();
		}

		return Response.status(SC_PRECONDITION_FAILED).entity(validation.getConstraintViolations()).build();
	}
}
