package adventure.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import adventure.entity.GCMRegID;
import adventure.persistence.GCMRegIDDAO;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ValidateRequest
@Path("/api/gcm")
@Produces(APPLICATION_JSON)
public class GCMService {

	@Inject
	private GCMRegIDDAO dao;

	@POST
	public Response saveGCMRegID(GCMForm form) throws MessagingException, IllegalAccessException, InvocationTargetException {
		
		// FIXME Ver a modelagem OO e escolher o melhor lugar para colocar esse código...
		
		GCMRegID gcmRegID = new GCMRegID();
		gcmRegID.setRegId(form.getRegId());
		
		dao.insert(gcmRegID);

		return Response.ok().build();
	}

	public static class GCMForm {

		@NotEmpty
		private String regId;

		public String getRegId() {
			return regId;
		}

		public void setRegId(String regId) {
			this.regId = regId;
		}
		
	}
	
	@Startup
	@Transactional
	public void loadTempData() throws MalformedURLException {
		
		if (dao.findAll().isEmpty()) {
			
			GCMRegID gcmRegID = new GCMRegID();
			gcmRegID.setRegId("APA91bHFlRHNahzYfzDl31U5g3vg6-R4mhF7AKr7D6xnxGdD4DaniO8T3KFzz6EBwxIfVW5o9DWYnZoJEA0VNahE93T7fj3-Pcc9O29CH1yng9qLFFoVS3KQeiW0s7V_R7_EL3LEhvZbamnO4s9DQHkeGAo8Y0xq-WvhkR1cXZP8MNpNX9EjZ1E");	
			
			dao.insert(gcmRegID);
			
		}
		
	}
	
}