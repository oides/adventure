package adventure.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import adventure.entity.Event;
import adventure.entity.GCMRegID;
import adventure.persistence.GCMRegIDDAO;

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
			gcmRegID.setRegId("APA91bHeSz2o5XsC8v8dXJkRlYEOBpeJfbVT3CwyiNyC5CB6rNRptAqpvSrkU1FLnOZA994jBg_NERaGGyJYu76myKB19t1xK836oyF7TbU_9i6hpUQahZzbyoYtsTHBHBnDoGWNG3PtbOP0T3okk2za1WqcURCllI8s_XLo623COSvG8g_O6E4");	
			
			dao.insert(gcmRegID);
			
		}
		
	}
	
}
