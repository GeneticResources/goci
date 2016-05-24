package uk.ac.ebi.spot.goci.curation;

import uk.ac.ebi.spot.goci.model.Event;
import uk.ac.ebi.spot.goci.model.EventType;
import uk.ac.ebi.spot.goci.model.SecureUser;

import java.util.Date;

/**
 * Created by emma on 24/05/2016.
 *
 * @author emma
 *         <p>
 *         Event builder for use during testing
 */
public class EventBuilder {

    private Event event = new Event();

    public EventBuilder setId(Long id) {
        event.setId(id);
        return this;
    }

    public EventBuilder setEventDate(Date eventDate) {
        event.setEventDate(eventDate);
        return this;
    }

    public EventBuilder setEventType(EventType eventType) {
        event.setEventType(eventType);
        return this;
    }

    public EventBuilder setUser(SecureUser user) {
        event.setUser(user);
        return this;
    }

    public Event build() {
        return event;
    }
}