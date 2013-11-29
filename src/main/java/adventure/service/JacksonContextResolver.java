package adventure.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

@Provider
@Produces(APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {

	private static ObjectMapper objectMapper;

	{
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
		objectMapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true);

		objectMapper.setSerializationInclusion(NON_NULL);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		objectMapper.setDateFormat(dateFormat);
	}

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}
}
