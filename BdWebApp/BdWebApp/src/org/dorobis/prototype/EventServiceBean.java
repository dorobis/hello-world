package org.dorobis.prototype;

import javax.ejb.Stateless;
import org.dorobis.prototype.EventServiceRemote;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public @Stateless class EventServiceBean implements EventServiceRemote {
	@PersistenceContext(unitName="org.dorobis.prototype")
	public EntityManager entityManager;
	
	public  EventServiceBean() {
		System.out.println("EventServiceBean instance created.");
	}
	
	public void addEvent(Event event) {
		entityManager.persist(event);
		entityManager.flush();
	}
	
	public void addEvent(String title, Date dateInstance) {
		entityManager.persist(new Event(title, dateInstance));
		entityManager.flush();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		return entityManager.createNativeQuery("select * from Events", Event.class ).getResultList();
	}
}
