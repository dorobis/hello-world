package org.dorobis.prototype;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
@Remote

public interface EventServiceRemote {
	public List<Event> getEvents();
	public void addEvent(Event event);
	public void addEvent(String title, Date dateInstance);
}
