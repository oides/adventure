package adventure.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import adventure.entity.Event;
import adventure.gcm.AdventureGCMUtil;
import adventure.persistence.EventDAO;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ValidateRequest
@Path("/api/event")
@Produces(APPLICATION_JSON)
public class EventService {

	@Inject
	private EventDAO dao;
	
	@Inject
	private AdventureGCMUtil adventureGCMUtil;
	
	@GET
	public List<Event> search() {
		return dao.findAll();
	}
	
	@POST
	public Response saveEvent(EventForm eventForm) throws MessagingException, IllegalAccessException, InvocationTargetException, MalformedURLException {
		
		// FIXME Ver a modelagem OO e escolher o melhor lugar para colocar esse código...
		
		Event event = new Event();
		event.setNome(eventForm.getNome());
		event.setData(new Date());
		event.setLocal(eventForm.getLocal());
		event.setLink(new URL(eventForm.getUrl()));
		
		dao.insert(event);

		adventureGCMUtil.sendMessage(event.getNome());
		
		return Response.ok().build();
	}

	public static class EventForm {

		@NotEmpty
		private String nome;

		@NotEmpty
		private String local;

		@NotEmpty
		private String url;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
				
	}
	
	@Startup
	@Transactional
	public void loadTempData() throws MalformedURLException {
		if (dao.findAll().isEmpty()) {
			Event event;

			event = new Event();
			event.setNome("Desafio dos Sertões 2014");
			event.setData(new Date());
			event.setLocal("Juazeiro-BA");
			event.setLink(new URL("http://www.desafiodossertoes.com.br"));
			dao.insert(event);

			event = new Event();
			event.setNome("Noite do Perrenge II");
			event.setData(new Date());
			event.setLocal("Sauípe-BA");
			event.setLink(new URL("http://www.noitedoperrengue.com.br"));
			dao.insert(event);
		}
	}
}
